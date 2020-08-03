$(document).ready(function() {
	
	$('#candidateAdd-btn').attr('disabled','disabled');
	
	/*  To display the candidate datatables in the candidate received jsp page  */

	$('#candidateReceivedTable').DataTable( {
		"scrollY":        "300px",
		"scrollCollapse": true,
		"scrollX":        true
	});
	
	
	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});


	/* To display the candidate received delete confirmation pop modal  */

	$("#candidateReceivedTable tbody").on('click', '.btnCandidateReceivedDelete', function () {
		var table = $("#candidateReceivedTable").DataTable();
		var candidateReceived = table.row($(this).closest('tr')).data();			
		$('#candidateReceivedDeleteconfirmModalBody').html("Are you sure, you want to delete the candidate from this par : <strong> "+candidateReceived[1]+" <strong> ?");
		$('#candidateReceivedModalDeleteParAllocId').val(candidateReceived[0]);
		$('#candidateReceivedDeleteconfirmModal').modal('show');		

	});
	
	
	$('#messageClose-btn').click(function(){
		var parNum = $('[name="parNo"]').val();
		getCandidateReceivedInfo(parNum)
	});

	/* Performs the functionality of deleting the select candidate */

	$('#candidateReceivedModalDelete-btn').click(function(){
		$('#candidateReceivedDeleteconfirmModal').modal('hide');
		var parAllocId= $('#candidateReceivedModalDeleteParAllocId').val();
	
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteParAllocationByParAllocId/"+parAllocId,
			data: "",
			success:function(data){
				$('#candidateReceivedDeleteModal').modal('hide');	
				$('#messageModalBody').html(data);
				$('#messageModal').modal('show');  				
			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});
	});
	
	/* To get the active candidate and recruiter from the corresponding tables */

	$('#candidateAdd-btn').click(function(){
		$('#candidateReceivedForm').removeClass('was-validated');
		$('#candidateReceivedModalProcess').val("candidateReceivedAdd");
		getActiveCandidates();
		getActiveRecruiters();
		$('#candidateReceivedEditModal').modal('show');
	}); 
	

	/* Performs the functionality of adding or updating the candidate received informations */

	$('#candidateReceivedModalEdit-btn').click(function() {


		// Fetch form to apply custom Bootstrap validation
		var url="";
		var isValid = $('#candidateReceivedForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#candidateReceivedForm').addClass('was-validated');

		if (isValid)
		{

			var parCode = $('[name="parId"]').val();
			var candidateId = $('[name="candidateNameModal"] :selected').val();
			var candidateName = $('[name="candidateNameModal"] :selected').text();
			var recruiterId = $('[name="recruiterNameModal"] :selected').val();
			var recruiterName = $('[name="recruiterNameModal"] :selected').text();
			var process = $('#candidateReceivedModalProcess').val();
			var data = '{"parCode":"'+parCode+'","candidateId":"'+candidateId+'","candidateName":"'+candidateName+'","recruiterName":"'+recruiterName+'","recruiterId":"'+recruiterId+'"}';	
		
			alert(data);
			alert(process);
			if (process == 'candidateReceivedAdd')
			{
				url = "./createParAllocation";
			}
			else
			{
				url="./updateParAllocation";
			}
	
			$.ajax({
				type:"POST",
				dataType:"text",
				contentType: "application/json",
				url:url,
				data: data,
				success:function(data){
					$('#candidateReceivedEditModal').modal('hide');	
					$('#messageModalBody').html(data);
					$('#messageModal').modal('show');  				
				},
				error:function(req, status, error)
				{
					
					console.log(req.responseText);
					console.log(status,error);
				}	
			});
		 }
	});
	
	/* To get the entered par number*/

	$('[name="parNo"]').change(function(){
		
		var data="";
		var parNum = $('[name="parNo"]').val();
		
		$.ajax({
			type:"GET",
			dataType:"text",
			contentType: "application/json",
			url:"./getParMasterByParNum/"+parNum,
			data: data,
			success:function(data){
				

				if(data.length > 0)
				{
					var dataout=$.parseJSON(data);
					$('#candidateAdd-btn').removeAttr('disabled');
					
					$('[name="parId"]').val(dataout.parId);
					$('[name="parNo"]').val(dataout.parNumber);
					var parReceivedDate = dataout.parReceivedDate.substring(6) + '-' + 
                    					  dataout.parReceivedDate.substring(0,2) +'-'+
                                          dataout.parReceivedDate.substring(3,5);
                    $('[name="parDateReceived"]').val(parReceivedDate);
                    $('[name="extStaffName"]').val(dataout.externalStaff.extStaffName);
                    $('[name="parRole"]').val(dataout.parRole.roleName);
                    $('[name="skill"]').val(dataout.skill.skillName);
					
					getCandidateReceivedInfo(parNum);	
				}
				else
				{
					$('#messageModalBody').html("Data not found for the Par Number : "+parNum);
					$('#messageModal').modal('show');
				}
				
			},
			error:function(req, status, error)
			{
				console.log("responseText:" + req.responseText);
				console.log(status,error);
			}	
		});
	}); 

	
	
	/*  Function to get all active candidate and populate the drop down */

	function getActiveCandidates() {
		
		$.ajax({
			type:"GET",
			dataType:"text",
			contentType: "text/plain",
			url:"./getActiveCandidates",
			data: "",
			success:function(data){
				$('[name="candidateNameModal"]').empty();
				var candidateDropDown = $('[name="candidateNameModal"]'); 
				
				var jsonObj = JSON.parse(data);
				$.each(jsonObj, function() {
					
						candidateDropDown.append($("<option />").val(this.candidateId).text(this.candidateName));
					
				    
				});
			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});
		
	}
	
	/*  Function to get all active recruiter and populate the drop down */

	function getActiveRecruiters() {
		
		$.ajax({
			type:"GET",
			dataType:"text",
			contentType: "text/plain",
			url:"./getActiveRecruiters",
			data: "",
			success:function(data){
				$('[name="RecruiterNameModal"]').empty();
				var recruiterDropDown = $('[name="recruiterNameModal"]'); 
				
				var jsonObj = JSON.parse(data);
				$.each(jsonObj, function() {
					
						recruiterDropDown.append($("<option />").val(this.recruiterId).text(this.recruiterName));
					
				    
				});
			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});
		
	}
	
	/*  Function to get all the candidate received information */

	function getCandidateReceivedInfo(parNum) {
		
		$.ajax({
			type:"GET",
			dataType:"text",
			contentType: "text/plain",
			url:"./getCandidateReceivedByParNum/"+parNum,
			data: "",
			success:function(data){
				$('#candidateReceivedTable').dataTable().fnClearTable();
				var jsonObj = JSON.parse(data);
				var jsonLen = jsonObj.length;
				if(jsonLen > 0 )
				{
					$('#candidateReceivedTable').DataTable().clear().destroy();
					
					$('#candidateReceivedTable').DataTable( {
						"scrollY":        "300px",
						"scrollCollapse": true,
						"scrollX":        true
					});
					
					$.each(jsonObj, function() {
						$('#candidateReceivedTable').dataTable().fnAddData( [
							this.parAllocationId,
					        this.candidate.candidateName,
					        this.recruiter.recruiterName,
					        this.candidate.candidateReceivedDate,
					       
					        '<button type="button" class="btn btnCandidateReceivedEdit btn-link" id="candidateReceivedTableEdit-btn">Edit</button> / <button type="button" class="btn btnCandidateReceivedDelete btn-link" id="candidateReceivedTableDelete-btn">Delete</button>'
					        
					      ]
						
						);
					    
					});
					
				}

			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});
		
	}

	
});