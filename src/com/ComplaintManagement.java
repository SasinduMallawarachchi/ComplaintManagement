package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class ComplaintManagement {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	//I n s e r t
	public String insertComp(String customerName, String customerPNO, String description)  
	{   
		String output = ""; 	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into complaint(`cID`,`customerName`,`customerPNO`,`description`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerName);
			preparedStmt.setString(3, customerPNO);
			preparedStmt.setString(4, description);
			// execute the statement   
			preparedStmt.execute();    
			con.close(); 
	   
			String newComp = readComp(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newComp + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Complaint. Try again\"}";  
			System.err.println(e.getMessage());   
		} 		
	  return output;  
	} 	
	
	// R e a d
	public String readComp()  
	{   
		String output = ""; 
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table  class='table table-hover table-bordered'><thead class='thead-dark'><th>customer Name</th><th>customer PNO</th><th>Description</th><th>Date</th><th>Update</th><th>Remove</th></thead>";
	 
			String query = "select * from complaint";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				 String cID = Integer.toString(rs.getInt("cID"));
				 String customerName = rs.getString("customerName");
				 String customerPNO = rs.getString("customerPNO");
				 String description = rs.getString("description");
				 String date = rs.getString("date");
				 
				 
				// Add into the html table 
				output += "<tr><td><input id='hidCompIDUpdate' name='hidCompIDUpdate' type='hidden' value='" + cID + "'>" + customerName + "</td>"; 
				output += "<td>" + customerPNO + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + date + "</td>";
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-success'></td>"       
						+ "<td><input name='hidItemIDDelete' type='button' value='Remove' class='btnRemove btn btn-danger' data-cid='" + cID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	   
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Unit.";    
			System.err.println(e.getMessage());   
		} 	 
		return output;  
	}
	//U p d a t e
	public String updateComp(String cID, String customerName, String customerPNO, String description)  
	{   
		String output = "";  
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE complaint SET customerName=?,customerPNO=?,description=?"  + "WHERE cID=?";  	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setString(1, customerName);
			 preparedStmt.setString(2, customerPNO);
			 preparedStmt.setString(3, description);
			 preparedStmt.setInt(4, Integer.parseInt(cID));
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			String newComp = readComp();    
			output = "{\"status\":\"success\", \"data\": \"" + newComp + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Unit.\"}";   
			System.err.println(e.getMessage());   
		} 	 
	  return output;  
	} 
	
	//D e l e t e
	public String deleteComp(String cID)   
	{   
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from complaint where cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(cID));

			// execute the statement
			preparedStmt.execute();
			con.close(); 
	 
			String newComp = readComp();    
			output = "{\"status\":\"success\", \"data\": \"" +  newComp + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Unit.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}


