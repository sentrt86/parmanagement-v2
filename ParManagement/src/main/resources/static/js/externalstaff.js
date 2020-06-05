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
		$('#extStaffForm').removeClass('was-validated');
		$.getJSON('nextExtStaffId', function (data) {
			$('[name="externalStaffIdModal"]').prop("readonly", true);
			$('[name="externalStaffIdModal"]').val(data);
			
		});
		$('[name="externalStaffNameModal"]').val("");
		$('#externalStaffModalProcess').val("externalStaffAdd");
		getAllAreas("");
		$('#externalStaffEditModal').modal('show');
	}); 
	

	/* To display the externalStaff delete confirmation pop modal  */

	$("#externalStaffTable tbody").on('click', '.btnExternalStaffDelete', function () {
		var table = $("#externalStaffTable").DataTable();
		var externalStaff = table.row($(this).closest('tr')).data();			
		$('#externalStaffDeleteconfirmModalBody').html("Are you sure you, want to delete externalStaff <strong> "+externalStaff[1]+" <strong> ?");
		$('#externalStaffModalDeleteExternalStaffId').val(externalStaff[0]);
		$('#externalStaffDeleteconfirmModal').modal('show');		

	});

	/* To display the externalStaff update  pop modal */

	$("#externalStaffTable tbody").on('click', '.btnExternalStaffEdit', function () {
		$('#extStaffForm').removeClass('was-validated');
		var table = $("#externalStaffTable").DataTable();
		var externalStaff = table.row($(this).closest('tr')).data();	
		$('#externalStaffModalProcess').val("externalStaffEdit");
		$('[name="externalStaffIdModal"]').prop("readonly", true);
		$('[name="externalStaffIdModal"]').val(externalStaff[0]);
		$('[name="externalStaffNameModal"]').val(externalStaff[1]);
		getAllAreas(externalStaff[2]);
		$('[name="externalStaffActiveModal"]').val(externalStaff[3]);
		$('#externalStaffEditModal').modal('show');		

	});

	/* Performs the functionality of adding or updating the externalStaff informations */

	$('#externalStaffModalEdit-btn').click(function() {
		var url="";
		var isValid = $('#extStaffForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#extStaffForm').addClass('was-validated');

		if (isValid)
		{
			var externalStaffId = $('[name="externalStaffIdModal"]').val();
			var externalStaffName = $('[name="externalStaffNameModal"]').val();
			var areaId = $('[name="externalStaffAreaNameModal"]').val();			
			var externalStaffActive = $('[name="externalStaffActiveModal"]').val();
			var process = $('#externalStaffModalProcess').val();
			var data = '{"externalStaffId":"'+externalStaffId+'","externalStaffName":"'+externalStaffName+'","areaId":"'+areaId+'","externalStaffActive":"'+externalStaffActive+'"}';
	
			alert(data);
			if (process == 'externalStaffAdd')
			{
				url = "./createExtStaff";
			}
			else
			{
				url="./updateExtStaff";
			}
	
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
		}

	});

	/* Reload the page after the message modal is closed */

	$('#messageClose-btn').click(function(){
		location.reload(); 
	});

	/* Performs the functionality of deleting the selected externalStaff */

	$('#externalStaffModalDelete-btn').click(function(){
		$('#externalStaffDeleteconfirmModal').modal('hide');
		var externalStaffId= $('#externalStaffModalDeleteExternalStaffId').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteExtStaff/"+externalStaffId,
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

/*  Function to get all area and populate the drop down */

function getAllAreas(areaname) {
	
	$.ajax({
		type:"GET",
		dataType:"text",
		contentType: "text/plain",
		url:"./getAllAreas",
		data: "",
		success:function(data){
			$('[name="externalStaffAreaNameModal"]').empty();
			var areaDropDown = $('[name="externalStaffAreaNameModal"]'); 
			
			var jsonObj = JSON.parse(data);
			$.each(jsonObj, function() {
				if (areaname == this.areaName){
					areaDropDown.append($("<option  selected/>").val(this.areaId).text(this.areaName));
				}
				else
				{
					areaDropDown.append($("<option />").val(this.areaId).text(this.areaName));
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