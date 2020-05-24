<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/> 
	
	<title>PAR Entry</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<!--  Jquery CSS -->
	<link rel="stylesheet" href="static/css/jquery-ui.min.css">
	<link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
	<!--  Bootstrap CSS -->
	<link rel="stylesheet" href="static/css/bootstrap.css">
	<link rel="stylesheet" href="static/css/datatables.bootstrap4.min.css">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<!-- Par Management CSS -->
	<link rel="stylesheet" href="static/css/common.css">
	<link rel="stylesheet" href="static/css/parentry.css">		
	
	<!-- JQuery -->
	<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
<!-- 	<script type="text/javascript" src="static/js/jquery.min.js"></script> -->
	<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="static/js/bootstrap.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>		
	<!-- Par Management Java Script -->
	<script type="text/javascript" src="static/js/common.js"></script>
</head>
<body>

		<!-- Page Header and Menu jsp -->
		<jsp:include page="header-menu.jsp" />
		
	
		<div class="main">
		    <div>
		    	<h1 class="screen-title">PAR</h1>
		    </div>
			<form class="parentry-form">
			  <div class="row">
			    <div class="col">
			      <input type="text" class="form-control" id="parId" placeholder="Enter Par Id" name="parId">
			    </div>
			    <div class="col">
			      <button type="button" class="btn btn-primary">New</button>
			    </div>
			  </div>
			  <hr class="linebreak"></hr>
			  <div class="row mt-3">
			    <div class="col">
			      <input type="text" class="form-control" id="parNo" placeholder="Enter Par No" name="parNo">
			    </div>
			    <div class="col">
			      <input type="text" class="form-control" id="role" placeholder="Enter Role" name="role">
			    </div>
			    <div class="col">
			      <input type="text" class="form-control" id="skill" placeholder="Enter Skill" name="skill">
			    </div>
			  </div>
			  <div class="row mt-3">
			    <div class="col">
			      <div class="form-group">
					  <textarea class="form-control" rows="5" id="description" name="description" placeholder="Enter Description"></textarea>
				  </div>
			    </div>
			  </div>
			  <div class="row mt-3">
			    <div class="col">
			 <!--      <input type="text" class="form-control" id="areaProduct" placeholder="Enter Area/Product" name="areaProduct"> -->
			      <select class="form-control input-small "  id="area"  placeholder="Enter Area/Product">
			           <option value="" selected disabled hidden>Select Area Name</option>
			       	   <c:forEach var="area" items="${allAreasList}">
						   <option  value="${area.areaId}" >${area.areaName}</option>
		        	   </c:forEach>
				 </select>
			    </div>
			  </div>
			  <div class="row mt-3">
			    <div class="col">
			      <input type="date" class="form-control" id="dateReceived" placeholder="Enter Date Received" name="dateReceived">
			    </div>
			    <div class="col">
			      <input type="text" class="form-control" id="extStaffName" placeholder="Enter External Staff Name" name="extStaffName">
			    </div>
			    <div class="col">
				  <select class="form-control" id="activePar" name="activePar">
				   <option>Active PAR (Y/N)</option>
				    <option>Yes</option>
				    <option>No</option>
				  </select>
			    </div>
			  </div>
			  <div class="row mt-3">
			   	  <div class="col">
			   	   	<button type="button" class="btn btn-primary">Save</button>
			   	  </div>
			  		
			  </div>
			</form>
		</div>
		<footer class="footer">
			<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
		</footer>
</body>
</html> 

