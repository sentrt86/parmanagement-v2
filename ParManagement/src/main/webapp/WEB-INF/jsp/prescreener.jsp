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
		
		<title>Prescreener</title>
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<!--  Jquery CSS -->
		<link rel="stylesheet" href="static/css/jquery-ui.min.css">
		<link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
		<link rel="stylesheet" href="static/css/bootstrap.css">
		<link rel="stylesheet" href="static/css/bootstrap.min.css">
		<link rel="stylesheet" href="static/css/datatables.bootstrap4.min.css"> 
		<!-- Par Management CSS -->
		<link rel="stylesheet" href="static/css/common.css">
		<link rel="stylesheet" href="static/css/prescreener.css">		
		
		<!-- JQuery -->
		<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
		<script type="text/javascript" src="static/js/popper.min.js"></script>
	
		<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
		<script type="text/javascript" src="static/js/bootstrap.js"></script>
		<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
		<!-- Par Management Java Script -->
		<script type="text/javascript" src="static/js/common.js"></script>
		<script type="text/javascript" src="static/js/prescreener.js"></script>
	</head>
	<body>
			<!-- Page Header and Menu jsp -->
			<jsp:include page="header-menu.jsp" />		
			<div class="main">
				<div >
					<h1 class="screen-title">PreScreener</h1>
				</div>
	
			    <div class="tablediv">
			    	<table id="prescreenerTable" class="table table-striped table-bordered" style="width:160%">
					     <thead>
					         <tr>
					             <th>Prescreener Id</th>
					             <th>Prescreener Name</th>
					             <th>Prescreener Email</th>
					             <th>Prescreener Phone</th>
					             <th>Active</th>
					             <th>Action</th>
					         </tr>
					     </thead>
					     <tbody>
					     	<c:forEach var="prescreener" items="${allPrescreenersList}">
					     		<tr>
					     			<td>${prescreener.preScreenerId}</td>
					     			<td>${prescreener.preScreenerName}</td>
					     			<td>${prescreener.preScreenerEmailId}</td>
					     			<td>${prescreener.preScreenercontactNo}</td>
					     			<td>${prescreener.preScreenerActive}</td>
					     			<td>
					     				<button type="button" class="btn btnPrescreenerEdit btn-link" id="prescreenerTableEdit-btn">Edit</button>/
					     				<button type="button" class="btn btnPrescreenerDelete btn-link" id="prescreenerTableDelete-btn">Delete</button>
					     			</td>
					     		</tr>        		
					     	</c:forEach>
					     </tbody>
					</table>
			   </div>
			   	<div>
					<button type="button" class="btn btn-primary btnPrescreenerAdd" id="prescreenerAdd-btn">Add Prescreener</button>
				</div>
			</div>
			<footer class="footer">
				<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
			</footer>
			
			
			<!-- Delete Prescreener Message Modal -->
			<div class="modal fade" id="prescreenerDeleteconfirmModal" tabindex="-1"
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
							<p id="prescreenerDeleteconfirmModalBody"></p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" id="prescreenerModalDelete-btn">Delete</button>
							<button type="button" class="btn btn-primary cancel"
								data-dismiss="modal">Cancel</button>
							<input type="hidden" id="prescreenerModalDeletePrescreenerId"/>
						</div>
					</div>
				</div>
			</div>
			
			<!-- Edit Prescreener Message Modal -->
			<div class="modal fade" id="prescreenerEditModal" tabindex="-1"
				role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="confirmModalLabel">Add / Update Prescreener</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form class="container" novalidate=""  id="prescreenerForm">
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess1">Prescreener Id</label>
							        <input type="text" class="form-control" name="prescreenerIdModal" id="inputSuccess1" required>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the prescreener id</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess2">Prescreener Name</label>
							        <input type="text" class="form-control"  name="prescreenerNameModal" required id="inputSuccess2">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the prescreener name</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess3">Prescreener Email Id</label>
							        <input type="text" class="form-control"  name="prescreenerEmailTxtModal" required id="inputSuccess3">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the prescreener name</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess4">Prescreener Phone</label>
							        <input type="text" class="form-control"  name="prescreenerPhoneModal" required id="inputSuccess4">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the prescreener Phone</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess5">Prescreener Active</label>
							        <select class="form-control" name="prescreenerActiveModal" required id="inputSuccess5">
								      <option value="Yes">Yes</option>
								      <option value="No">No</option>
								    </select>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please select the prescreener active yes or no</div>
							    </div>
								 <div class="modal-footer">
									<button type="button" class="btn btn-primary" id="prescreenerModalEdit-btn">Save</button>
									<button type="button" class="btn btn-primary cancel"
											data-dismiss="modal">Cancel</button>
									<input type="hidden" id="prescreenerModalProcess"/>
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