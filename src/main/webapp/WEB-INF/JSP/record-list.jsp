<%@page import="com.Model.Record"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Inventory Management Application</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2 class="mb-4">Records Management</h2>
		<table class="table table-striped table-hover">
			<thead class="table-dark">
				<tr>
					<th>ID</th>
					<th>User</th>
					<th>Item</th>
					<th>Quantity</th>
					<th>Action</th>
					<th>Date</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Record> records = (List<Record>) request.getAttribute("listRecord");
				for (Record r : records) {
				%>
				<tr>
					<td><%=r.getRecordId()%></td>
					<td><%=r.getUser()%></td>
					<td><%=r.getItem().getName()%></td>
					<td><%=r.getUsedQuantity()%></td>
					<td><%=r.getAction()%></td>
					<td><%=r.getDate()%></td>
				</tr>
				<%
				}
				%>

			</tbody>
		</table>
		<h4>
			<a href="new">Add New Record</a> 
		</h4>
	</div>

	<!-- Bootstrap JS (optional, for certain Bootstrap features) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>