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
			$('[name="candidateIdModal"]').prop("readonly", true);
			$('[name="candidateIdModal"]').val(data);
		});
		$('[name="candidateNameModal"]').val("");
		$('[name="candidatePhoneNumModal"]').val("");
		$('[name="candidateEmailTxtModal"]').val("");
		$('[name="candidateReceivedDateModal"]').val("");
		$('#candidateModalProcess').val("candidateAdd");
		$('#candidateForm').removeClass('was-validated');
		getAllSkills("");
		$('#candidateEditModal').modal('show');
	}); 


	

	/* To display the candidate delete confirmation pop modal  */

	$("#candidateTable tbody").on('click', '.btnCandidateDelete', function () {
		var table = $("#candidateTable").DataTable();
		var candidate = table.row($(this).closest('tr')).data();			
		$('#candidateDeleteconfirmModalBody').html("Are you sure you, want to delete candidate <strong> "+candidate[1]+" <strong> ?");
		$('#candidateModalDeleteCandidateId').val(candidate[0]);
		$('#candidateDeleteconfirmModal').modal('show');		

	});

	/* To display the candidate update  pop modal */

	$("#candidateTable tbody").on('click', '.btnCandidateEdit', function () {
		$('#candidateForm').removeClass('was-validated');
		var table = $("#candidateTable").DataTable();
		var candidate = table.row($(this).closest('tr')).data();	
		$('#candidateModalProcess').val("candidateEdit");
		$('[name="candidateIdModal"]').prop("readonly", true);
		$('[name="candidateIdModal"]').val(candidate[0]);
		$('[name="candidateNameModal"]').val(candidate[1]);
		$('[name="candidatePhoneNumModal"]').val(candidate[2]);
		$('[name="candidateEmailTxtModal"]').val(candidate[3]);
		
		alert(candidate[4]);
		$('[name="candidateReceivedDateModal"]').val('01-01-2020');
		getAllSkills(candidate[5]);
		$('[name="candidateActiveModal"]').val(candidate[6]);
		$('#candidateEditModal').modal('show');		

	});

	/* Performs the functionality of adding or updating the candidate informations */

	$('#candidateModalEdit-btn').click(function() {
		
		// Fetch form to apply custom Bootstrap validation
		
		var url="";
		var isValid = $('#candidateForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#candidateForm').addClass('was-validated');
		
		alert(isValid);

		if (isValid)
		{
			var candidateId = $('[name="candidateIdModal"]').val();
			var candidateName = $('[name="candidateNameModal"]').val();
			var candidatePhoneNum = $('[name="candidatePhoneNumModal"]').val();
			var candidateEmailTxt = $('[name="candidateEmailTxtModal"]').val();
			var candidateReceivedDate = $('[name="candidateReceivedDateModal"]').val();
			var skillId = $('[name="candidateSkillNameModal"]').val();
			var candidateActive = $('[name="candidateActiveModal"] :selected').val();
			var process = $('#candidateModalProcess').val();
			var data = '{"candidateId":"'+candidateId+'","candidateName":"'+candidateName+'","candidatePhoneNum":"'+candidatePhoneNum+'","candidateEmailTxt":"'+candidateEmailTxt+'","candidateReceivedDate":"'+candidateReceivedDate+'","candidateActive":"'+candidateActive+'","skillId":"'+skillId+'"}';
	
			alert(data);
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
		 }

	});

	/* Reload the page after the message modal is closed */

	$('#messageClose-btn').click(function(){
		location.reload(); 
	});

	/* Performs the functionality of deleting the select candidate */

	$('#candidateModalDelete-btn').click(function(){
		$('#candidateDeleteconfirmModal').modal('hide');
		var candidateId= $('#candidateModalDeleteCandidateId').val();
		
		alert(candidateId);
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


	/*  Function to get all skills and populate the drop down */

	function getAllSkills(skillName) {
		
		$.ajax({
			type:"GET",
			dataType:"text",
			contentType: "text/plain",
			url:"./getAllSkills",
			data: "",
			success:function(data){
				$('[name="candidateSkillNameModal"]').empty();
				var skillDropDown = $('[name="candidateSkillNameModal"]'); 
				
				var jsonObj = JSON.parse(data);
				$.each(jsonObj, function() {
					if (skillName == this.skillName){
						skillDropDown.append($("<option  selected/>").val(this.skillId).text(this.skillName));
					}
					else
					{
						skillDropDown.append($("<option />").val(this.skillId).text(this.skillName));
					}
				    
				});
			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});
		
	}


} );