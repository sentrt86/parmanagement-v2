$(document).ready(function() {
	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});
	

		$('#sendEmail-btn').click(function(){
			var isValid = $('#parEntryForm')[0].checkValidity();

			if (!isValid) 
			{
				event.preventDefault();
				event.stopPropagation();
			}
	
			$('#parEntryForm').addClass('was-validated');
			
			if(isValid)
			{
				var parNumber = $('[name="parNo"]').val();
				var parComment= $('[name="parComment"]').val();
				var parDescriptionText = $('[name="parDescriptionText"').val();
				var parDateReceieved = $('[name="parDateReceived"').val();
				var parStatus =$('[name="activePar"').val();
				var roleName = $('[name="parRole"]').val();
				var skillName = $('[name="skillName"]').val();
				var locationName =$('[name="locationName"]').val();
				var extStaffName =$('[name="extStaffName"]').val();
				var areaName=$('[name="areaName"]').val();
				
				var parId=0;
				
				var roleActive,areaActive,locationActive,skillActive=false;
				var intentToFill ="No";
				var intentSentDate="";
				var emailSent="Yes"
				
				
				var data = '{"parId":"'+parId+'","parNumber":"'+parNumber+'","roleName":"'+roleName+'","skillName":"'+skillName+'","locationName":"'+locationName+'","parDescriptionText":"'+parDescriptionText+'","areaName":"'+areaName+'","extStaffName":"'+extStaffName+'","parReceivedDate":"'+parDateReceieved+'","intentToFill":"'+intentToFill+'","intentSentDate":"'+intentSentDate+'","emailSent":"'+emailSent+'","parComment":"'+parComment+'","parStatus":"'+parStatus+'"}';
				
				var url = "./sendEmailRecruiters";
				
				alert(data);
				$.ajax({
					type:"POST",
					dataType:"text",
					contentType: "application/json",
					url:url,
					data:data,
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
		
		
		/* To get the entered par number information*/

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
						$('[name="parComment"]').val(dataout.parComment);
						var parReceivedDate = dataout.parReceivedDate.substring(6) + '-' + 
	                    					  dataout.parReceivedDate.substring(0,2) +'-'+
	                                          dataout.parReceivedDate.substring(3,5);
	                    $('[name="parDateReceived"]').val(parReceivedDate);
	                    $('[name="parDescriptionText"]').val(dataout.parDescriptionText);
	                    $('[name="activePar"]').val(dataout.parStatus);
	                    $('[name="extStaffName"]').val(dataout.externalStaff.extStaffName);
	                    $('[name="areaName"]').val(dataout.area.areaName);
	                    $('[name="parRole"]').val(dataout.parRole.roleName);
	                    $('[name="skillName"]').val(dataout.skill.skillName);
	                    $('[name="locationName"]').val(dataout.location.locationName);
						
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
		
		$('#messageClose-btn').click(function(){
			location.reload(); 
		});
});
		