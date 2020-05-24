$(document).ready(function() {
	
	$(function () {
	    var token = $("meta[name='_csrf']").attr("content");
	    var header = $("meta[name='_csrf_header']").attr("content");
	    $(document).ajaxSend(function(e, xhr, options) {
	        xhr.setRequestHeader(header, token);
	    });
	});
	
	/*  To display the skill datatables in the skill jsp page  */
	
    $('#skillTable').DataTable({
    	"scrollY":        "300px",
        "scrollCollapse": true
    });
    
    /* To get the next skill id and display in the add pop modal */
    
    $('#skillAdd-btn').click(function(){
		$.getJSON('nextSkillId', function (data) {
			$('#skillIdModal').prop("readonly", true);
			$('#skillIdModal').val(data);
		});
		$('#skillModalProcess').val("skillAdd");
	}); 
    
    /* To display the skill delete confirmation pop modal  */
    
    $("#skillTable tbody").on('click', '.btnSkillDelete', function () {
    	var table = $("#skillTable").DataTable();
		var skill = table.row($(this).closest('tr')).data();			
		$('#skillDeleteconfirmModalBody').html("Are you sure you, want to delete skill <strong> "+skill[1]+" <strong> ?");
		$('#skillDeleteconfirmModal').modal('show');		
	    
	});
    
    /* To display the skill update  pop modal */
    
    $("#skillTable tbody").on('click', '.btnSkillEdit', function () {
    	var table = $("#skillTable").DataTable();
		var skill = table.row($(this).closest('tr')).data();	
		$('#skillIdModal').val(skill[0]);
		$('#skillNameModal').val(skill[1]);
		$('#skillActiveModal').val(skill[2]);
		$('#skillEditModal').modal('show');		
	    
	});
    
    /* Performs the functionality of adding or updating the skill informations */

	$('#skillModalEdit-btn').click(function() {
		var url;
		var skillId = $('#skillIdModal').val();
		var skillName = $('#skillNameModal').val();
		var skillActive = $('#skillActiveModal :selected').val();
		var process = $('#skillModalProcess').val();
		var data = '{"skillId":"'+skillId+'","skillName":"'+skillName+'","skillActive":"'+skillActive+'"}';

		alert(process);
		if (process == 'skillAdd')
		{
			url = "./createSkill";
		}
		else
		{
			url="./updateSkill";
		}

		alert(url);
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "application/json",
			url:url,
			data: data,
			success:function(data){
				$('#skillEditModal').modal('hide');	
				$('#messageModalBody').html(data);
				$('#messageModal').modal('show');  				
			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});

	});

	/* Reload the page after the message modal is closed */

	$('#messageClose-btn').click(function(){
		location.reload(); 
	});

	/* Performs the functionality of deleting the select skill */

	$('#skillModalDelete-btn').click(function(){
		$('#skillDeleteconfirmModal').modal('hide');
		var areaId= $('#skillModalDeleteSkillId').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteArea/"+areaId,
			data: "",
			success:function(data){
				$('#skillDeleteModal').modal('hide');	
				$('#messageModalBody').html(data);
				$('#messageModal').modal('show');  				
			},
			error:function(req, status, error)
			{
				console.log(status,error);
			}	
		});
	});

} );