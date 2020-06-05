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
		
		<title>Recruiter</title>
		
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
		<script type="text/javascript" src="static/js/popper.min.js"></script>
	
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
					<button type="button" class="btn btn-primary btnRecruiterAdd" id="recruiterAdd-btn">Add Recruiter</button>
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
						<button type="button" class="btn btn-primary" id="recruiterModalDelete-btn">Delete</button>
						<button type="button" class="btn btn-primary cancel"
							data-dismiss="modal">Cancel</button>
						<input type="hidden" id="recruiterModalDeleteRecruiterId"/>
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
						<form class="container" novalidate=""  id="recruiterForm">
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess1">Recruiter Id</label>
							        <input type="text" class="form-control" name="recruiterIdModal" id="inputSuccess1" required>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the recruiter id</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess2">Recruiter Name</label>
							        <input type="text" class="form-control"  name="recruiterNameModal" required id="inputSuccess2">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the recruiter name</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess3">Recruiter Phone</label>
							        <input type="text" class="form-control"  name="recruiterPhoneModal" required id="inputSuccess3">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the recruiter phone</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess4">Recruiter Email</label>
							        <input type="text" class="form-control"  name="recruiterEmailModal" required id="inputSuccess4">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the recruiter email</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess5">Recruiter Email Flag</label>
							        <select class="form-control" name="recruiterEmailFlagModal" required id="inputSuccess5">
								      <option value="Yes">Yes</option>
								      <option value="No">No</option>
								    </select>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please select the recruiter email flag yes or no</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess6">Recruiter Active</label>
							        <select class="form-control" name="recruiterActiveModal" required id="inputSuccess6">
								      <option value="Yes">Yes</option>
								      <option value="No">No</option>
								    </select>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please select the area active yes or no</div>
							    </div>
								 <div class="modal-footer">
									<button type="button" class="btn btn-primary" id="recruiterModalEdit-btn">Save</button>
									<button type="button" class="btn btn-primary cancel"
											data-dismiss="modal">Cancel</button>
									<input type="hidden" id="recruiterModalProcess"/>
								</div>
							</form>
						</div>
					</div>
				</div>
		</div>
			
			
			<!-- Message Modal -->
			<jsp:include page="Message.jsp" />
	</body>
	</html>