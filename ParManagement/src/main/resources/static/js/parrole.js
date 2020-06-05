$(document).ready(function() {
	
	$(function () {
	    var token = $("meta[name='_csrf']").attr("content");
	    var header = $("meta[name='_csrf_header']").attr("content");
	    $(document).ajaxSend(function(e, xhr, options) {
	        xhr.setRequestHeader(header, token);
	    });
	});
	
	/*  To display the par role datatables in the par role jsp page  */
	
    $('#parRoleTable').DataTable({
    	"scrollY":        "300px",
        "scrollCollapse": true
    });
    
    /* To get the next par role id and display in the add pop modal */
    
    $('#parRoleAdd-btn').click(function(){
    	$('#parRoleForm').removeClass('was-validated');
		$.getJSON('nextParRoleId', function (data) {
			$('[name="parRoleIdModal"]').prop("readonly", true);
			$('[name="parRoleIdModal"]').val(data);
		});
		$('[name="parRoleNameModal"]').val("")
		$('#parRoleModalProcess').val("parRoleAdd");
		$('#parRoleEditModal').modal('show');
	}); 
    
    /* To display the par role delete confirmation pop modal  */
    
    $("#parRoleTable tbody").on('click', '.btnParRoleDelete', function () {
    	var table = $("#parRoleTable").DataTable();
		var parRole = table.row($(this).closest('tr')).data();			
		$('#parRoleDeleteconfirmModalBody').html("Are you sure you, want to delete par role <strong> "+parRole[1]+" <strong> ?");
		$('#parRoleModalDeleteParRoleId').val(parRole[0]);
		$('#parRoleDeleteconfirmModal').modal('show');		
	    
	});
    
    /* To display the par role update  pop modal */
    
    $("#parRoleTable tbody").on('click', '.btnParRoleEdit', function () {
    	$('#parRoleForm').removeClass('was-validated');
    	var table = $("#parRoleTable").DataTable();
		var parRole = table.row($(this).closest('tr')).data();
		$('#parRoleModalProcess').val("parRoleEdit");
		$('[name="parRoleIdModal"]').prop("readonly", true);
		$('[name="parRoleIdModal"]').val(parRole[0]);
		$('[name="parRoleNameModal"]').val(parRole[1]);
		$('[name="parRoleActiveModal"]').val(parRole[2]);
		$('#parRoleEditModal').modal('show');		
	    
	});
    
    /* Performs the functionality of adding or updating the skill informations */

	$('#parRoleModalEdit-btn').click(function() {
		// Fetch form to apply custom Bootstrap validation
		var url="";
		var isValid = $('#parRoleForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#parRoleForm').addClass('was-validated');

		if (isValid)
		{
			var parRoleId = $('[name="parRoleIdModal"]').val();
			var parRoleName = $('[name="parRoleNameModal"]').val();
			var parRoleActive = $('[name="parRoleActiveModal"] :selected').val();
			var process = $('#parRoleModalProcess').val();
			var data = '{"roleId":"'+parRoleId+'","roleName":"'+parRoleName+'","roleActive":"'+parRoleActive+'"}';
	
			alert(data);
			alert(process);
			if (process == 'parRoleAdd')
			{
				url = "./createParRole";
			}
			else
			{
				url="./updateParRole";
			}
	
			alert(url);
			$.ajax({
				type:"POST",
				dataType:"text",
				contentType: "application/json",
				url:url,
				data: data,
				success:function(data){
					$('#parRoleEditModal').modal('hide');	
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

	/* Performs the functionality of deleting the select skill */

	$('#parRoleModalDelete-btn').click(function(){
		$('#parRoleDeleteconfirmModal').modal('hide');
		var parRoleId= $('#parRoleModalDeleteParRoleId').val();

		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteParRole/"+parRoleId,
			data: "",
			success:function(data){
				$('#parRoleDeleteModal').modal('hide');	
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