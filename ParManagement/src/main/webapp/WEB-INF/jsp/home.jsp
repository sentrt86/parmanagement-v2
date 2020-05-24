<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
	
	<title>PAR Management</title>
	
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
	<link rel="stylesheet" href="static/css/area.css">		
	
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
		</div>
		<footer class="footer">
			<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
		</footer>
</body>
</html> 

