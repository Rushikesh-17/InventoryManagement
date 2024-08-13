<%@page import="com.Model.Item"%>
<%@page import="java.util.List"%>
<%@page import="com.Services.ItemServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Item Management Application</title>

<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: left;
}
</style>
</head>
<body>
	<h1>Item Management</h1>
    <h2>
        <a href="new">Add New Item</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">List All Items</a>
    </h2>
	<table>
		<tr>
			<th>Item ID</th>
			<th>Name</th>
			<th>Quantity</th>
			<th>Actions</th>
		</tr>

		<%
		List<Item> itemList = (List<Item>) request.getAttribute("listItem");
		for (Item i : itemList) {
			out.println("<tr>");
			out.println("<td>" + i.getId() + "</td>");
			out.println("<td>" + i.getName() + "</td>");
			out.println("<td>" + i.getQuantity() + "</td>");
			
			out.println("<td> <a href='edit?id="+i.getId()+"'>Edit</a><br>"
					   +"<a href='delete?id="+i.getId()+"'>Delete</a></td>");
			out.println("</tr>");
		}
		%>
	</table>
	
</body>
</html>
