<!-- <!DOCTYPE html> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/> 
	
	<title>External Staff</title>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<!--  Jquery CSS -->
	<link rel="stylesheet" href="static/css/jquery-ui.min.css">
	<link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="static/css/bootstrap.css">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/datatables.bootstrap4.min.css"> 
	<!-- Par Management CSS -->
	<link rel="stylesheet" href="static/css/common.css">
	<link rel="stylesheet" href="static/css/externalstaff.css">		
	
	<!-- JQuery -->
	<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="static/js/popper.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
	<script type="text/javascript" src="static/js/bootstrap.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
	<!-- Par Management Java Script -->
	<script type="text/javascript" src="static/js/common.js"></script>
	<script type="text/javascript" src="static/js/externalstaff.js"></script>
</head>
<body>
		<!-- Page Header and Menu jsp -->
		<jsp:include page="header-menu.jsp" />
	
		<div class="main">
			<div >
				<h1 class="screen-title">External Staff</h1>
			</div>

		    <div class="tablediv">
		    	<table id="externalStaffTable" class="table table-striped table-bordered" style="width:100%">
				     <thead>
				         <tr>
				             <th>ExternalStaff Id</th>
				             <th>ExternalStaff Name</th>
				             <th>Area Name</th>
				             <th>Active</th>
				             <th>Action</th>
				         </tr>
				     </thead>
				     <tbody>
				     	<c:forEach var="externalStaff" items="${allExtStaffsList}">
				     		<tr>
				     			<td>${externalStaff.extStaffId}</td>
				     			<td>${externalStaff.extStaffName}</td>
				     			<td>${externalStaff.area.areaName}</td>
				     			<td>${externalStaff.extStaffActive}</td>
				     			<td>
				     				<button type="button" class="btn btnExternalStaffEdit btn-link" id="externalStaffTableEdit-btn">Edit</button>/
				     				<button type="button" class="btn btnExternalStaffDelete btn-link" id="externalStaffTableDelete-btn">Delete</button>
				     			</td>
				     		</tr>        		
				     	</c:forEach>
				     </tbody>
				</table>
		   </div>
		   	<div>
				<button type="button" class="btn btn-primary btnExternalStaffAdd" id="externalStaffAdd-btn">Add ExternalStaff</button>
			</div>
		</div>
		<footer class="footer">
			<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
		</footer>
		
		
		<!-- Delete ExternalStaff Message Modal -->
		<div class="modal fade" id="externalStaffDeleteconfirmModal" tabindex="-1"
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
						<p id="externalStaffDeleteconfirmModalBody"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="externalStaffModalDelete-btn">Delete</button>
						<button type="button" class="btn btn-primary cancel"
							data-dismiss="modal">Cancel</button>
						<input type="hidden" id="externalStaffModalDeleteExternalStaffId"/>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Edit ExternalStaff Message Modal -->
		<div class="modal fade" id="externalStaffEditModal" tabindex="-1"
			role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="confirmModalLabel">Add / Update ExternalStaff</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form class="container" novalidate=""  id="extStaffForm">
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess1">External Staff Id</label>
							        <input type="text" class="form-control" name="externalStaffIdModal" id="inputSuccess1" required>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the external Staff id</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess2">External Staff Name</label>
							        <input type="text" class="form-control"  name="externalStaffNameModal" required id="inputSuccess2">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the external staff name</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess3">Area Name</label>
							        <select class="form-control" name="externalStaffAreaNameModal" required id="inputSuccess3">
								        <%-- <c:forEach var="area" items="${allAreasList}">
						   					<option  value="${area.areaId}" >${area.areaName}</option>
		        	   					</c:forEach> --%>
								    </select>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please select the area name</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess4">External Staff Active</label>
							        <select class="form-control" name="externalStaffActiveModal" required id="inputSuccess4">
								      <option value="Yes">Yes</option>
								      <option value="No">No</option>
								    </select>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please select the external staff active yes or no</div>
							    </div>
								 <div class="modal-footer">
									<button type="button" class="btn btn-primary" id="externalStaffModalEdit-btn">Save</button>
									<button type="button" class="btn btn-primary cancel"
											data-dismiss="modal">Cancel</button>
									<input type="hidden" id="externalStaffModalProcess"/>
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