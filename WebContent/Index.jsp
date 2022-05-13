<%@ page import="com.ComplaintManagement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complaint Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/validComp.js"></script> 
</head>
<body>

<div class="container"> 
	<div class="row">  
		<div class="col"> 
			<h1>Complaint Management</h1>
			<br>
				<form id="formComplain" name="formComplain" method="post" action="Index.jsp">  
					Customer Name:  
 	 				<input id="customerName" name="customerName" type="text"  class="form-control form-control-sm">
					<br>Customer Phone:   
  					<input id="customerPNO" name="customerPNO" type="text" class="form-control form-control-sm" >   
  					<br>Description:   
  					<input id="description" name="description" type="text"  class="form-control form-control-sm">
					<br> 
					
					<div id="alertSuccess" class="alert alert-success"> </div>				
			   		<div id="alertError" class="alert alert-danger"></div>
			   		 
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidCompIDSave" name="hidCompIDSave" value=""> 
				</form>
				
				
			   <br>
				<div id="divCompGrid">
					<%
					ComplaintManagement compObj = new ComplaintManagement();
						out.print(compObj.readComp());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>