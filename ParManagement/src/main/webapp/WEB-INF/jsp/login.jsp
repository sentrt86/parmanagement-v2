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
	
	<title>Login Page</title>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<!--  Jquery CSS -->
	<link rel="stylesheet" href="static/css/jquery-ui.min.css">
	<link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="static/css/bootstrap.css">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/datatables.bootstrap4.min.css"> 
	<!-- Par Management CSS -->
	<link rel="stylesheet" href="static/css/common.css">
	<link rel="stylesheet" href="static/css/login.css">
		
	
	<!-- JQuery -->
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>

	<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
	<script type="text/javascript" src="static/js/bootstrap.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
	<!-- Par Management Java Script -->
	<script type="text/javascript" src="static/js/common.js"></script>
</head>
<body>
		
		<div class="header">
			<div class="logo">
				<img src="static/images/HTC-P3 logo.png" class="htc_p3_logo"/> 
			</div>			
			<div class="login_header_title">
				<span><h1>PAR Management Portal</h1></span>
			</div>
	   </div>

	   <hr class="hrline">
	   
	   <div class="main" style="margin-left: 20px; margin-top: 165px; " >
     		<div style="text-align:center;">
				<form method="post" id="login" enctype="application/x-www-form-urlencoded" action="./login">
		     		
						<div class="container">							
							<div>
							    <div class="userlogin">
							   		<h4 align="center">User Login</h4>
								</div>
								<div style="margin-top:7%;">
				 					<input type="text" id="username" name="username" class="form-control" placeholder="User name" autofocus="autofocus">
				     			</div>
				     			<div style="margin-top:7%;">
									<input type="password" id="password" name="password" class="form-control" placeholder="Password">
				     			</div>
				     			<br>
				     			<div align="center">
									<input name="submit" type="submit" value="Login" class="btn btn-primary">
				     			</div>
				     			<div class="msg">${error}</div>
			     			</div>		     			
			     			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</div>
		     		
				</form>
			</div>
	   </div>
	   
	   <footer class="footer">
				<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
		</footer>
		

</body>
</html>
	


		