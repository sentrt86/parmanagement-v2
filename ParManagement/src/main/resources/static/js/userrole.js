$(document).ready(function() {
	
	
	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});

	/*  To display the userRole datatables in the userRole jsp page  */

	$('#userRoleTable').DataTable( {
		"scrollY":        "300px",
		"scrollCollapse": true
	});


	/* To get the next userRole id and display in the add pop modal */

	$('#userRoleAdd-btn').click(function(){
		$('#userRoleForm').removeClass('was-validated');
		$("#userRoleIdModal").val("");
		$.getJSON('nextUserRoleId', function (data) {
			$('[name="userRoleIdModal"]').prop("readonly", true);
			$('[name="userRoleIdModal"]').val(data);

		});
		$('[name="userRoleNameModal"]').val("");
		$('#userRoleModalProcess').val("userRoleAdd");		
		$('#userRoleEditModal').modal('show');
	}); 


	/* To display the userRole delete confirmation pop modal  */

	$("#userRoleTable tbody").on('click', '.btnUserRoleDelete', function () {
		var table = $("#userRoleTable").DataTable();
		var userRole = table.row($(this).closest('tr')).data();			
		$('#userRoleDeleteconfirmModalBody').html("Are you sure you, want to delete User Role : <strong> "+userRole[1]+" <strong> ?");
		$('#userRoleModalDeleteUserRoleId').val(userRole[0]);
		$('#userRoleDeleteconfirmModal').modal('show');		

	});

	/* To display the userRole update  pop modal */

	$("#userRoleTable tbody").on('click', '.btnUserRoleEdit', function () {
		
		var table = $("#userRoleTable").DataTable();
		var userRole = table.row($(this).closest('tr')).data();
		$('#userRoleForm').removeClass('was-validated');
		$('#userRoleModalProcess').val("userRoleEdit");
		$('[name="userRoleIdModal"]').prop("readonly", true);
		$('[name="userRoleIdModal"]').val(userRole[0]);
		$('[name="userRoleNameModal"]').val(userRole[1]);
		$('#userRoleEditModal').modal('show');		

	});

	/* Performs the functionality of adding or updating the userRole informations */

	$('#userRoleModalEdit-btn').click(function(event) {

		// Fetch form to apply custom Bootstrap validation
		var url="";
		var isValid = $('#userRoleForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#userRoleForm').addClass('was-validated');

		if (isValid)
		{	   
			var userRoleId = $('[name="userRoleIdModal"]').val();
			var userRoleName = $('[name="userRoleNameModal"]').val();
			var process = $('#userRoleModalProcess').val();
			var data = '{"userRoleId":"'+userRoleId+'","userRoleName":"'+userRoleName+'"}';

			if (process == 'userRoleAdd')
			{
				url = "./createUserRole";
			}
			else
			{
				url="./updateUserRole";
			}

			$.ajax({
				type:"POST",
				dataType:"text",
				contentType: "application/json",
				url:url,
				data: data,
				success:function(data){
					$('#userRoleEditModal').modal('hide');	
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

	/* Reload the page after the message modal is closed */

	$('#messageClose-btn').click(function(){
		location.reload(); 
	});

	/* Performs the functionality of deleting the selected userRole */

	$('#userRoleModalDelete-btn').click(function(){
		$('#userRoleDeleteconfirmModal').modal('hide');
		var userRoleId= $('#userRoleModalDeleteUserRoleId').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteUserRole/"+userRoleId,
			data: "",
			success:function(data){
				$('#userRoleDeleteModal').modal('hide');	
				$('#messageModalBody').html(data);
				$('#messageModal').modal('show');  				
			},
			error:function(req, status, error)
			{
				console.log(req.responseText);
				console.log(status,error);
			}	
		});
	});





} );