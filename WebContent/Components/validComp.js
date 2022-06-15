$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateComplainetForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidCompIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "ComplaintAPI",  
			type : type,  
			data : $("#formComplain").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onComptSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onComptSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divCompGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidCompIDSave").val("");  
	$("#formComplain")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidCompIDSave").val($(this).closest("tr").find('#hidCompIDUpdate').val());     
	$("#customerName").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#customerPNO").val($(this).closest("tr").find('td:eq(1)').text());
	$("#description").val($(this).closest("tr").find('td:eq(2)').text());
	    
}); 



//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "ComplaintAPI",   
		type : "DELETE",   
		data : "cID=" + $(this).data("cid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onComptDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onComptDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divCompGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateComplainetForm() 
{  
	// ACCcustomerNameOUNT  
	if ($("#customerName").val().trim() == "")  
	{   
		return "Insert customerName.";  
	}

	// customerPNO------------------------  
	if ($("#customerPNO").val().trim() == "")  
	{   
		return "Insert customerPNO.";  
	} 
	
	// description------------------------  
	if ($("#description").val().trim() == "")  
	{   
		return "Insert description.";  
	}
	

	return true; 
}

