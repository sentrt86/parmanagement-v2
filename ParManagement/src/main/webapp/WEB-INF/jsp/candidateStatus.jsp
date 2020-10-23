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
		
		<title>Par Entry Edit</title>
		
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
		<link rel="stylesheet" href="static/css/parentry.css">		
		
		<!-- JQuery -->
		<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
		<script type="text/javascript" src="static/js/popper.min.js"></script>
	
		<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
		<script type="text/javascript" src="static/js/bootstrap.js"></script>
		<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
		<!-- Par Management Java Script -->
		<script type="text/javascript" src="static/js/common.js"></script>
		<script type="text/javascript" src="static/js/candidatestatus.js"></script>
	</head>
	<body>
			<!-- Page Header and Menu jsp -->
			<jsp:include page="header-menu.jsp" />		
			<div class="main">
				<div>
			    	<h1 class="screen-title">Candidate Status</h1>
			    </div>
				<form class="form-top">
				  <div class="row">
				    <div class="col col-sm-4">
				      <div class="form-inline">
				      	<label class="form-control-label" for="inputSuccess1">Candidate Name :  </label> &nbsp;&nbsp;
				        <select class="form-control mb-6 mr-sm-2" name="candidateName" id="inputSuccess1" required  style="width:205px;" >
					        <option value="" disabled selected>Select the Candidate</option>
							        <c:forEach var="candidate" items="${allCandidateList}">
					   					<option  value="${candidate.candidateId}" >${candidate.candidateName}</option>
		       	   					</c:forEach>
						 </select>
				        <div class="valid-feedback">Success!</div>
				        <div class="invalid-feedback">Please Select the Candidate</div>
				      </div>
				    </div>
				    <div class="col col-sm-4">
				      <div class="form-inline">
				      	<label class="form-control-label" for="inputSuccess2">Par No :  </label> &nbsp;&nbsp;
				        <select class="form-control mb-6 mr-sm-2" name="parNo" id="inputSuccess2" disabled  style="width:205px;" >
					        <option value="" disabled selected>Select the Par Number</option>
							        
						 </select>
				        <div class="valid-feedback">Success!</div>
				        <div class="invalid-feedback">Please Select the Par Number</div>
				      </div>
				    </div>
				  </div>
				 </form>

				 <hr class="linebreak"></hr>
				 <form class="form-top" id="candidateStatusForm"> 
				  <div class="row mt-3 marginRow">
				    <div class="col-sm">
				      <div class="form-group">
				      		<label class="form-control-label mr-sm-2" >Role</label>
					        <input type="text" class="form-control  mb-6 mr-sm-2" name="parRole" disabled id="parRole" style="width:225px;"></input>
					  </div>
				    </div>
				    <div class="col-sm">
				       <div class="form-group">
				       		<label class="form-control-label mr-sm-2" >Skill</label>
					        <input type="text" class="form-control mb-6 mr-sm-2" name="skillName" disabled id="skillName" style="width:230px;"></input>
					   </div>
				    </div>
				    <div class="col-sm" >
				       <div class="form-group">
				       		<label class="form-control-label mr-sm-2" >Location</label>
					        <input type="text" class="form-control mb-6 mr-sm-2" name="locationName" disabled id="locationName" style="width:230px;"></input>
					   </div>
				    </div>
				    <div class="col-sm">
				      <div class="form-group">
				        	<label class="form-control-label mr-sm-2" >Area</label>
					        <input type="text" class="form-control mb-6 mr-sm-2" name="areaName" disabled id="areaName"></input>
					  </div>
				    </div>
				  </div>				  
				  <div class="row mt-3 marginRow">
				    <div class="col">
				      <div class="form-group">
				          <label class="form-control-label" >Description</label>
						  <textarea class="form-control" rows="5" name="parDescriptionText" disabled id="parDescriptionText" placeholder="Enter Description"></textarea>  
					  </div>
				    </div>
				  </div>
				  
				  <div class="row mt-3 marginRow">
				  	<div class="col">
				      <div class="form-group">
				       	 	<label class="form-control-label mr-sm-2" style="font-family:Roboto; font-weight:100;">External Staff</label>
					        <input type="text" class="form-control mb-6 mr-sm-2" name="extStaffName" disabled id="extStaffName"></input>
					  </div>
				    </div>
				    <div class="col">
				      <div class="form-group">
				      	<label class="form-control-label mr-sm-2" >Par Received Date</label>
				      	<input type="text" class="form-control mb-6 mr-sm-2" name="parDateReceived" id="parDateReceived" disabled></input>
				      </div>
				    </div>
				    <div class="col">
					  <div class="form-group">
					    <label class="form-control-label mr-sm-2">Active Par</label>
				        <input type="text" class="form-control mb-6 mr-sm-2" name="activePar" disabled id="activePar"></input>
				      </div>
				    </div>
				  </div>
				  <div class="row mt-3 marginRow">
				    <div class="col">
				      <div class="form-group">
				      		<label class="form-control-label mr-sm-2" for="inputSuccess10">Prescreener Name</label>
					        <select class="form-control  mb-6 mr-sm-2" name="prescreenerName" disabled id="inputSuccess10">
					            <option value="" disabled selected>Select the Role</option>
						        <c:forEach var="prescreener" items="${allprescreenerList}">
				   					<option  value="${prescreener.preScreenerId}" >${prescreener.preScreenerName}</option>
	       	   					</c:forEach>
						    </select>
					        <div class="valid-feedback">Success!</div>
					        <div class="invalid-feedback">Please select the prescreener</div>
					  </div>
				    </div>
				    <div class="col">
				       <div class="form-group">
				       		<label class="form-control-label mr-sm-2" for="inputSuccess11">Prescreening Date</label>
					        <input type="date" class="form-control mb-6 mr-sm-2" name="preScreeningDate" id="inputSuccess11" disabled placeholder="Enter Date Received"></input>
				       		 
					        <div class="valid-feedback">Success!</div>
					        <div class="invalid-feedback">Please enter the prescreening date</div>
					   </div>
				    </div>
				    <div class="col">
				       <div class="form-group">
				       		<label class="form-control-label mr-sm-2" for="inputSuccess12">Prescreening Comment Status</label>
					        <select class="form-control  mb-6 mr-sm-2" name="prescreeningCommentStatus" disabled id="inputSuccess12">
					           
					     		<option value="inprogress">inprogress</option>
					      		<option value="completed">completed</option>
					      		<option value="Notassigned">Notassigned</option>
						    </select>
					   </div>
				    </div>
				  </div>				  
				  <div class="row  mt-3 marginRow">
				   	  <div class="col">
				   	   	<button type="button" class="btn btn-primary" id="candidateStatusSave-btn">Save</button>
				   	   	<input type="hidden" id="parId" name="parId">
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