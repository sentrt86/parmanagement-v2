$(document).ready(function(){
	var screen_selected_candidate_ID;
	var parCode;
	var parAllocId;
	var prescreenerId;
	var candidateName;
	var prescreeningDate;
	var prescreeningCommentText;
	
	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});
	
	/* To get the entered par number information*/
	
	$('[name="candidateName"]').change(function(){
		$('[name="parNo"]').removeAttr("disabled");
		screen_selected_candidate_ID = $('[name="candidateName"]').val();
		var data="";
		$.ajax({
			type:"GET",
			contenttype:"text",
			dataType: "text",
			url:"./getParMasterBycandidateId/"+screen_selected_candidate_ID,
			data: data,
			success:function(data){
				if(data.length > 0)
				{	
					var dataout=$.parseJSON(data);
					$.each(dataout,function(key,value){
						$('[name="parNo"]').append($('<option>').val(value.parId).text(value.parNumber));
					});
					
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

	$('[name="parNo"]').change(function(){
		
		var data="";
		var parNum = $('select[name="parNo"] option:selected').text();
		candidateName= $('[name="candidateName"]').children("option:selected").text();
		$('[name="prescreenerName"]').removeAttr("disabled");
		$('[name="preScreeningDate"]').removeAttr("disabled");
		$('[name="prescreeningCommentStatus"]').removeAttr("disabled");
		var url="./getParMasterByParNum/"+parNum;
		
		console.log(url);
		
		$.ajax({
			type:"GET",
			dataType:"text",
			contentType: "application/json",
			url:url,
			data: "",
			success:function(data){
				
				var dataout = $.parseJSON(data);
				
					  
				var parReceivedDate =     dataout.parReceivedDate.substring(0,2) +'-'+
										  dataout.parReceivedDate.substring(3,5) + '-'+
										  dataout.parReceivedDate.substring(6);
					$('[name="parDateReceived"]').val(parReceivedDate);
					$('[name="extStaffName"]').val(dataout.externalStaff.extStaffName);
					$('[name="parRole"]').val(dataout.parRole.roleName);
					$('[name="skillName"]').val(dataout.skill.skillName);
					$('[name="locationName"]').val(dataout.location.locationName);
					$('[name="areaName"]').val(dataout.area.areaName);
					$('[name="activePar"]').val(dataout.parStatus).prop('selected',true);
					$('[name="parDescriptionText"]').val(dataout.parDescriptionText);
					getParAllocationInfo(parNum);	
				

			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});


	}); 
	
		
	$('#candidateStatusSave-btn').click(function() {


		// Fetch form to apply custom Bootstrap validation
		var url="";
		var isValid = $('#candidateStatusForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#candidateStatusForm').addClass('was-validated');

		if (isValid)
		{

			parCode = $('[name="parNo"]').val();
			
			prescreenerId= $('[name="prescreenerName"]').val();
			candidateName= $('[name="candidateName"]').children("option:selected").text();
			prescreeningDate = $('[name="preScreeningDate"]').val();
			prescreeningCommentText = $('[name="prescreeningCommentStatus"]').val();
			
			var data = '{"parCode":"'+parCode+'","parAllocId":"'+parAllocId+'","candidateName":"'+candidateName+'","prescreenerId":"'+prescreenerId+'","prescreeningDate":"'+prescreeningDate+'","prescreeningCommentText":"'+prescreeningCommentText+'"}';	

			url="./updatePrescreening";
			
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
	
	function getParAllocationInfo(parNum) {	
		$.ajax({
			type:"GET",
			dataType:"text",
			contentType: "text/plain",
			url:"./getParAllocationByParNum/"+parNum,
		
			data: "",
			success:function(data){
				var jsonObj = JSON.parse(data);
				var jsonLen = jsonObj.length;
				var candidatename;
				var prescreeningDate;

				if(jsonLen > 0)
				{	
				 $.each(jsonObj,function(key,value){
					candidatename = value.candidate.candidateName;
					if(candidatename == candidateName){
						$('[name="prescreenerName"]').val(value.prescreener.preScreenerId).prop('selected',true);
						parAllocId = value.parAllocationId;
						prescreeningDate = value.prescreenerDate;
						prescreeningDate = value.prescreenerDate.substring(6) + '-'+ value.prescreenerDate.substring(0,2) + '-' +
										   value.prescreenerDate.substring(3,5);
						$('[name="preScreeningDate"]').val(prescreeningDate);
						
						if(value.prescreenerCommentText != null)
							{
							 $('[name="prescreeningCommentStatus"]').val('completed').prop('selected',true);
							}
						else {
							$('[name="prescreeningCommentStatus"]').val('Notassigned').prop('selected',true);
						}
					}
					
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
				alert("error");
				console.log("responseText:" + req.responseText);
				console.log(status,error);
			}	
		});
}
	
	$('#messageClose-btn').click(function(){
		location.reload(); 
	});
	
});