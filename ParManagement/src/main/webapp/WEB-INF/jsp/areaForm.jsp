<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Area and Product</title>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

	<!--  Jquery CSS -->
	<link rel="stylesheet" href="static/css/jquery-ui.min.css">
	<link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
	<!--  Bootstrap CSS -->
	<link rel="stylesheet" href="static/css/bootstrap.css">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/datatables.bootstrap4.min.css">
	<!-- Par Management CSS -->
	<link rel="stylesheet" href="static/css/common.css">
	<link rel="stylesheet" href="static/css/area.css">		
	
	<!-- JQuery -->
	<script type="text/javascript" src="static/js/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="static/js/bootstrap.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>		
	<!-- Par Management Java Script -->
	<script type="text/javascript" src="static/js/common.js"></script>
	<script type="text/javascript" src="static/js/area.js"></script>
</head>
<body>


	<div class="row header">
		<img src="static/images/htc-logo.png" class="htc_logo"/> 
		<div class="header_title">
			<span><h1>PAR Management</h1></span>
		</div>
		<div class="header_right">		      
		      <a href="#">
		   		<i class="fa fa-sign-out logout" style="font-size:24px; color:#ffa500;"></i>
		  	  </a>
		  	  <span class="username">Pradeep Ekambaram</span>
		</div>
	</div>

	<hr class="hrline">
	


		<div class="sidenav">
		 <a href="./"><img src="static/images/home.png" width="13px" height="13px" />&nbsp;Home</a>
		  <button class="dropdown-btn"><img src="static/images/master.png" width="13px" height="13px" />&nbsp;Masters 
		    <i class="fa fa-caret-down"></i>
		  </button>
		  <div class="dropdown-container">
		    <a href="./area">Area / Product</a>
		    <a href="#">External Staffing</a>
		    <a href="#">Role</a>
		    <a href="#">Skill</a>
		    <a href="#">Recruiter</a>
		    <a href="#">Candidate</a>
		    <a href="#">Pre Screener</a>
		    <a href="#">User</a>
		    
		  </div>
		  <button class="dropdown-btn"><img src="static/images/fullfillment.png" width="13px" height="13px" />&nbsp;Par Fullfillment 
		    <i class="fa fa-caret-down"></i>
		  </button>
		  <div class="dropdown-container">
		    <a href="./parentry">PAR Entry</a>
		    <a href="#">Email Recruiters</a>
		    <a href="#">Intent to Fill</a>
		    <a href="#">Candidate Received</a>
		    <a href="#">Prescreening Results</a>
		    <a href="#">Submit Candidate</a>
		    <a href="#">Offer Received</a>
		    <a href="#">Setup Submitted</a>
		    <a href="#">Candidate Starts</a>
		  </div>
		   
		  <button class="dropdown-btn"> <img src="static/images/reports.png" width="13px" height="13px" />&nbsp; Reports
		    <i class="fa fa-caret-down"></i>
		  </button>
		  <div class="dropdown-container">
		    <a href="#">Link 1</a>
		    <a href="#">Link 2</a>
		    <a href="#">Link 3</a>
		  </div>
		</div>
	
		<div class="main">
			<div >
				<h1 class="screen-title">Area / Product</h1>
			</div>
		    <div class="tablediv">
		    	<table id="areaTable" class="table table-striped table-bordered" style="width:100%">
				     <thead>
				         <tr>
				             <th>Area Id</th>
				             <th>Area Name</th>
				             <th>Active</th>
				         </tr>
				     </thead>
				     <tbody>
				     	<c:forEach var="area" items="${allAreasList}">
				     		<tr>
				     			<td>${area.areaId}</td>
				     			<td>${area.areaName}</td>
				     			<td>${area.areaActive}</td>
				     		</tr>        		
				     	</c:forEach>
				     </tbody>
				</table>
		   </div>
		</div>
		<footer class="footer">
			<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
		</footer>
		
		
		<!-- Delete Area Message Modal -->
		<div class="modal fade" id="areaDeleteconfirmModal" tabindex="-1"
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
						<p id="areaDeleteconfirmModalBody"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="areaDelete-btn">Delete</button>
						<button type="button" class="btn btn-primary cancel"
							data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Edit Area Message Modal -->
		<div class="modal fade" id="areaEditModal" tabindex="-1"
			role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="confirmModalLabel">Update Area</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
						  <div class="form-group">
						    <label>Area Id</label>
						    <input type="text" class="form-control" id="areaIdModal" readonly>
						  </div>
						  <div class="form-group">
						    <label>Area Id</label>
						    <input type="text" class="form-control" id="areaNameModal">
						  </div> 
						  <div class="form-group">
						    <label>Area Active</label>
							    <select class="form-control" id="areaActiveModal">
							      <option>Yes</option>
							      <option>No</option>
							    </select>
						  </div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="areaSave-btn">Save</button>
						<button type="button" class="btn btn-primary cancel"
							data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>
		</div>
</body>
</html>