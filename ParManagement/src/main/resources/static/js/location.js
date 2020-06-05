$(document).ready(function() {

	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});

	/*  To display the location datatables in the location jsp page  */

	$('#locationTable').DataTable( {
		"scrollY":        "300px",
		"scrollCollapse": true
	});


	/* To get the next location id and display in the add pop modal */

	$('#locationAdd-btn').click(function(){
		$('#locationForm').removeClass('was-validated');
		$.getJSON('getNextLocationId', function (data) {
			$('[name="locationIdModal"]').prop("readonly", true);
			$('[name="locationIdModal"]').val(data);	
		});
		$('[name="locationNameModal"]').val("");
		$('#locationModalProcess').val("locationAdd");
		$('#locationEditModal').modal('show');
	}); 

	
	/* To display the location delete confirmation pop modal  */

	$("#locationTable tbody").on('click', '.btnLocationDelete', function () {
		var table = $("#locationTable").DataTable();
		var location = table.row($(this).closest('tr')).data();			
		$('#locationDeleteconfirmModalBody').html("Are you sure you, want to delete location <strong> "+location[1]+" <strong> ?");
		$('#locationModalDeleteLocationId').val(location[0]);
		$('#locationDeleteconfirmModal').modal('show');		

	});

	/* To display the location update  pop modal */

	$("#locationTable tbody").on('click', '.btnLocationEdit', function () {
		$('#locationForm').removeClass('was-validated');
		var table = $("#locationTable").DataTable();
		var location = table.row($(this).closest('tr')).data();	
		$('#locationModalProcess').val("locationEdit");
		$('[name="locationIdModal"]').prop("readonly", true);
		$('[name="locationIdModal"]').val(location[0]);
		$('[name="locationNameModal"]').val(location[1]);
		$('[name="locationActiveModal"]').val(location[2]);
		$('#locationEditModal').modal('show');		

	});

	/* Performs the functionality of adding or updating the location informations */

	$('#locationModalEdit-btn').click(function() {


		// Fetch form to apply custom Bootstrap validation
		var url="";
		var isValid = $('#locationForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#locationForm').addClass('was-validated');

		if (isValid)
		{
			var locationId = $('[name="locationIdModal"]').val();
			var locationName = $('[name="locationNameModal"]').val();
			var locationActive = $('[name="locationActiveModal"] :selected').val();
			var process = $('#locationModalProcess').val();
			var data = '{"locationId":"'+locationId+'","locationName":"'+locationName+'","locationActive":"'+locationActive+'"}';	

			if (process == 'locationAdd')
			{
				url = "./createLocation";
			}
			else
			{
				url="./updateLocation";
			}
	
			$.ajax({
				type:"POST",
				dataType:"text",
				contentType: "application/json",
				url:url,
				data: data,
				success:function(data){
					$('#locationEditModal').modal('hide');	
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

	/* Performs the functionality of deleting the select location */

	$('#locationModalDelete-btn').click(function(){
		$('#locationDeleteconfirmModal').modal('hide');
		var locationId= $('#locationModalDeleteLocationId').val();

		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteLocation/"+locationId,
			data: "",
			success:function(data){
				$('#locationDeleteModal').modal('hide');	
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