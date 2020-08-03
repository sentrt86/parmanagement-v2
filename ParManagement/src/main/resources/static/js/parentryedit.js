$(document).ready(function() {
	
	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
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
					var parReceivedDate = dataout.parReceivedDate.substring(6) + '-' + 
                    					  dataout.parReceivedDate.substring(0,2) +'-'+
                                          dataout.parReceivedDate.substring(3,5);
                    $('[name="parDateReceived"]').val(parReceivedDate);
                    $('[name="extStaffName"]').val(dataout.externalStaff.extStaffId).prop('selected',true);
                    $('[name="parRole"]').val(dataout.parRole.roleId).prop('selected',true);
                    $('[name="skillName"]').val(dataout.skill.skillId).prop('selected',true);
                    $('[name="locationName"]').val(dataout.location.locationId).prop('selected',true);
                    $('[name="areaName"]').val(dataout.area.areaId).prop('selected',true);
                    $('[name="activePar"]').val(dataout.parStatus).prop('selected',true);
                    $('[name="parDescriptionText"]').val(dataout.parDescriptionText);
						
				}
				else
				{
					$('#messageModalBody').html("Data not found for the Par Number : "+parNum);
					$('#messageModal').modal('show');
				}
				
			},
			error:function(req, status, error)
			{
				alert("error");
				console.log("responseText:" + req.responseText);
				console.log(status,error);
			}	
		});
	}); 
	
	$('#parEntrySave-btn').click(function(){
		var isValid = $('#parEntryForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#parEntryForm').addClass('was-validated');
		
		if(isValid)
		{ 
			var parId = $('[name="parId"]').val();
			var parNumber = $('[name="parNo"]').val();
			var parDescriptionText = $('[name="parDescriptionText"').val();
			var parDateReceieved = $('[name="parDateReceived"').val();
			var parStatus =$('[name="activePar"').val();
			var roleId = $('[name="parRole"]').val();
			var skillId = $('[name="skillName"]').val();
			var locationId =$('[name="locationName"]').val();
			var extStaffId =$('[name="extStaffName"]').val();
			var areaId=$('[name="areaName"]').val();
			
			
			var roleActive,areaActive,locationActive,skillActive=false;
			var intentToFill,emailSent = "No";
			var intentSentDate,parComment="";
			
			var data = '{"parId":"'+parId+'","parNumber":"'+parNumber+'","roleId":"'+roleId+'","skillId":"'+skillId+'","locationId":"'+locationId+'","parDescriptionText":"'+parDescriptionText+'","areaId":"'+areaId+'","extStaffId":"'+extStaffId+'","parReceivedDate":"'+parDateReceieved+'","intentToFill":"'+intentToFill+'","intentSentDate":"'+intentSentDate+'","emailSent":"'+emailSent+'","parComment":"'+parComment+'","parStatus":"'+parStatus+'"}';
			
			var url = "./updateParMaster";
			
			
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
	
	
	$('#parEntryDelete-btn').click(function(){
		var parNo = $('[name="parNo"]').val();
		$('#parMasterDeleteconfirmModalBody').html("Are you sure you, want to delete the Par  : <strong>  " + parNo + " <strong> ?");
		$('#parMasterDeleteconfirmModal').modal('show');	
		
	});
	
	$('#parMasterModalDelete-btn').click(function(){
		var parNo= $('[name="parNo"]').val();
		var url = "./deleteParMaster/"+parNo;
		
				$.ajax({
					type:"POST",
					dataType:"text",
					contentType: "text",
					url:url,
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
			
			
	});
	
	$('#messageClose-btn').click(function(){
		location.reload(); 
	});
	
});