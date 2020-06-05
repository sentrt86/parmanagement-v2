$(document).ready(function() {

	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});

	/*  To display the area datatables in the area jsp page  */

	$('#areaTable').DataTable( {
		"scrollY":        "300px",
		"scrollCollapse": true
	});


	/* To get the next area id and display in the add pop modal */

	$('#areaAdd-btn').click(function(){
		$('#areaForm').removeClass('was-validated');
		$.getJSON('nextAreaId', function (data) {
			$('[name="areaIdModal"]').prop("readonly", true);
			$('[name="areaIdModal"]').val(data);	
		});
		$('[name="areaNameModal"]').val("");
		$('#areaModalProcess').val("areaAdd");
		$('#areaEditModal').modal('show');
	}); 

	
	/* To display the area delete confirmation pop modal  */

	$("#areaTable tbody").on('click', '.btnAreaDelete', function () {
		var table = $("#areaTable").DataTable();
		var area = table.row($(this).closest('tr')).data();			
		$('#areaDeleteconfirmModalBody').html("Are you sure you, want to delete area <strong> "+area[1]+" <strong> ?");
		$('#areaModalDeleteAreaId').val(area[0]);
		$('#areaDeleteconfirmModal').modal('show');		

	});

	/* To display the area update  pop modal */

	$("#areaTable tbody").on('click', '.btnAreaEdit', function () {
		$('#areaForm').removeClass('was-validated');
		var table = $("#areaTable").DataTable();
		var area = table.row($(this).closest('tr')).data();	
		$('#areaModalProcess').val("areaEdit");
		$('[name="areaIdModal"]').prop("readonly", true);
		$('[name="areaIdModal"]').val(area[0]);
		$('[name="areaNameModal"]').val(area[1]);
		$('[name="areaActiveModal"]').val(area[2]);
		$('#areaEditModal').modal('show');		

	});

	/* Performs the functionality of adding or updating the area informations */

	$('#areaModalEdit-btn').click(function() {


		// Fetch form to apply custom Bootstrap validation
		var url="";
		var isValid = $('#areaForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#areaForm').addClass('was-validated');

		if (isValid)
		{
			var areaId = $('[name="areaIdModal"]').val();
			var areaName = $('[name="areaNameModal"]').val();
			var areaActive = $('[name="areaActiveModal"] :selected').val();
			var process = $('#areaModalProcess').val();
			var data = '{"areaId":"'+areaId+'","areaName":"'+areaName+'","areaActive":"'+areaActive+'"}';	
		
			if (process == 'areaAdd')
			{
				url = "./createArea";
			}
			else
			{
				url="./updateArea";
			}
	
			$.ajax({
				type:"POST",
				dataType:"text",
				contentType: "application/json",
				url:url,
				data: data,
				success:function(data){
					$('#areaEditModal').modal('hide');	
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

	/* Performs the functionality of deleting the select area */

	$('#areaModalDelete-btn').click(function(){
		$('#areaDeleteconfirmModal').modal('hide');
		var areaId= $('#areaModalDeleteAreaId').val();

		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteArea/"+areaId,
			data: "",
			success:function(data){
				$('#areaDeleteModal').modal('hide');	
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