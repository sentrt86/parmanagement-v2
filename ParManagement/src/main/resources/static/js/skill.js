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
    	$('#skillForm').removeClass('was-validated');
		$.getJSON('nextSkillId', function (data) {
			$('[name="skillIdModal"]').prop("readonly", true);
			$('[name="skillIdModal"]').val(data);
		});
		$('[name="skillNameModal"]').val("");
		$('#skillModalProcess').val("skillAdd");
		$('#skillEditModal').modal('show');
	}); 
    
    /* To display the skill delete confirmation pop modal  */
    
    $("#skillTable tbody").on('click', '.btnSkillDelete', function () {
    	var table = $("#skillTable").DataTable();
		var skill = table.row($(this).closest('tr')).data();			
		$('#skillDeleteconfirmModalBody').html("Are you sure you, want to delete skill <strong> "+skill[1]+" <strong> ?");
		$('#skillModalDeleteSkillId').val(skill[0]);
		$('#skillDeleteconfirmModal').modal('show');		
	    
	});
    
    /* To display the skill update  pop modal */
    
    $("#skillTable tbody").on('click', '.btnSkillEdit', function () {
    	$('#skillForm').removeClass('was-validated');
    	var table = $("#skillTable").DataTable();
		var skill = table.row($(this).closest('tr')).data();
		$('[name="skillIdModal"]').prop("readonly", true);
		$('[name="skillIdModal"]').val(skill[0]);
		$('[name="skillNameModal"]').val(skill[1]);
		$('[name="skillActiveModal"]').val(skill[2]);
		$('#skillModalProcess').val("skillEdit");
		$('#skillEditModal').modal('show');		
	    
	});
    
    /* Performs the functionality of adding or updating the skill informations */

	$('#skillModalEdit-btn').click(function() {

		// Fetch form to apply custom Bootstrap validation
		var url="";
		var isValid = $('#skillForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#skillForm').addClass('was-validated');

		if (isValid)
		{
			var skillId = $('[name="skillIdModal"]').val();
			var skillName = $('[name="skillNameModal"]').val();
			var skillActive = $('[name="skillActiveModal"] :selected').val();
			var process = $('#skillModalProcess').val();
			var data = '{"skillId":"'+skillId+'","skillName":"'+skillName+'","skillActive":"'+skillActive+'"}';

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
		}

	});

	/* Reload the page after the message modal is closed */

	$('#messageClose-btn').click(function(){
		location.reload(); 
	});

	/* Performs the functionality of deleting the select skill */

	$('#skillModalDelete-btn').click(function(){
		$('#skillDeleteconfirmModal').modal('hide');
		var skillId= $('#skillModalDeleteSkillId').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteSkill/"+skillId,
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