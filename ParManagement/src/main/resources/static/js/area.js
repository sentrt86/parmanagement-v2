$(document).ready(function() {
	
    $('#areaTable').DataTable( {
    	"scrollY":        "300px",
        "scrollCollapse": true
    });
	

    $("#areaAdd-btn").click(function(){
    	$("#areaIdModal").prop("readonly", false);
    	$("#areaIdModal").val(" ");
    	$("#areaNameModal").val(" ");
    	$('#areaEditModal').modal('show');	
    	
    	
    });
    
    $("#areaTable tbody").on('click', '.btnAreaDelete', function () {
    	var table = $("#areaTable").DataTable();
		var area = table.row($(this).closest('tr')).data();			
		$('#areaDeleteconfirmModalBody').html("Are you sure you, want to delete area <strong> "+area[1]+" <strong> ?");
		$('#areaDeleteconfirmModal').modal('show');		
	    
	});
    
    $("#areaTable tbody").on('click', '.btnAreaEdit', function () {
    	var table = $("#areaTable").DataTable();
		var area = table.row($(this).closest('tr')).data();	
		$("#areaIdModal").prop("readonly", true);
		$('#areaIdModal').val(area[0]);
		$('#areaNameModal').val(area[1]);
		$('#areaActiveModal').val(area[2]);
		$('#areaEditModal').modal('show');		
	    
	});
    
    $('#areaSave-btn').click(function() {
        var areaName = $('#areaNameModal').val();
        
        alert(areaName.length)
        // Check if there is an entered value
        if(areaName.length == 0) {
          // Add errors highlight
        	alert('error');
          $('#areaNameModal').closest('.form-group').removeClass('has-success').addClass('has-error');
        } else {
          // Remove the errors highlight
          $('#areaNameModal').closest('.form-group').removeClass('has-error').addClass('has-success');
        }
      });
/*    
      $('#areaAdd-btn').click(function(){
		
    	  alert('hi');
		var data = '{"tournamentYear":"'+tournamentyear+'", "eventCode":"'+eventCode+'"}';
    	var data=" ";
		var url = "http://localhost:8081/par/area/getNextAreaId"	
		
		$.ajax({
			type:"GET",
			dataType:"jsonp",
			contentType: "application/json",
			url:url,
			data:data,
			success:function(data)
			{

					alert ('Data got  successfully:'+ data);
					
					$("#myModalbadmintonevent .close").click()
	
			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}
			
		});
			
      }); */
    
} );