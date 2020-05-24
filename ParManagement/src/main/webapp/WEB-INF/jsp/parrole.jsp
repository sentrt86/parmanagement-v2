<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/> 
	
	<title>Par Role</title>
	
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
	<link rel="stylesheet" href="static/css/parrole.css">		
	
	<!-- JQuery -->
	<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
	<script type="text/javascript" src="static/js/bootstrap.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
	<!-- Par Management Java Script -->
	<script type="text/javascript" src="static/js/common.js"></script>
	<script type="text/javascript" src="static/js/parrole.js"></script>
</head>
<body>

		<!-- Page Header and Menu jsp -->
		<jsp:include page="header-menu.jsp" />
	
	
		<div class="main">
			<div >
				<h1 class="screen-title">Par Role</h1>
			</div>
			<div class="tablediv">
				<table id="parRoleTable" class="table table-striped table-bordered" style="width:100%">
			        <thead>
			            <tr>
			                <th>Role Id</th>
			                <th>Role Name</th>
			                <th>Role Active</th>
			                <th>Action</th>
			            </tr>
			        </thead>
			        <tbody>
				     	<c:forEach var="role" items="${allRolesList}">
				     		<tr>
				     			<td>${role.roleId}</td>
				     			<td>${role.roleName}</td>
				     			<td>${role.roleActive}</td>
				     			<td>
				     				<button type="button" class="btn btnParRoleEdit btn-link" id="parRoleDelete-btn">Edit</button>/
				     				<button type="button" class="btn btnParRoleDelete btn-link" id="parRoleDelete-btn">Delete</button>
				     			</td>
				     		</tr>        		
				     	</c:forEach>
			        </tbody>
			    </table>
		    </div>
		    <div>
				<button type="button" class="btn btn-primary btnParRoleAdd" id="parRoleAdd-btn">Add Role</button>
			</div>
		</div>		
		<footer class="footer">
			<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
		</footer>
		
		<!-- Delete Skill Message Modal -->
		<div class="modal fade" id="parRoleDeleteconfirmModal" tabindex="-1"
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
						<p id="parRoleDeleteconfirmModalBody"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="parRoleDelete-btn">Delete</button>
						<button type="button" class="btn btn-primary cancel"
							data-dismiss="modal">Cancel</button>
						<input type="hidden" id="parRoleModalDeleteSkillId"/>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Edit Skill Message Modal -->
		<div class="modal fade" id="parRoleEditModal" tabindex="-1"
			role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="confirmModalLabel">Add / Update Role</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
						  <div class="form-group">
						    <label>Role Id</label>
						    <input type="text" class="form-control" id="parRoleIdModal" readonly>
						  </div>
						  <div class="form-group">
						    <label>Role Name</label>
						    <input type="text" class="form-control" id="parRoleNameModal">
						  </div> 
						  <div class="form-group">
						    <label>Role Active</label>
							    <select class="form-control" id="parRoleActiveModal">
							      <option>Yes</option>
							      <option>No</option>
							    </select>
						  </div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="parRoleSave-btn">Save</button>
						<button type="button" class="btn btn-primary cancel"
							data-dismiss="modal">Cancel</button>
						<input type="hidden" id="parRoleModalProcess"/>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Message Modal -->
		<jsp:include page="Message.jsp" />
		
</body>
</html> 

