$(document).ready(function() {

	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});

	/*  To display the candidate datatables in the candidate jsp page  */

	$('#candidateTable').DataTable( {
		"scrollY":        "300px",
		"scrollX":        true,
		"scrollCollapse": true
	});


	/* To get the next candidate id and display in the add pop modal */

	$('#candidateAdd-btn').click(function(){
		$.getJSON('nextCandidateId', function (data) {
			$('#candidateIdModal').prop("readonly", true);
			$('#candidateIdModal').val(data);
			$("#candidateNameModal").val(" ");
		});
		$('#candidateModalProcess').val("candidateAdd");
		$('#candidateEditModal').modal('show');
	}); 


	

	/* To display the candidate delete confirmation pop modal  */

	$("#candidateTable tbody").on('click', '.btnCandidateDelete', function () {
		var table = $("#candidateTable").DataTable();
		var candidate = table.row($(this).closest('tr')).data();			
		$('#candidateDeleteconfirmModalBody').html("Are you sure you, want to delete candidate <strong> "+candidate[1]+" <strong> ?");
		$('#candidateModalDeleteCandidateid').val(candidate[0]);
		$('#candidateDeleteconfirmModal').modal('show');		

	});

	/* To display the candidate update  pop modal */

	$("#candidateTable tbody").on('click', '.btnCandidateEdit', function () {
		var table = $("#candidateTable").DataTable();
		var candidate = table.row($(this).closest('tr')).data();	
		alert(candidate[2]);
		$('#candidateModalProcess').val("candidateEdit");
		$('#candidateIdModal').prop("readonly", true);
		$('#candidateIdModal').val(candidate[0]);
		$('#candidateNameModal').val(candidate[1]);
		$('#candidatePhoneModal').val(candidate[2]);
		$('#candidateEmailModal').val(candidate[3]);
		$('#candidateSkillModal').val(candidate[4]).attr("selected", "selected");;
		$('#candidateActiveModal').val(candidate[5]);
		$('#candidateEditModal').modal('show');		

	});

	/* Performs the functionality of adding or updating the candidate informations */

	$('#candidateModalEdit-btn').click(function() {
		var url;
		var candidateId = $('#candidateIdModal').val();
		var candidateName = $('#candidateNameModal').val();
		var candidateActive = $('#candidateActiveModal :selected').val();
		var process = $('#candidateModalProcess').val();
		var data = '{"candidateId":"'+candidateId+'","candidateName":"'+candidateName+'","candidateActive":"'+candidateActive+'"}';

		alert(process);
		if (process == 'candidateAdd')
		{
			url = "./createCandidate";
		}
		else
		{
			url="./updateCandidate";
		}

		alert(url);
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "application/json",
			url:url,
			data: data,
			success:function(data){
				$('#candidateEditModal').modal('hide');	
				$('#messageModalBody').html(data);
				$('#messageModal').modal('show');  				
			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});

	});

	/* Reload the page after the message modal is closed */

	$('#messageClose-btn').click(function(){
		location.reload(); 
	});

	/* Performs the functionality of deleting the select candidate */

	$('#candidateModalDelete-btn').click(function(){
		$('#candidateDeleteconfirmModal').modal('hide');
		var candidateId= $('#candidateModalDeleteCandidateId').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteCandidate/"+candidateId,
			data: "",
			success:function(data){
				$('#candidateDeleteModal').modal('hide');	
				$('#messageModalBody').html(data);
				$('#messageModal').modal('show');  				
			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});
	});





} );