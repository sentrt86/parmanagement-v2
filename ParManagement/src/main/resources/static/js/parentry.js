$(document).ready(function() {

	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});
	
	
	/* To get the next par sequence  id and display in the par id field*/

	$('#parSeqNew-btn').click(function(){
		$('#parEntryForm').removeClass('was-validated');
		$.getJSON('getNextParSeqId', function (data) {
			$('[name="parId"]').val(data);	
		});
		$('[name="parId"]').prop("readonly", true); 	
		
	});
	
	
	/* Performs the functionality of adding the Par */

	$('#parEntrySave-btn').click(function() {


		// Fetch form to apply custom Bootstrap validation
		var isValid = $('#parEntryForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#parEntryForm').addClass('was-validated');

		alert(isValid);
		if (isValid)
		{
			var parId = $('[name="parId"]').val();
			var parNo = $('[name="parNo"]').val();
			var parDescriptionText = $('[name="parDescription"]').val();
			var parReceivedDate = $('[name="parDateReceived"]').val();	
			var roleId = $('[name="parRole"] :selected').val();
			var skillId = $('[name="skillName"] :selected').val();
			var locationId = $('[name="locationName"] :selected').val();
			var areaId     = $('[name="areaName"] :selected').val();
			var extStaffId  = $('[name="extStaffName"] :selected').val();
			var activePar  = $('[name="activePar"] :selected').val();
			var process = $('#areaModalProcess').val();
			var intentToFill = "false";
			var intentSentDate = " ";
			var emailSent = "false";
			var parComment = " ";
			var data = '{"parId":"'+parId+'","parNumber":"'+parNo+'","roleId":"'+roleId+'","skillId":"'+skillId+'","locationId":"'+locationId+'","parDescriptionText":"'+parDescriptionText+'","areaId":"'+areaId+'","extStaffId":"'+extStaffId+'","parReceivedDate":"'+parReceivedDate+'","intentToFill":"'+intentToFill+'","intentSentDate":"'+intentSentDate+'","emailSent":"'+emailSent+'","parComment":"'+parComment+'","parStatus":"'+activePar+'"}';	
		    var url = "./createParMaster";
			
			alert(data);
			
			
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


});
