<%@page import="com.Model.Item"%>
<%@page import="java.util.List"%>
<%@page import="com.Services.ItemServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inventory Management Application</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

</head>
<body>
	<div class="container mt-5">
		<h2 class="mb-4">Item Management</h2>
		<table class="table table-striped table-hover">
			<thead class="table-dark">
				<tr>
					<th>Item ID</th>
					<th>Name</th>
					<th>Quantity</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Item> itemList = (List<Item>) request.getAttribute("listItem");
				for (Item i : itemList) {
					String editlink = "edit?id=" + i.getId();
					String deletelink = "delete?id=" + i.getId();
				%>
				<tr>
					<td><%=i.getId()%></td>
					<td><%=i.getName()%></td>
					<td><%=i.getQuantity()%></td>
					<td><a href=<%=editlink%>>Edit</a><br> +"<a
						href=<%=deletelink%>>Delete</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<h4>
			<a href="new">Add New Item</a>
		</h4>
	</div>

</body>
</html>
