$(document).ready(function() {
	
	$(function () {
	    var token = $("meta[name='_csrf']").attr("content");
	    var header = $("meta[name='_csrf_header']").attr("content");
	    $(document).ajaxSend(function(e, xhr, options) {
	        xhr.setRequestHeader(header, token);
	    });
	});
	
	/*  To display the prescreener datatables in the prescreener jsp page  */
	
    $('#prescreenerTable').DataTable({
    	"scrollY":        "300px",
    	"scrollX":        true,
        "scrollCollapse": true
    });
    
    /* To get the next prescreener id and display in the add pop modal */
    
    $('#prescreenerAdd-btn').click(function(){
    	$('#prescreenerForm').removeClass('was-validated');
		$.getJSON('getNextPrescreenerId', function (data) {
			$('[name="prescreenerIdModal"]').prop("readonly", true);
			$('[name="prescreenerIdModal"]').val(data);
		});
		$('#prescreenerModalProcess').val("prescreenerAdd");
		$('#prescreenerEditModal').modal('show');
	}); 
    
    /* To display the prescreener delete confirmation pop modal  */
    
    $("#prescreenerTable tbody").on('click', '.btnPrescreenerDelete', function () {
    	var table = $("#prescreenerTable").DataTable();
		var prescreener = table.row($(this).closest('tr')).data();			
		$('#prescreenerDeleteconfirmModalBody').html("Are you sure you, want to delete prescreener <strong> "+prescreener[1]+" <strong> ?");
		$('#prescreenerModalDeletePrescreenerId').val(prescreener[0]);
		$('#prescreenerDeleteconfirmModal').modal('show');		
	    
	});
    
    /* To display the prescreener update  pop modal */
    
    $("#prescreenerTable tbody").on('click', '.btnPrescreenerEdit', function () {
    	$('#prescreenerForm').removeClass('was-validated');
    	$('[name="prescreenerIdModal"]').prop("readonly", true);
    	var table = $("#prescreenerTable").DataTable();
		var prescreener = table.row($(this).closest('tr')).data();	
		$('[name="prescreenerIdModal"]').val(prescreener[0]);
		$('[name="prescreenerNameModal"]').val(prescreener[1]);
		$('[name="prescreenerPhoneModal"]').val(prescreener[3]);
		$('[name="prescreenerEmailTxtModal"]').val(prescreener[2]);
		$('[name="prescreenerActiveModal"]').val(prescreener[4]);
		$('#prescreenerModalProcess').val("prescreenerEdit");
		$('#prescreenerEditModal').modal('show');		
	    
	});
    
    /* Performs the functionality of adding or updating the prescreener informations */

	$('#prescreenerModalEdit-btn').click(function() {
		// Fetch form to apply custom Bootstrap validation
		var url="";
		var isValid = $('#prescreenerForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#prescreenerForm').addClass('was-validated');

		if (isValid)
		{
			var prescreenerId = $('[name="prescreenerIdModal"]').val();
			var prescreenerName = $('[name="prescreenerNameModal"]').val();
			var prescreenerPhoneNo = $('[name="prescreenerPhoneModal"]').val();
			var prescreenerEmailTxt = $('[name="prescreenerEmailTxtModal"]').val();
			var prescreenerActive = $('[name="prescreenerActiveModal"] :selected').val();
			var process = $('#prescreenerModalProcess').val();
			var data = '{"preScreenerId":"'+prescreenerId+'","preScreenerName":"'+prescreenerName+'","preScreenercontactNo":"'+prescreenerPhoneNo+'","preScreenerEmailId":"'+prescreenerEmailTxt+'","preScreenerActive":"'+prescreenerActive+'"}';

			if (process == 'prescreenerAdd')
			{
				url = "./createPrescreener";
			}
			else
			{
				url="./updatePrescreener";
			}
	
			alert(url);
			$.ajax({
				type:"POST",
				dataType:"text",
				contentType: "application/json",
				url:url,
				data: data,
				success:function(data){
					$('#prescreenerEditModal').modal('hide');	
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

	/* Performs the functionality of deleting the select prescreener */

	$('#prescreenerModalDelete-btn').click(function(){
		$('#prescreenerDeleteconfirmModal').modal('hide');
		var prescreenerId= $('#prescreenerModalDeletePrescreenerId').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deletePrescreener/"+prescreenerId,
			data: "",
			success:function(data){
				$('#prescreenerDeleteModal').modal('hide');	
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