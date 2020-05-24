$(document).ready(function() {

	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});

	/*  To display the externalStaff datatables in the externalStaff jsp page  */

	$('#externalStaffTable').DataTable( {
		"scrollY":        "300px",
		"scrollCollapse": true
	});


	/* To get the next externalStaff id and display in the add pop modal */

	$('#externalStaffAdd-btn').click(function(){
		$.getJSON('nextExternalStaffId', function (data) {
			$('#externalStaffIdModal').prop("readonly", true);
			$('#externalStaffIdModal').val(data);
			$("#externalStaffNameModal").val(" ");
		});
		$('#externalStaffModalProcess').val("externalStaffAdd");
		$('#externalStaffEditModal').modal('show');
	}); 


	

	/* To display the externalStaff delete confirmation pop modal  */

	$("#externalStaffTable tbody").on('click', '.btnExternalStaffDelete', function () {
		var table = $("#externalStaffTable").DataTable();
		var externalStaff = table.row($(this).closest('tr')).data();			
		$('#externalStaffDeleteconfirmModalBody').html("Are you sure you, want to delete externalStaff <strong> "+externalStaff[1]+" <strong> ?");
		$('#externalStaffModalDeleteExternalStaffid').val(externalStaff[0]);
		$('#externalStaffDeleteconfirmModal').modal('show');		

	});

	/* To display the externalStaff update  pop modal */

	$("#externalStaffTable tbody").on('click', '.btnExternalStaffEdit', function () {
		var table = $("#externalStaffTable").DataTable();
		var externalStaff = table.row($(this).closest('tr')).data();	
		alert(externalStaff[2]);
		$('#externalStaffModalProcess').val("externalStaffEdit");
		$('#externalStaffIdModal').prop("readonly", true);
		$('#externalStaffIdModal').val(externalStaff[0]);
		$('#externalStaffNameModal').val(externalStaff[1]);
		$('#externalStaffAreaModal').val(externalStaff[2]).attr("selected", "selected");;
		$('#externalStaffActiveModal').val(externalStaff[3]);
		$('#externalStaffEditModal').modal('show');		

	});

	/* Performs the functionality of adding or updating the externalStaff informations */

	$('#externalStaffModalEdit-btn').click(function() {
		var url;
		var externalStaffId = $('#externalStaffIdModal').val();
		var externalStaffName = $('#externalStaffNameModal').val();
		var externalStaffActive = $('#externalStaffActiveModal :selected').val();
		var process = $('#externalStaffModalProcess').val();
		var data = '{"externalStaffId":"'+externalStaffId+'","externalStaffName":"'+externalStaffName+'","externalStaffActive":"'+externalStaffActive+'"}';

		alert(process);
		if (process == 'externalStaffAdd')
		{
			url = "./createExternalStaff";
		}
		else
		{
			url="./updateExternalStaff";
		}

		alert(url);
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "application/json",
			url:url,
			data: data,
			success:function(data){
				$('#externalStaffEditModal').modal('hide');	
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

	/* Performs the functionality of deleting the select externalStaff */

	$('#externalStaffModalDelete-btn').click(function(){
		$('#externalStaffDeleteconfirmModal').modal('hide');
		var externalStaffId= $('#externalStaffModalDeleteExternalStaffId').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteExternalStaff/"+externalStaffId,
			data: "",
			success:function(data){
				$('#externalStaffDeleteModal').modal('hide');	
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