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
		
		<title>Location</title>
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<!--  Jquery CSS -->
		<link rel="stylesheet" href="static/css/jquery-ui.min.css">
		<link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
		<link rel="stylesheet" href="static/css/bootstrap.css">
		<link rel="stylesheet" href="static/css/bootstrap.min.css">
		<link rel="stylesheet" href="static/css/datatables.bootstrap4.min.css"> 
		<!-- Par Management CSS -->
		<link rel="stylesheet" href="static/css/common.css">
		<link rel="stylesheet" href="static/css/location.css">		
		
		<!-- JQuery -->
		<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
		<script type="text/javascript" src="static/js/popper.min.js"></script>
	
		<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
		<script type="text/javascript" src="static/js/bootstrap.js"></script>
		<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
		<!-- Par Management Java Script -->
		<script type="text/javascript" src="static/js/common.js"></script>
		<script type="text/javascript" src="static/js/location.js"></script>
	</head>
	<body>
			<!-- Page Header and Menu jsp -->
			<jsp:include page="header-menu.jsp" />		
			<div class="main">
				<div >
					<h1 class="screen-title">Location</h1>
				</div>
	
			    <div class="tablediv">
			    	<table id="locationTable" class="table table-striped table-bordered" style="width:100%">
					     <thead>
					         <tr>
					             <th>Location Id</th>
					             <th>Location Name</th>
					             <th>Active</th>
					             <th>Action</th>
					         </tr>
					     </thead>
					     <tbody>
					     	<c:forEach var="location" items="${allLocationsList}">
					     		<tr>
					     			<td>${location.locationId}</td>
					     			<td>${location.locationName}</td>
					     			<td>${location.locationActive}</td>
					     			<td>
					     				<button type="button" class="btn btnLocationEdit btn-link" id="locationTableEdit-btn">Edit</button>/
					     				<button type="button" class="btn btnLocationDelete btn-link" id="locationTableDelete-btn">Delete</button>
					     			</td>
					     		</tr>        		
					     	</c:forEach>
					     </tbody>
					</table>
			   </div>
			   	<div>
					<button type="button" class="btn btn-primary btnLocationAdd" id="locationAdd-btn">Add Location</button>
				</div>
			</div>
			<footer class="footer">
				<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
			</footer>
			
			
			<!-- Delete Location Message Modal -->
			<div class="modal fade" id="locationDeleteconfirmModal" tabindex="-1"
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
							<p id="locationDeleteconfirmModalBody"></p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" id="locationModalDelete-btn">Delete</button>
							<button type="button" class="btn btn-primary cancel"
								data-dismiss="modal">Cancel</button>
							<input type="hidden" id="locationModalDeleteLocationId"/>
						</div>
					</div>
				</div>
			</div>
			
			<!-- Edit Location Message Modal -->
			<div class="modal fade" id="locationEditModal" tabindex="-1"
				role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="confirmModalLabel">Add / Update Location</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form class="container" novalidate=""  id="locationForm">
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess1">Location Id</label>
							        <input type="text" class="form-control" name="locationIdModal" id="inputSuccess1" required>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the location id</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess2">Location Name</label>
							        <input type="text" class="form-control"  name="locationNameModal" required id="inputSuccess2">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the location name</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess2">Location Active</label>
							        <select class="form-control" name="locationActiveModal" required id="inputSuccess3">
								      <option value="Yes">Yes</option>
								      <option value="No">No</option>
								    </select>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please select the location active yes or no</div>
							    </div>
								 <div class="modal-footer">
									<button type="button" class="btn btn-primary" id="locationModalEdit-btn">Save</button>
									<button type="button" class="btn btn-primary cancel"
											data-dismiss="modal">Cancel</button>
									<input type="hidden" id="locationModalProcess"/>
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