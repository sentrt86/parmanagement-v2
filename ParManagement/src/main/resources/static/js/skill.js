$(document).ready(function() {
    $('#skillTable').DataTable({
    	"scrollY":        "300px",
        "scrollCollapse": true
    });
    
    $("#skillTable tbody").on('click', '.btnSkillDelete', function () {
    	var table = $("#skillTable").DataTable();
		var area = table.row($(this).closest('tr')).data();			
		$('#skillDeleteconfirmModalBody').html("Are you sure you, want to delete area <strong> "+area[1]+" <strong> ?");
		$('#skillDeleteconfirmModal').modal('show');		
	    
	});
    
    $("#skillTable tbody").on('click', '.btnSkillEdit', function () {
    	var table = $("#areaTable").DataTable();
		var area = table.row($(this).closest('tr')).data();	
		$('#skillIdModal').val(area[0]);
		$('#skillNameModal').val(area[1]);
		$('#skillActiveModal').val(area[2]);
		$('#skillEditModal').modal('show');		
	    
	});
} );