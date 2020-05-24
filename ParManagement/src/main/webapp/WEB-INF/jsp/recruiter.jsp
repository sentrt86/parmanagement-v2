<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/> 
	
	<title>Recruiter</title>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<!--  Jquery CSS -->
	<link rel="stylesheet" href="static/css/jquery-ui.min.css">
	<link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="static/css/bootstrap.css">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/datatables.bootstrap4.min.css"> 
	
	<!-- Par Management CSS -->
	<link rel="stylesheet" href="static/css/common.css">
	<link rel="stylesheet" href="static/css/recruiter.css">		
	
	<!-- JQuery -->
	<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
	<script type="text/javascript" src="static/js/bootstrap.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
	<!-- Par Management Java Script -->
	<script type="text/javascript" src="static/js/common.js"></script>
	<script type="text/javascript" src="static/js/recruiter.js"></script>
</head>
<body>

		<!-- Page Header and Menu jsp -->
		<jsp:include page="header-menu.jsp" />
	
	
		<div class="main">
			<div >
				<h1 class="screen-title">Recruiter</h1>
			</div>
			<div class="tablediv">
				<table id="recruiterTable" class="table table-striped table-bordered" style="width:160%;">
			        <thead>
			            <tr>
			                <th style="width:10%;">Recruiter Id</th>
			                <th>Recruiter Name</th>
			                <th>Phone No.</th>
			                <th>Email</th>
			                <th style="width:10%;">Email Flag</th>
			                <th style="width:10%;">Active</th>
			                <th style="width:13%;">Action</th>
			            </tr>
			        </thead>
			        <tbody>
				     	<c:forEach var="recruiter" items="${allRecruitersList}">
				     		<tr>
				     			<td style="width:10%;">${recruiter.recruiterId}</td>
				     			<td>${recruiter.recruiterName}</td>
				     			<td>${recruiter.recruiterPhoneNo}</td>
				     			<td>${recruiter.recruiterEmail}</td>
				     			<td style="width:10%;">${recruiter.recruiterEmailFlag}</td>
				     			<td style="width:10%;">${recruiter.recruiterActive}</td>
				     			<td style="width:13%;">
				     				<button type="button" class="btn btnRecruiterEdit btn-link" id="recruiterDelete-btn">Edit</button>/
				     				<button type="button" class="btn btnRecruiterDelete btn-link" id="recruiterDelete-btn">Delete</button>
				     			</td>
				     		</tr>        		
				     	</c:forEach>
			        </tbody>
			    </table>
		    </div>
		    <div>
				<button type="button" class="btn btn-primary btnRecruiterAdd" id="recruiterAdd-btn">Add Role</button>
			</div>
		</div>		
		<footer class="footer">
			<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
		</footer>
		
		<!-- Delete Skill Message Modal -->
		<div class="modal fade" id="recruiterDeleteconfirmModal" tabindex="-1"
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
						<p id="recruiterDeleteconfirmModalBody"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="recruiterDelete-btn">Delete</button>
						<button type="button" class="btn btn-primary cancel"
							data-dismiss="modal">Cancel</button>
						<input type="hidden" id="recruiterModalDeleteSkillId"/>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Edit Skill Message Modal -->
		<div class="modal fade" id="recruiterEditModal" tabindex="-1"
			role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="confirmModalLabel">Add / Update Recruiter</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
						  <div class="form-group">
						    <label>Recruiter Id</label>
						    <input type="text" class="form-control" id="recruiterIdModal" readonly>
						  </div>
						  <div class="form-group">
						    <label>Recruiter Name</label>
						    <input type="text" class="form-control" id="recruiterNameModal">
						  </div>
						  <div class="form-group">
						    <label>Recruiter Phone</label>
						    <input type="text" class="form-control" id="recruiterPhoneNoModal">
						  </div>
						  <div class="form-group">
						    <label>Recruiter Email</label>
						    <input type="text" class="form-control" id="recruiterEmailModal">
						  </div> 
						  <div class="form-group">
						    <label>Recruiter Email Flag</label>
							    <select class="form-control" id="recruiterEmailFlagModal">
							      <option>Yes</option>
							      <option>No</option>
							    </select>
						  </div>
						  <div class="form-group">
						    <label>Recruiter Active</label>
							    <select class="form-control" id="recruiterActiveModal">
							      <option>Yes</option>
							      <option>No</option>
							    </select>
						  </div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="recruiterSave-btn">Save</button>
						<button type="button" class="btn btn-primary cancel"
							data-dismiss="modal">Cancel</button>
						<input type="hidden" id="recruiterModalProcess"/>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Message Modal -->
		<jsp:include page="Message.jsp" />
		
</body>
</html> 

