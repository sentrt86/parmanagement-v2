$(document).ready(function() {
	
	/*  To display the area datatables in the area jsp page  */

	$('#submitCandidateTable').DataTable( {
		"scrollY":        "300px",
		"scrollCollapse": true
	});
	
	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});
	
	/* To display the area update  pop modal */

	$("#submitCandidateTable tbody").on('click', '.btnSubmitCandidateEdit', function () {
		$('#submitCandidateForm').removeClass('was-validated');
		var table = $("#submitCandidateTable").DataTable();
		var submitCandidate = table.row($(this).closest('tr')).data();
		var tempSubmitDate = submitCandidate[2];
		
		$('#submitCandidateModalProcess').val("submitCandidateEdit");
		$('#submitCandidateModalEditParAllocId').val(submitCandidate[0]);
		
		$('[name="candidateNameModal"]').val(submitCandidate[1]);
		
		if(tempSubmitDate != null)
		{ 
			var submitDate = tempSubmitDate.substring(6) + '-' + 
							 tempSubmitDate.substring(0,2) +'-'+
                             tempSubmitDate.substring(3,5);
			$('[name="submitDateModal"]').val(submitDate);
		}
		$('#submitCandidateEditModal').modal('show');		

	});
	
	/* To display the candidate submit delete confirmation pop modal  */

	$("#submitCandidateTable tbody").on('click', '.btnSubmitCandidateDelete', function () {
		var table = $("#submitCandidateTable").DataTable();
		var submitCandidate = table.row($(this).closest('tr')).data();			
		$('#submitCandidateDeleteconfirmModalBody').html("Are you sure, you want to delete the candidate from this par : <strong> "+submitCandidate[1]+" <strong> ?");
		$('#submitCandidateModalDeleteParAllocId').val(submitCandidate[0]);
		$('#submitCandidateDeleteconfirmModal').modal('show');		

	});
	
	$('#messageClose-btn').click(function(){
		var parNum = $('[name="parNo"]').val();
		getParAllocationInfo(parNum)
	});
	
	/* Performs the functionality of deleting the select candidate */

	$('#submitCandidateModalDelete-btn').click(function(){
		$('#submitCandidateDeleteconfirmModal').modal('hide');
		var parAllocId= $('#submitCandidateModalDeleteParAllocId').val();
	
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteParAllocationByParAllocId/"+parAllocId,
			data: "",
			success:function(data){
				$('#submitCandidateDeleteModal').modal('hide');	
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

	$('#submitCandidateModalEdit-btn').click(function() {


		// Fetch form to apply custom Bootstrap validation
		var url="";
		var isValid = $('#submitCandidateForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#submitCandidateForm').addClass('was-validated');

		if (isValid)
		{

			var parCode = $('[name="parId"]').val();
			var parAllocId = $('#submitCandidateModalEditParAllocId').val();
			var candidateName = $('[name="candidateNameModal"]').val();
			var submitDate = $('[name="submitDateModal"]').val();
			var submitIndicator = 'Yes';
			var process = $('#submitCandidateModalProcess').val();
			var data = '{"parCode":"'+parCode+'","parAllocId":"'+parAllocId+'","candidateName":"'+candidateName+'","submitDate":"'+submitDate+'","submitIndicator":"'+submitIndicator+'"}';	
		
			if (process == 'submitCandidateEdit')
			{
				url="./updateSubmitCandidate";
			}
	
			$.ajax({
				type:"POST",
				dataType:"text",
				contentType: "application/json",
				url:url,
				data: data,
				success:function(data){
					$('#submitCandidateEditModal').modal('hide');	
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
					$('[name="parId"]').val(dataout.parId);
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
	/*  Function to get all the par allocation information */

	function getParAllocationInfo(parNum) {
		
		$.ajax({
			type:"GET",
			dataType:"text",
			contentType: "text/plain",
			url:"./getParAllocationByParNum/"+parNum,
			data: "",
			success:function(data){
				$('#submitCandidateTable').dataTable().fnClearTable();
				var jsonObj = JSON.parse(data);
				var jsonLen = jsonObj.length;
				if(jsonLen > 0 )
				{
					$('#submitCandidateTable').DataTable().clear().destroy();
					$.each(jsonObj, function() {
						$('#submitCandidateTable').dataTable().fnAddData( [
							this.parAllocationId,
					        this.candidate.candidateName,
					        this.submitDate,
			
					        '<button type="button" class="btn btnSubmitCandidateEdit btn-link" id="submitCandidateTableEdit-btn">Edit</button> / <button type="button" class="btn btnSubmitCandidateDelete btn-link" id="submitCandidateTableDelete-btn">Delete</button>'
					        
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