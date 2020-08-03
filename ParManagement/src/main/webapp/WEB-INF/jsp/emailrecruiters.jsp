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
		
		<title>Email Recruiters</title>
		
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
		<link rel="stylesheet" href="static/css/emailrecruiters.css">		
		
		<!-- JQuery -->
		<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
		<script type="text/javascript" src="static/js/popper.min.js"></script>
	
		<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
		<script type="text/javascript" src="static/js/bootstrap.js"></script>
		<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
		<!-- Par Management Java Script -->
		<script type="text/javascript" src="static/js/common.js"></script>
		<script type="text/javascript" src="static/js/emailrecruiters.js"></script>
	</head>
	<body>
			<!-- Page Header and Menu jsp -->
			<jsp:include page="header-menu.jsp" />		
			<div class="main">
				<div>
			    	<h1 class="screen-title">Email Recruiters</h1>
			    </div>
				<form class="form-top" id="parEntryForm">
				  <div class="row">
				    <div class="col col-sm-4">
				      <div class="form-inline">
				      	<label class="form-control-label" for="inputSuccess1">Par No :  </label> &nbsp;&nbsp;
				        <input type="text" class="form-control mb-6 mr-sm-2" name="parNo" id="inputSuccess1" required  placeholder="Enter Par No" style="width:205px;">
				        <div class="valid-feedback">Success!</div>
				        <div class="invalid-feedback">Please enter the par no</div>
				      </div>
				    </div>
				  </div>
				  <div class="row" style="padding-top:25px;">
				  	<div class="col">
				      <div class="form-group">
				          <label class="form-control-label" for="inputSuccess2" >Par Comment</label>
						  <textarea class="form-control" rows="5" name="parComment"  id="inputSuccess2" placeholder="Par Comment" ></textarea>
					  </div>
				    </div>
				  </div>
				  <hr class="linebreak"></hr>
				  <div class="row mt-3 marginRow">
				   <!--  <div class="col" >
				      <div class="form-group">
				        <label class="form-control-label" for="inputSuccess3">Par No </label>
				      	<input type="text" class="form-control mb-6 mr-sm-2" name="parNo" id="inputSuccess3" placeholder="Par No" readonly>
				      </div>
				    </div> -->
				    <div class="col">
				      <div class="form-group">
				      		<label class="form-control-label" for="inputSuccess4">Role</label>
				      		<input type="text" class="form-control mb-6 mr-sm-2" name="parRole" id="inputSuccess4"  placeholder="Role" readonly>
					  </div>
				    </div>
				    <div class="col">
				       <div class="form-group">
				       		<label class="form-control-label" for="inputSuccess5">Skill</label>
				       		<input type="text" class="form-control mb-6 mr-sm-2" name="skillName" id="inputSuccess5"  placeholder="Skill" readonly>
					   </div>
				    </div>
				    <div class="col">
				       <div class="form-group">
				       		<label class="form-control-label" for="inputSuccess6">Location</label>
				       		<input type="text" class="form-control mb-6 mr-sm-2" name="locationName" id="inputSuccess6"  placeholder="Skill" readonly>
					   </div>
				    </div>
				  </div>
				  <div class="row mt-3 marginRow">
				    <div class="col">
				      <div class="form-group">
				          <label class="form-control-label" for="inputSuccess7" >Description</label>
						  <textarea class="form-control" rows="5" name="parDescriptionText"  id="inputSuccess7" placeholder="Description" readonly></textarea>
					  </div>
				    </div>
				  </div>
				  <div class="row mt-3 marginRow">
				  	<div class="col">
				      <div class="form-group">
				        	<label class="form-control-label mr-sm-2" for="inputSuccess8">Area</label>
				        	<input type="text" class="form-control mb-6 mr-sm-2" name="areaName" id="inputSuccess8"  placeholder="Area" readonly>
					  </div>
				    </div>
				  </div>
				  <div class="row mt-3 marginRow">
				  	<div class="col">
				      <div class="form-group">
				       	 	<label class="form-control-label mr-sm-2" for="inputSuccess9">External Staff</label>
					        <input type="text" class="form-control mb-6 mr-sm-2" name="extStaffName" id="inputSuccess9"  placeholder="External Staff" readonly>
					  </div>
				    </div>
				    <div class="col">
				      <div class="form-group">
				      	<label class="form-control-label mr-sm-2" for="inputSuccess10">Par Received Date</label>
				      	<input type="date" class="form-control mb-6 mr-sm-2" name="parDateReceived" id="inputSuccess10" placeholder="Date Received" readonly>
				      </div>
				    </div>
				    <div class="col">
					  <div class="form-group">
					    <label class="form-control-label mr-sm-2" for="inputSuccess11">Active Par (Y/N)</label>
					    <input type="text" class="form-control mb-6 mr-sm-2" name="activePar" id="inputSuccess11"  placeholder="Active Par (Y/N)" readonly>
				      </div>
				    </div>
				  </div>
				  <div class="row">
				  	<div class="col">
				      <button type="button" class="btn btn-primary" id="sendEmail-btn">Send Email</button>
				    </div>
				  </div>
				</form>
				
			</div>
			<footer class="footer">
				<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
			</footer>
			
		
			
			<!-- Message Modal -->
			<jsp:include page="Message.jsp" />
	</body>
	</html>