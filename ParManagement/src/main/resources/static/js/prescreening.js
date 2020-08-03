$(document).ready(function() {
	
	/*  To display the area datatables in the area jsp page  */

	$('#prescreeningTable').DataTable( {
		"scrollY":        "300px",
		"scrollX":        true,
		"scrollCollapse": true
	});
	
	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});
	
	/* To display the prescreening update  pop modal */

	$("#prescreeningTable tbody").on('click', '.btnPrescreeningEdit', function () {
		$('#prescreeningForm').removeClass('was-validated');
		var table = $("#prescreeningTable").DataTable();
		var prescreening = table.row($(this).closest('tr')).data();
		var tempPrescreeningDate = prescreening[3];
		

		$('#prescreeningModalEditParAllocId').val(prescreening[0]);		
		$('[name="candidateNameModal"]').val(prescreening[1]);
		
		getActivePrescreeners(prescreening[2]);
		
		if(tempPrescreeningDate != null)
		{ 
			var PrescreeningDate = tempPrescreeningDate.substring(6) + '-' + 
							 tempPrescreeningDate.substring(0,2) +'-'+
                             tempPrescreeningDate.substring(3,5);
			$('[name="prescreeningDateModal"]').val(PrescreeningDate);
		}
		$('#prescreeningEditModal').modal('show');		

	});
	
	/* To display the candidate submit delete confirmation pop modal  */

	$("#prescreeningTable tbody").on('click', '.btnPrescreeningDelete', function () {
		var table = $("#prescreeningTable").DataTable();
		var prescreening = table.row($(this).closest('tr')).data();			
		$('#prescreeningDeleteconfirmModalBody').html("Are you sure, you want to delete the candidate from this par : <strong> "+prescreening[1]+" <strong> ?");
		$('#prescreeningModalDeleteParAllocId').val(prescreening[0]);
		$('#prescreeningDeleteconfirmModal').modal('show');		

	});
	
	$('#messageClose-btn').click(function(){
		var parNum = $('[name="parNo"]').val();
		getParAllocationInfo(parNum)
	});
	
	/* Performs the functionality of deleting the select candidate */

	$('#prescreeningModalDelete-btn').click(function(){
		$('#prescreeningDeleteconfirmModal').modal('hide');
		var parAllocId= $('#prescreeningModalDeleteParAllocId').val();
	
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteParAllocationByParAllocId/"+parAllocId,
			data: "",
			success:function(data){
				$('#prescreeningDeleteModal').modal('hide');	
				$('#messageModalBody').html(data);
				$('#messageModal').modal('show');  				
			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});
	});
	
	/* Performs the functionality of adding or updating the submit candidate informations */

	$('#prescreeningModalEdit-btn').click(function() {


		// Fetch form to apply custom Bootstrap validation
		var url="";
		var isValid = $('#prescreeningForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#prescreeningForm').addClass('was-validated');

		if (isValid)
		{

			var parCode = $('[name="parId"]').val();
			var parAllocId = $('#prescreeningModalEditParAllocId').val();
			var prescreenerId= $('[name="prescreenerNameModal"]').val();
			var candidateName= $('[name="candidateNameModal"]').val();
			var prescreeningDate = $('[name="prescreeningDateModal"]').val();
			var prescreeningCommentText = $('[name="prescreeningCommentModal"]').val();
			var process = $('#prescreeningModalProcess').val();
			var data = '{"parCode":"'+parCode+'","parAllocId":"'+parAllocId+'","candidateName":"'+candidateName+'","prescreenerId":"'+prescreenerId+'","prescreeningDate":"'+prescreeningDate+'","prescreeningCommentText":"'+prescreeningCommentText+'"}';	

			url="./updatePrescreening";
			
			$.ajax({
				type:"POST",
				dataType:"text",
				contentType: "application/json",
				url:url,
				data: data,
				success:function(data){
					$('#prescreeningEditModal').modal('hide');	
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
					var parReceivedDate = dataout.parReceivedDate.substring(6) + '-' + 
                    					  dataout.parReceivedDate.substring(0,2) +'-'+
                                          dataout.parReceivedDate.substring(3,5);
                    $('[name="parDateReceived"]').val(parReceivedDate);
                    $('[name="extStaffName"]').val(dataout.externalStaff.extStaffName);
                    $('[name="parRole"]').val(dataout.parRole.roleName);
                    $('[name="skill"]').val(dataout.skill.skillName);
					
					getParAllocationInfo(parNum);	
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
	
	/*  Function to get all active prescreener and populate the drop down */

	function getActivePrescreeners(prescreenerName) {
		
		$.ajax({
			type:"GET",
			dataType:"text",
			contentType: "text/plain",
			url:"./getActivePrescreeners",
			data: "",
			success:function(data){
				$('[name="prescreenerNameModal"]').empty();
				var prescreenerDropDown = $('[name="prescreenerNameModal"]'); 
				
				var jsonObj = JSON.parse(data);
				$.each(jsonObj, function() {
					
					if (prescreenerName == this.preScreenerName){
						prescreenerDropDown.append($("<option selected/>").val(this.preScreenerId).text(this.preScreenerName));
					}
					else
					{
						prescreenerDropDown.append($("<option />").val(this.preScreenerId).text(this.preScreenerName));
						
					}
											
				    
				});
			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});
		
	}

	/*  Function to get all the par allocation  information */

	function getParAllocationInfo(parNum) {
		
		$.ajax({
			type:"GET",
			dataType:"text",
			contentType: "text/plain",
			url:"./getParAllocationByParNum/"+parNum,
			data: "",
			success:function(data){
				$('#prescreeningTable').dataTable().fnClearTable();
				var jsonObj = JSON.parse(data);
				var jsonLen = jsonObj.length;
				if(jsonLen > 0 )
				{
					$('#prescreeningTable').DataTable().clear().destroy();
					
					$('#prescreeningTable').DataTable( {
						"scrollY":        "300px",
						"scrollX":        true,
						"scrollCollapse": true
					});
					
					$.each(jsonObj, function() {
						$('#prescreeningTable').dataTable().fnAddData( [
							this.parAllocationId,
					        this.candidate.candidateName,
					        this.prescreener.preScreenerName,
					        this.prescreenerDate,
					        this.prescreenerCommentText,
					   
			
					        '<button type="button" class="btn btnPrescreeningEdit btn-link" id="prescreeningTableEdit-btn">Edit</button> / <button type="button" class="btn btnPrescreeningDelete btn-link" id="prescreeningTableDelete-btn">Delete</button>'
					        
					      ]
						
						);
					    
					});
					
				}

			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});
		
	}
	

});