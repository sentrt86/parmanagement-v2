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
		
		<title>Candidate Onboard</title>
		
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
		<link rel="stylesheet" href="static/css/candidateonboard.css">	
		
		<!-- JQuery -->
		<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
		<script type="text/javascript" src="static/js/popper.min.js"></script>
	
		<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
		<script type="text/javascript" src="static/js/bootstrap.js"></script>
		<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
		<!-- Par Management Java Script -->
		<script type="text/javascript" src="static/js/common.js"></script>
		<script type="text/javascript" src="static/js/candidateonboard.js"></script>
	</head>
	<body>
			<!-- Page Header and Menu jsp -->
			<jsp:include page="header-menu.jsp" />		
			<div class="main">
				<div>
			    	<h1 class="screen-title">Candidate Onboard</h1>
			    </div>
				<form class="form-top">
				  <div class="row">
				    <div class="col col-sm-4">
				      <div class="form-inline">
				      	<label class="form-control-label" for="inputSuccess1">Par No :  </label> &nbsp;&nbsp;
				        <input type="text" class="form-control mb-6 mr-sm-2" name="parNo" id="inputSuccess1" required style="width:205px;" placeholder=" Enter Par No">
				        <div class="valid-feedback">Success!</div>
				        <div class="invalid-feedback">Please enter the par no</div>
				      </div>
				    </div>
				  </div>
				  <hr class="linebreak"></hr>
				  <div class="row mt-3 marginRow">
				    <div class="col">
				      <div class="form-group">
				      	<label class="form-control-label mr-sm-2" for="inputSuccess2">Par Received Date</label>
				      	<input type="date" class="form-control mb-6 mr-sm-2" name="parDateReceived" id="inputSuccess2" required placeholder="Date Received" readonly>
				      </div>
				    </div>
				    <div class="col">
				      <div class="form-group">
				       	 	<label class="form-control-label mr-sm-2" for="inputSuccess3">External Staff</label>
					        <input type="text" class="form-control mb-6 mr-sm-2" name="extStaffName" id="inputSuccess3"  placeholder="External Staff" readonly>
					  </div>
				    </div>
				  </div>
				  <div class="row mt-3 marginRow">
				     <div class="col">
				      <div class="form-group">
				      		<label class="form-control-label" for="inputSuccess4">Role</label>
				      		<input type="text" class="form-control mb-6 mr-sm-2" name="parRole" id="inputSuccess4"  placeholder="Role" readonly>
					  </div>
				    </div>
				    <div class="col">
				       <div class="form-group">
				       		<label class="form-control-label" for="inputSuccess5">Skill</label>
				       		<input type="text" class="form-control mb-6 mr-sm-2" name="skill" id="inputSuccess5"  placeholder="Skill" readonly>
					   </div>
				    </div>
				  </div>
				</form>
				
				<div class="tablediv">
			    	<table id="candidateOnboardTable" class="table table-striped table-bordered" style="width:100%">
					     <thead>
					         <tr>
					             <th>Candidate Name</th>
					             <th>Start Date</th>
					             <th>Started (Y/N) </th>
					             <th>Actual Start Date</th>
					             <th>Action</th>
					         </tr>
					     </thead>
					     <tbody>					     	
					     </tbody>
					</table>
			   </div>
			</div>
			<footer class="footer">
				<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
			</footer>
			
			<!-- Edit Candidate received Message Modal -->
			<div class="modal fade" id="candidateOnboardEditModal" tabindex="-1"
				role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="confirmModalLabel">Update candidateOnboard</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form class="container" novalidate=""  id="candidateonboardform">
							     <div class="form-group">
							        <label class="form-control-label" for="inputSuccess6">Candidate Name</label>
								     <input type="text" class="form-control"  name="candidateNameModal" required id="inputSuccess6" readonly>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please select the Candidate Name</div>
							        
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess7">Start Date</label>
							        <input type="date" class="form-control "name="startDateModal" required id="inputSuccess7">							
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please add the start date</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess6">Started </label>
								     <input type="text" class="form-control"  name="startedModal" required id="inputSuccess6">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the Candidate Stared Yes/No</div>
							        
							    </div>
							    
							     <div class="form-group">
							        <label class="form-control-label" for="inputSuccess8">Actual Start Date</label>
							        <input type="date"  name="actualStartdateModal" id="inputSuccess8" required>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the actual start Date</div>
							    </div>
							    
							    <div class="modal-footer">
									<button type="button" class="btn btn-primary" id="candidateOnboardModalEdit-btn">Save</button>
									<button type="button" class="btn btn-primary cancel"
											data-dismiss="modal">Cancel</button>
									<input type="hidden" id="candidateOnboardModalEditParAllocId">									
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			
			<!-- Delete candidateOnboard  Message Modal -->
			<div class="modal fade" id="candidateOnboardDeleteconfirmModal" tabindex="-1"
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
							<p id="candidateOnboardDeleteconfirmModalBody"></p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" id="candidateOnboardModalDelete-btn">Delete</button>
							<button type="button" class="btn btn-primary cancel"
								data-dismiss="modal">Cancel</button>
							<input type="hidden" id="canidateOnboardModalDeleteParAllocId"/>
						</div>
					</div>
				</div>
			</div>
			
		
			
			<!-- Message Modal -->
			<jsp:include page="Message.jsp" />
	</body>
	</html>