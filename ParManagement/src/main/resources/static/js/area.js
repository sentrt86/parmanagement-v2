$(document).ready(function() {
	
	$('#areaNameModal').closest('.form-group').removeClass('has-error');
	$('#areaNameModal').closest('.form-group').removeClass('has-success');
	
    $('#areaTable').DataTable( {
    	"scrollY":        "300px",
        "scrollCollapse": true
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
		$('#areaIdModal').val(area[0]);
		$('#areaNameModal').val(area[1]);
		$('#areaActiveModal').val(area[2]);
		$('#areaEditModal').modal('show');		
	    
	});
    
    $('#areaSave-btn').click(function(e) {
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
    
} );