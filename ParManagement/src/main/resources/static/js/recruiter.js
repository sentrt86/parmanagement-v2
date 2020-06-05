$(document).ready(function() {
	
	$(function () {
	    var token = $("meta[name='_csrf']").attr("content");
	    var header = $("meta[name='_csrf_header']").attr("content");
	    $(document).ajaxSend(function(e, xhr, options) {
	        xhr.setRequestHeader(header, token);
	    });
	});
	
	/*  To display the recruiter datatables in the recruiter jsp page  */
	
    $('#recruiterTable').DataTable({
    	"scrollY":        "300px",
    	"scrollX":        true,
        "scrollCollapse": true
    });
    
    /* To get the next recruiter id and display in the add pop modal */
    
    $('#recruiterAdd-btn').click(function(){
    	$('#recruiterForm').removeClass('was-validated');
		$.getJSON('nextRecruiterId', function (data) {
			$('[name="recruiterIdModal"]').prop("readonly", true);
			$('[name="recruiterIdModal"]').val(data);
		});
		$('#recruiterModalProcess').val("recruiterAdd");
		$('#recruiterEditModal').modal('show');
	}); 
    
    /* To display the recruiter delete confirmation pop modal  */
    
    $("#recruiterTable tbody").on('click', '.btnRecruiterDelete', function () {
    	var table = $("#recruiterTable").DataTable();
		var recruiter = table.row($(this).closest('tr')).data();			
		$('#recruiterDeleteconfirmModalBody').html("Are you sure you, want to delete recruiter <strong> "+recruiter[1]+" <strong> ?");
		$('#recruiterModalDeleteRecruiterId').val(recruiter[0]);
		$('#recruiterDeleteconfirmModal').modal('show');		
	    
	});
    
    /* To display the recruiter update  pop modal */
    
    $("#recruiterTable tbody").on('click', '.btnRecruiterEdit', function () {
    	$('#recruiterForm').removeClass('was-validated');
    	$('[name="recruiterIdModal"]').prop("readonly", true);
    	var table = $("#recruiterTable").DataTable();
		var recruiter = table.row($(this).closest('tr')).data();	
		$('[name="recruiterIdModal"]').val(recruiter[0]);
		$('[name="recruiterNameModal"]').val(recruiter[1]);
		$('[name="recruiterPhoneModal"]').val(recruiter[2]);
		$('[name="recruiterEmailModal"]').val(recruiter[3]);
		$('[name="recruiterEmailFlagModal"]').val(recruiter[4]);
		$('[name="recruiterActiveModal"]').val(recruiter[5]);
		$('#recruiterModalProcess').val("recruiterEdit");
		$('#recruiterEditModal').modal('show');		
	    
	});
    
    /* Performs the functionality of adding or updating the recruiter informations */

	$('#recruiterModalEdit-btn').click(function() {
		// Fetch form to apply custom Bootstrap validation
		var url="";
		var isValid = $('#recruiterForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#recruiterForm').addClass('was-validated');

		if (isValid)
		{
			var recruiterId = $('[name="recruiterIdModal"]').val();
			var recruiterName = $('[name="recruiterNameModal"]').val();
			var recruiterPhoneNo = $('[name="recruiterPhoneModal"]').val();
			var recruiterEmail = $('[name="recruiterEmailModal"]').val();
			var recruiterEmailFlag = $('[name="recruiterEmailFlagModal"] :selected').val();
			var recruiterActive = $('[name="recruiterActiveModal"] :selected').val();
			var process = $('#recruiterModalProcess').val();
			var data = '{"recruiterId":"'+recruiterId+'","recruiterName":"'+recruiterName+'","recruiterPhoneNo":"'+recruiterPhoneNo+'","recruiterEmail":"'+recruiterEmail+'","recruiterEmailFlag":"'+recruiterEmailFlag+'","recruiterActive":"'+recruiterActive+'"}';
	
			alert(process);
			if (process == 'recruiterAdd')
			{
				url = "./createRecruiter";
			}
			else
			{
				url="./updateRecruiter";
			}
	
			alert(url);
			$.ajax({
				type:"POST",
				dataType:"text",
				contentType: "application/json",
				url:url,
				data: data,
				success:function(data){
					$('#recruiterEditModal').modal('hide');	
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

	/* Performs the functionality of deleting the select recruiter */

	$('#recruiterModalDelete-btn').click(function(){
		$('#recruiterDeleteconfirmModal').modal('hide');
		var recruiterId= $('#recruiterModalDeleteRecruiterId').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteRecruiter/"+recruiterId,
			data: "",
			success:function(data){
				$('#recruiterDeleteModal').modal('hide');	
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