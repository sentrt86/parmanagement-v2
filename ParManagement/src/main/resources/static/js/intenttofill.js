$(document).ready(function() {

	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});



	/* To get the entered par number*/

	$('[name="parNo"]').change(function(){
		
		var data="";
		var parNum = $('[name="parNo"]').val();
		
		$.ajax({
			type:"GET",
			dataType:"text",
			contentType: "application/json",
			url:"./getParMasterByParNum/"+parNum,
			data: data,
			success:function(data){
				

				if(data.length > 0)
				{
					var dataout=$.parseJSON(data);
					$('[name="parId"]').val(dataout.parId);
					$('[name="parNo"]').val(dataout.parNumber);
					var parReceivedDate = dataout.parReceivedDate.substring(6) + '-' + 
					                      dataout.parReceivedDate.substring(0,2) +'-'+
					                      dataout.parReceivedDate.substring(3,5);
					$('[name="parDateReceived"]').val(parReceivedDate);
					$('[name="extStaffName"]').val(dataout.externalStaff.extStaffName);
					
					if(dataout.intentToFill == 'true')
					{
						$('[name="intentToFill"]').val("Yes")
					}
					
					if(dataout.intentToFill == 'false')
					{
						$('[name="intentToFill"]').val("No")
					}
					
					if (dataout.intentSentDate != null)
					{
						var intentSentDate = dataout.intentSentDate.substring(6) + '-' + 
                                             dataout.intentSentDate.substring(0,2) +'-'+
                                             dataout.intentSentDate.substring(3,5);
	
						$('[name="intentSentDate"]').val(intentSentDate);
					}
					
						
				}
				else
				{
					$('#messageModalBody').html("Data not found for the Par Number : "+parNum);
					$('#messageModal').modal('show');
				}
				
			},
			error:function(req, status, error)
			{
				console.log("responseText:" + req.responseText);
				console.log(status,error);
			}	
		});
	}); 

	
	
	/* Performs the functionality updating the par master informations */

	$('#intentToFillSave-btn').click(function() {


		// Fetch form to apply custom Bootstrap validation

		var isValid = $('#intentToFill-Form')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#intentToFill-Form').addClass('was-validated');

		if (isValid)
		{
			var parId = $('[name="parId"]').val();
			var parNumber = $('[name="parNo"]').val();
			var intentToFill = $('[name="intentToFill"]').val();
			var intentSentDate = $('[name="intentSentDate"]').val();
			
			var data = '{"parId":"'+parId+'","intentToFill":"'+intentToFill+'","parNumber":"'+parNumber+'","intentSentDate":"'+intentSentDate+'"}';	
		
			
			var url="./updateIntentToFill";
	
	
			$.ajax({
				type:"POST",
				dataType:"text",
				contentType: "application/json",
				url:url,
				data: data,
				success:function(data){
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

	
	

} );