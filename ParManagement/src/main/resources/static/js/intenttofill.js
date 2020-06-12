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
			contentType: "text/plain",
			url:"./getParMasterByParNum/"+parNum,
			data: data,
			success:function(data){
				var jsonObj = JSON.parse(data);
				var jsonLen = jsonObj.length;
				
				if(jsonLen > 0)
				{
					
					$.each(jsonObj, function() {
						$('[name="parId"]').val(this.parId);
						$('[name="parNo"]').val(this.parNumber);
						var parReceivedDate = this.parReceivedDate.substring(6) + '-' + 
						                      this.parReceivedDate.substring(0,2) +'-'+
						                      this.parReceivedDate.substring(3,5);
						$('[name="parDateReceived"]').val(parReceivedDate);
						$('[name="extStaffName"]').val(this.externalStaff.extStaffName);

						alert(this.intentToFill);
						
						if(this.intentToFill == 'true')
						{
							$('[name="intentToFill"]').val("Yes")
						}
						
						if(this.intentToFill == 'false')
						{
							$('[name="intentToFill"]').val("No")
						}
						
						var intentSentDate = this.intentSentDate.substring(6) + '-' + 
	                                         this.intentSentDate.substring(0,2) +'-'+
	                                         this.intentSentDate.substring(3,5);
						
						$('[name="intentSentDate"]').val(intentSentDate);
						
					});	
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