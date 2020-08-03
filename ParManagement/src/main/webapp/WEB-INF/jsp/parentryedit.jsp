	<!-- <!DOCTYPE html> -->
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
	<%@ page session="false" %>
	<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="_csrf" content="${_csrf.token}"/>
	    <meta name="_csrf_header" content="${_csrf.headerName}"/> 
		
		<title>Par Entry Edit</title>
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<!--  Jquery CSS -->
		<link rel="stylesheet" href="static/css/jquery-ui.min.css">
		<link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
		<link rel="stylesheet" href="static/css/bootstrap.css">
		<link rel="stylesheet" href="static/css/bootstrap.min.css">
		<link rel="stylesheet" href="static/css/datatables.bootstrap4.min.css"> 
		<!-- Par Management CSS -->
		<link rel="stylesheet" href="static/css/common.css">
		<link rel="stylesheet" href="static/css/parfullfillmentcommon.css">
		<link rel="stylesheet" href="static/css/parentry.css">		
		
		<!-- JQuery -->
		<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
		<script type="text/javascript" src="static/js/popper.min.js"></script>
	
		<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
		<script type="text/javascript" src="static/js/bootstrap.js"></script>
		<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
		<!-- Par Management Java Script -->
		<script type="text/javascript" src="static/js/common.js"></script>
		<script type="text/javascript" src="static/js/parentryedit.js"></script>
	</head>
	<body>
			<!-- Page Header and Menu jsp -->
			<jsp:include page="header-menu.jsp" />		
			<div class="main">
				<div>
			    	<h1 class="screen-title">PAR Edit Screen</h1>
			    </div>
				<form class="form-top">
				  <div class="row">
				    <div class="col col-sm-4">
				      <div class="form-inline">
				      	<label class="form-control-label" for="inputSuccess1">Par No :  </label> &nbsp;&nbsp;
				        <input type="text" class="form-control mb-6 mr-sm-2" name="parNo" id="inputSuccess1" required  style="width:205px;" placeholder="Par No">
				        <div class="valid-feedback">Success!</div>
				        <div class="invalid-feedback">Please enter the par no</div>
				      </div>
				    </div>
				  </div>
				 </form>

				 <hr class="linebreak"></hr>
				 <form class="form-top" id="parEntryForm"> 
				  <div class="row mt-3 marginRow">
				    <div class="col-sm">
				      <div class="form-group">
				      		<label class="form-control-label mr-sm-2" for="inputSuccess3">Role</label>
					        <select class="form-control  mb-6 mr-sm-2" name="parRole" required id="inputSuccess3" style="width:225px;">
					            <option value="" disabled selected>Select the Role</option>
						        <c:forEach var="role" items="${allParRolesList}">
				   					<option  value="${role.roleId}" >${role.roleName}</option>
	       	   					</c:forEach>
						    </select>
					        <div class="valid-feedback">Success!</div>
					        <div class="invalid-feedback">Please select the role</div>
					  </div>
				    </div>
				    <div class="col-sm">
				       <div class="form-group">
				       		<label class="form-control-label mr-sm-2" for="inputSuccess4">Skill</label>
					        <select class="form-control mb-6 mr-sm-2" name="skillName" required id="inputSuccess4" style="width:230px;">
					            <option value="" disabled selected>Select the Skill</option>
						        <c:forEach var="skill" items="${allSkillsList}">
				   					<option  value="${skill.skillId}" >${skill.skillName}</option>
	       	   					</c:forEach>
						    </select>
					        <div class="valid-feedback">Success!</div>
					        <div class="invalid-feedback">Please select the skill</div>
					   </div>
				    </div>
				    <div class="col-sm" >
				       <div class="form-group">
				       		<label class="form-control-label mr-sm-2" for="inputSuccess5">Location</label>
					        <select class="form-control mb-6 mr-sm-2" name="locationName" required id="inputSuccess5" style="width:230px;">
					            <option value="" disabled selected>Select the Location</option>
						        <c:forEach var="location" items="${allLocationsList}">
				   					<option  value="${location.locationId}" >${location.locationName}</option>
	       	   					</c:forEach>
						    </select>
					        <div class="valid-feedback">Success!</div>
					        <div class="invalid-feedback">Please select the skill</div>
					   </div>
				    </div>
				  </div>				  
				  <div class="row mt-3 marginRow">
				    <div class="col">
				      <div class="form-group">
				          <label class="form-control-label" for="inputSuccess6" >Description</label>
						  <textarea class="form-control" rows="5" name="parDescriptionText" required id="inputSuccess6" placeholder="Enter Description"></textarea>
						  <div class="valid-feedback">Success!</div>
					        <div class="invalid-feedback">Please enter the description</div>
					  </div>
				    </div>
				  </div>
				  <div class="row mt-3 marginRow">
				  <div class="col">
				      <div class="form-group">
				        	<label class="form-control-label mr-sm-2" for="inputSuccess5">Area</label>
					        <select class="form-control mb-6 mr-sm-2" name="areaName" required id="inputSuccess5">
					            <option value="" disabled selected>Select the Area</option>
						        <c:forEach var="area" items="${allAreasList}">
				   					<option  value="${area.areaId}" >${area.areaName}</option>
	       	   					</c:forEach>
						    </select>
					        <div class="valid-feedback">Success!</div>
					        <div class="invalid-feedback">Please select the area</div>
					  </div>
				    </div>
				  </div>
				  <div class="row mt-3 marginRow">
				  	<div class="col">
				      <div class="form-group">
				       	 	<label class="form-control-label mr-sm-2" for="inputSuccess7" style="font-family:Roboto; font-weight:100;">External Staff</label>
					        <select class="form-control mb-6 mr-sm-2" name="extStaffName" required id="inputSuccess7">
					           <option value="" disabled selected>Select the External Staff</option>
						        <c:forEach var="extStaff" items="${allExtStaffsList}">
				   					<option  value="${extStaff.extStaffId}" >${extStaff.extStaffName}</option>
	       	   					</c:forEach>
						    </select>
					        <div class="valid-feedback">Success!</div>
					        <div class="invalid-feedback">Please select the area</div>
					  </div>
				    </div>
				    <div class="col">
				      <div class="form-group">
				      	<label class="form-control-label mr-sm-2" for="inputSuccess8">Par Received Date</label>
				      	<input type="date" class="form-control mb-6 mr-sm-2" name="parDateReceived" id="inputSuccess8" required placeholder="Enter Date Received">
				        <div class="valid-feedback">Success!</div>
				       	<div class="invalid-feedback">Please enter the par received date</div>
				      </div>
				    </div>
				    <div class="col">
					  <div class="form-group">
					    <label class="form-control-label mr-sm-2" for="inputSuccess9">Active Par (Y/N)</label>
				        <select class="form-control mb-6 mr-sm-2" name="activePar" required id="inputSuccess9">
				          <option value="" disabled selected>Select the Active Par (Y/N)</option>
					      <option value="Yes">Yes</option>
					      <option value="No">No</option>
					    </select>
				        <div class="valid-feedback">Success!</div>
				        <div class="invalid-feedback">Please select the area active yes or no</div>
				    </div>
				    </div>
				  </div>
				  <div class="row  mt-3 marginRow">
				   	  <div class="col">
				   	   	<button type="button" class="btn btn-primary" id="parEntrySave-btn">Save</button>
				   	   	<button type="button" class="btn btn-primary" id="parEntryDelete-btn">Delete</button>
				   	   	<input type="hidden" id="parId" name="parId">
				   	  </div>
				  		
				  </div>
				</form>
				
			</div>
			<footer class="footer">
				<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
			</footer>
			
			
			<!-- Par Master Message Modal -->
			<div class="modal fade" id="parMasterDeleteconfirmModal" tabindex="-1"
				role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="confirmModalLabel">Delete
								Confirmation</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<p id="parMasterDeleteconfirmModalBody"></p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" id="parMasterModalDelete-btn">Delete</button>
							<button type="button" class="btn btn-primary cancel"
								data-dismiss="modal">Cancel</button>
						</div>
					</div>
				</div>
			</div>
			
		    <!-- Message Modal -->
			<jsp:include page="Message.jsp" />
	</body>
	</html>