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
		$.getJSON('nextAreaId', function (data) {
			$('#areaIdModal').prop("readonly", true);
			$('#areaIdModal').val(data);
			$("#areaNameModal").val(" ");
		});
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
		var table = $("#areaTable").DataTable();
		var area = table.row($(this).closest('tr')).data();	
		$('#areaModalProcess').val("areaEdit");
		$('#areaIdModal').prop("readonly", true);
		$('#areaIdModal').val(area[0]);
		$('#areaNameModal').val(area[1]);
		$('#areaActiveModal').val(area[2]);
		$('#areaEditModal').modal('show');		

	});

	/* Performs the functionality of adding or updating the area informations */

	$('#areaModalEdit-btn').click(function() {
		var url;
		var areaId = $('#areaIdModal').val();
		var areaName = $('#areaNameModal').val();
		var areaActive = $('#areaActiveModal :selected').val();
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