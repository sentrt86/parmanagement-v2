$(document).ready(function() {

	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});

	/*  To display the area datatables in the area jsp page  */

	$('#userTable').DataTable( {
		"scrollY":        "300px",
		"scrollX":        true,
		"scrollCollapse": true
	});
	
	
	/* To get the next user id and display in the add pop modal */

	$('#userAdd-btn').click(function(){
		$.getJSON('nextUserId', function (data) {
			$('[name="userIdModal"]').prop("readonly", true);
			$('[name="userIdModal"]').val(data);
		});
		$('[name="userFirstNameModal"]').val("");
		$('[name="userLastNameModal"]').val("");
		$('[name="userPhoneNumModal"]').val("");
		$('[name="userEmailTxtModal"]').val("");
		$('[name="userUserNameModal"]').val("");
		$('[name="userPasswordModal"]').val("");
		$('#userModalProcess').val("userAdd");
		$('#userForm').removeClass('was-validated');
		getAllUserRoles("");
		$('#userEditModal').modal('show');
	}); 


	

	/* To display the user delete confirmation pop modal  */

	$("#userTable tbody").on('click', '.btnUserDelete', function () {
		var table = $("#userTable").DataTable();
		var user = table.row($(this).closest('tr')).data();			
		$('#userDeleteconfirmModalBody').html("Are you sure you, want to delete user <strong> "+user[1]+" <strong> ?");
		$('#userModalDeleteUserId').val(user[0]);
		$('#userDeleteconfirmModal').modal('show');		

	});

	/* To display the user update  pop modal */

	$("#userTable tbody").on('click', '.btnUserEdit', function () {
		$('#userForm').removeClass('was-validated');
		var table = $("#userTable").DataTable();
		var user = table.row($(this).closest('tr')).data();	
		$('#userModalProcess').val("userEdit");
		$('[name="userIdModal"]').prop("readonly", true);
		$('[name="userIdModal"]').val(user[0]);
		$('[name="userFirstNameModal"]').val(user[1]);
		$('[name="userLastNameModal"]').val(user[2]);
		$('[name="userPhoneNumModal"]').val(user[3]);
		$('[name="userEmailTxtModal"]').val(user[4]);
		$('[name="userUserNameModal"]').val(user[5]);
		$('[name="userPasswordModal"]').val(user[6]);		
		getAllUserRoles(user[7]);
		$('[name="userActiveModal"]').val(user[8]);
		$('#userEditModal').modal('show');		

	});
	
	/* Performs the functionality of adding or updating the user informations */

	$('#userModalEdit-btn').click(function() {
		
		// Fetch form to apply custom Bootstrap validation
		
		var url="";
		var isValid = $('#userForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#userForm').addClass('was-validated');
		

		if (isValid)
		{
			var userId = $('[name="userIdModal"]').val();
			var userFirstName = $('[name="userFirstNameModal"]').val();
			var userLastName = $('[name="userLastNameModal"]').val();
			var userPhoneNum = $('[name="userPhoneNumModal"]').val();
			var userEmailTxt = $('[name="userEmailTxtModal"]').val();
			var userRoleId = $('[name="userRoleNameModal"]').val();
			var userActive = $('[name="userActiveModal"] :selected').val();
			var userUserName =  $('[name="userUserNameModal"]').val();
			var userPassword =  $('[name="userPasswordModal"]').val();								
			var process = $('#userModalProcess').val();
			var data = '{"userId":"'+userId+'","userFirstName":"'+userFirstName+'","userLastName":"'+userLastName+'","userPhoneNum":"'+userPhoneNum+'","userEmailTxt":"'+userEmailTxt+'","userActive":"'+userActive+'","username":"'+userUserName+'","password":"'+userPassword+'","userRoleId":"'+userRoleId+'"}';
	

			
			if (process == 'userAdd')
			{
				url = "./createUser";
			}
			else
			{
				url="./updateUser";
			}
	
			$.ajax({
				type:"POST",
				dataType:"text",
				contentType: "application/json",
				url:url,
				data: data,
				success:function(data){
					$('#userEditModal').modal('hide');	
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

	/* Performs the functionality of deleting the select user */

	$('#userModalDelete-btn').click(function(){
		$('#userDeleteconfirmModal').modal('hide');
		var userId= $('#userModalDeleteUserId').val();
		
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteUser/"+userId,
			data: "",
			success:function(data){
				$('#userDeleteModal').modal('hide');	
				$('#messageModalBody').html(data);
				$('#messageModal').modal('show');  				
			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});
	});

	
	/*  Function to get all roles and populate the drop down */

	function getAllUserRoles(roleName) {
		alert('hi');
		$.ajax({
			type:"GET",
			dataType:"text",
			contentType: "text/plain",
			url:"./getAllUserRoles",
			data: "",
			success:function(data){
				
				alert(data);
				$('[name="userRoleNameModal"]').empty();
				var roleDropDown = $('[name="userRoleNameModal"]'); 
				
				var jsonObj = JSON.parse(data);
				$.each(jsonObj, function() {
					if (roleName == this.userRoleName){
						roleDropDown.append($("<option  selected/>").val(this.userRoleId).text(this.userRoleName));
					}
					else
					{
						roleDropDown.append($("<option />").val(this.userRoleId).text(this.userRoleName));
					}
				    
				});
			},
			error:function(req, status, error)
			{
				alert('error');
				console.log(status,error);
			}	
		});
		
	}

});