$(document).ready(function() {
	
	/*  To display the area datatables in the area jsp page  */

	$('#candidateOnboardTable').DataTable( {
		"scrollY":        "300px",
		"scrollX":        true,
		"scrollCollapse": true
	});
	
	var candidates=[];
	
	var candidate={};
	
	var candidatecntr=0;
	
	var parCode=0;
	
	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});
	
	/* To display the candidateOnboard update  pop modal */

	$("#candidateOnboardTable tbody").on('click', '.btncandidateOnboardEdit', function () {
		$('#candidateonboardform').removeClass('was-validated');
		var table = $("#candidateOnboardTable").DataTable();
		var candidateOnboard = table.row($(this).closest('tr')).data();
		var tempPrescreeningDate = candidateOnboard[1];
		

		$('[name="candidateNameModal"]').val(candidateOnboard[0]);		
		
		$('[name="startedModal"]').val(candidateOnboard[2]);
		$('[name="actualStartdateModal"]').val(candidateOnboard[3]);
		
		if(tempPrescreeningDate != null)
		{ 
			var expectedstartdate = tempPrescreeningDate.substring(6) + '-' + 
							 tempPrescreeningDate.substring(0,2) +'-'+
                             tempPrescreeningDate.substring(3,5);
			$('[name="startDateModal"]').val(expectedstartdate);
		}
		
		$('#candidateOnboardEditModal').modal('show');		

	});
	
	/* To display the candidate submit delete confirmation pop modal  */

	$("#candidateOnboardTable tbody").on('click', '.btncandidateOnboardDelete', function () {
		var table = $("#candidateOnboardTable").DataTable();
		var candidateOnboard = table.row($(this).closest('tr')).data();			
		$('#candidateOnboardDeleteconfirmModalBody').html("Are you sure, you want to delete the candidate from this par : <strong> "+candidateOnboard[1]+" <strong> ?");
		$('#candidateNameModal').val(candidateOnboard[0]);
		$('#candidateOnboardDeleteconfirmModal').modal('show');		

	});
	
	$('#messageClose-btn').click(function(){
		var parNum = $('[name="parNo"]').val();
		getParAllocationInfo(parNum)
	});
	
	/* Performs the functionality of deleting the select candidate */

	$('#candidateOnboardModalDelete-btn').click(function(){
		$('#candidateOnboardDeleteconfirmModal').modal('hide');
		var parAllocId= $('#candidateOnboardModalDeleteParAllocId').val();
	
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteParAllocationByParAllocId/"+parAllocId,
			data: "",
			success:function(data){
				$('#candidateOnboardDeleteModal').modal('hide');	
				$('#messageModalBody').html(data);
				$('#messageModal').modal('show');  				
			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});
	});
	
	/* Performs the functionality of adding or updating the submit candidate informations */

	$('#candidateOnboardModalEdit-btn').click(function() {


		// Fetch form to apply custom Bootstrap validation
		var url="";
		var isValid = $('#candidateonboardform')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#candidateOnboardForm').addClass('was-validated');

		if (isValid)
		{

			
			var candidateId=0;
			
			var candidateName= $('[name="candidateNameModal"]').val();
			$.each(candidates,function(){
				if(this.candidateName.localeCompare(candidateName) ==0){
					candidateId=this.candidateId;
				}
			});
			var candidateactualstartDate = $('[name="actualStartdateModal"]').val();
			var data = '{"parCode":"'+parCode+'","candidateId":"'+candidateId+'","actualStartDate":"'+candidateactualstartDate+'"}';	

			url="./updateCandidateonBoard";
			
			$.ajax({
				type:"POST",
				dataType:"text",
				contentType: "application/json",
				url:url,
				data: data,
				success:function(data){
					$('#candidateOnboardEditModal').modal('hide');	
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
	/* To get the entered par number information*/

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
					parCode = dataout.parId;
					$('[name="parNo"]').val(dataout.parNumber);
					var parReceivedDate = dataout.parReceivedDate.substring(6) + '-' + 
                    					  dataout.parReceivedDate.substring(0,2) +'-'+
                                          dataout.parReceivedDate.substring(3,5);
                    $('[name="parDateReceived"]').val(parReceivedDate);
                    $('[name="extStaffName"]').val(dataout.externalStaff.extStaffName);
                    $('[name="parRole"]').val(dataout.parRole.roleName);
                    $('[name="skill"]').val(dataout.skill.skillName);
					
					getParAllocationInfo(parNum);	
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
	
	

	/*  Function to get all the par allocation  information */

	function getParAllocationInfo(parNum) {
		var action='';
		
		$.ajax({
			type:"GET",
			dataType:"text",
			contentType: "text/plain",
			url:"./getParAllocationByParNum/"+parNum,
			data: "",
			success:function(data){
				$('#candidateOnboardTable').dataTable().fnClearTable();
				var jsonObj = JSON.parse(data);
				var jsonLen = jsonObj.length;
				if(jsonLen > 0 )
				{
					$('#candidateOnboardTable').DataTable().clear().destroy();
					
					$('#candidateOnboardTable').DataTable( {
						"scrollY":        "300px",
						"scrollX":        true,
						"scrollCollapse": true
					});
					
					parcode = jsonObj[0].parCode;
					
					$.each(jsonObj, function() {
						candidates.push(this.candidate);
						if(this.actualStartDate != " "){
							if(this.actualStartDate > this.expectedStartDate){
								action ='N';
							}else{
								action ='Y';
							}
							
						}else{
							action='N';
						}
						$('#candidateOnboardTable').dataTable().fnAddData( [
					        this.candidate.candidateName,
					        this.expectedStartDate,
					        action,
					        this.actualStartDate,

					   
			
					        '<button type="button" class="btn btncandidateOnboardEdit btn-link" id="candidateOnboardTableEdit-btn">Edit</button> / <button type="button" class="btn btncandidateOnboardDelete btn-link" id="candidateOnboardTableDelete-btn">Delete</button>'
					        
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