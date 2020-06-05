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
	
	<title>User Master</title>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<!--  Jquery CSS -->
	<link rel="stylesheet" href="static/css/jquery-ui.min.css">
	<link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="static/css/bootstrap.css">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/datatables.bootstrap4.min.css"> 
	<!-- Par Management CSS -->
	<link rel="stylesheet" href="static/css/common.css">
	<link rel="stylesheet" href="static/css/user.css">		
	
	<!-- JQuery -->
	<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="static/js/popper.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
	<script type="text/javascript" src="static/js/bootstrap.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
	<!-- Par Management Java Script -->
	<script type="text/javascript" src="static/js/common.js"></script>
	<script type="text/javascript" src="static/js/user.js"></script>
</head>
<body>
		<!-- Page Header and Menu jsp -->
		<jsp:include page="header-menu.jsp" />
	
		<div class="main">
			<div >
				<h1 class="screen-title">User</h1>
			</div>

		    <div class="tablediv">
		    	<table id="userTable" class="table table-striped table-bordered" style="width:250%">
				     <thead>
				         <tr>
				             <th>User Id</th>
				             <th>First Name</th>
				             <th>Last Name</th>
				             <th>Email</th>
				             <th>Phone No</th>
				             <th>Username</th>
				             <th>Password</th>
				             <th>Role</th>
				             <th>Active</th>
				             <th>Action</th>
				         </tr>
				     </thead>
				     <tbody>
				     	<c:forEach var="user" items="${allUserMastersList}">
				     		<tr>
				     			<td>${user.userId}</td>
				     			<td>${user.userFirstName}</td>
				     			<td>${user.userLastName}</td>
				     			<td>${user.userEmailTxt}</td>
				     			<td>${user.userPhoneNo}</td>
				     			<td>${user.userName}</td>
				     			<td>${user.password}</td>
				     			<td>${user.userRole.userRoleName}</td>
				     			<td>${user.userActive}</td>
				     			<td>
				     				<button type="button" class="btn btnUserEdit btn-link" id="userTableEdit-btn">Edit</button>/
				     				<button type="button" class="btn btnUserDelete btn-link" id="userTableDelete-btn">Delete</button>
				     			</td>
				     		</tr>        		
				     	</c:forEach>
				     </tbody>
				</table>
		   </div>
		   	<div>
				<button type="button" class="btn btn-primary btnUserAdd" id="userAdd-btn">Add User</button>
			</div>
		</div>
		<footer class="footer">
			<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
		</footer>
		
		
		<!-- Delete User Message Modal -->
		<div class="modal fade" id="userDeleteconfirmModal" tabindex="-1"
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
						<p id="userDeleteconfirmModalBody"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="userModalDelete-btn">Delete</button>
						<button type="button" class="btn btn-primary cancel"
							data-dismiss="modal">Cancel</button>
						<input type="hidden" id="userModalDeleteUserId"/>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Edit User Message Modal -->
		<div class="modal fade" id="userEditModal" tabindex="-1"
			role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="confirmModalLabel">Add / Update User</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form class="container" novalidate=""  id="userForm">
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess1">User Id</label>
							        <input type="text" class="form-control" name="userIdModal" id="inputSuccess1" required>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the user id</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess2">First Name</label>
							        <input type="text" class="form-control"  name="userFirstNameModal" required id="inputSuccess2">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the user first name</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess3">Last Name</label>
							        <input type="text" class="form-control"  name="userLastNameModal" required id="inputSuccess3">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the user Last name</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess4">Email</label>
							         <input type="text" class="form-control"  name="userEmailTxtModal" required id="inputSuccess4">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the user email</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess5">Phone No</label>
							         <input type="text" class="form-control"  name="userPhoneNumModal" required id="inputSuccess5">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the user phone</div>
							    </div> 
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess6">Login Username</label>
							         <input type="text" class="form-control"  name="userUserNameModal" required id="inputSuccess6">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the login username</div>
							    </div> 
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess7">Login Password</label>
							         <input type="password" class="form-control"  name="userPasswordModal" required id="inputSuccess7">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the login password</div>
							    </div>   
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess8">Role</label>
							        <select class="form-control" name="userRoleNameModal" required id="inputSuccess8">
								    </select>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the user role</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess9">User Active</label>
							        <select class="form-control" name="userActiveModal" required id="inputSuccess9">
								      <option value="Yes">Yes</option>
								      <option value="No">No</option>
								    </select>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please select the user active yes or no</div>
							    </div>
								 <div class="modal-footer">
									<button type="button" class="btn btn-primary" id="userModalEdit-btn">Save</button>
									<button type="button" class="btn btn-primary cancel"
											data-dismiss="modal">Cancel</button>
									<input type="hidden" id="userModalProcess"/>
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