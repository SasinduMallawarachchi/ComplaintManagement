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

<nav class="navbar navbar-dark bg-dark" style="justify-content: center">
    <h3 style="color: white">ElectroGrid</h3>
</nav>
<br>
<div class="container" style="max-width: 90%">
<div style="text-align: center"> 
	<h1>Complaint Management</h1>
</div>	
	<div class="row">  
		<div class="col-4"> 			
			<br>
				<form id="formComplain" name="formComplain" method="post" action="Index.jsp">  
					Customer Name:  
 	 				<input id="customerName" name="customerName" type="text"  class="form-control">
					<br>Customer Phone:   
  					<input id="customerPNO" name="customerPNO" type="text" class="form-control">   
  					<br>Description:   
  					<input id="description" name="description" type="text"  class="form-control">
					<br> 

					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-info">  
					<input type="hidden" id="hidCompIDSave" name="hidCompIDSave" value=""> 
				</form>
				<br>
				
				<div id="alertSuccess" class="alert alert-success"> </div>				
			   	<div id="alertError" class="alert alert-danger"></div>	   
			</div>
		
			<div id="divCompGrid" class="col-8" style="text-align: center">
				<br><br>
				<%
					ComplaintManagement compObj = new ComplaintManagement();
					out.print(compObj.readComp());
				%>
			</div>
		</div>
</div>
 
</body>
</html>
