<%@page import="com.Model.Record"%>
<%@page import="java.util.List"%>
<%@page errorPage="error.jsp"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Inventory Management Application</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<%@include file="navbar.jsp"%>

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
			<a href="records/new">Add New Record</a> <a href="records/fileUpload">Add
				file</a>
		</h4>
		<!-- Pagination -->
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<%
				// Retrieve pagination attributes from the request
				int currentPage = (int) request.getAttribute("currentPage");
				int totalPages = (int) request.getAttribute("totalPages");

				// Previous button
				if (currentPage > 1) {
				%>
				<li class="page-item"><a class="page-link"
					href="records?page=<%=currentPage - 1%>">Previous</a></li>
				<%
				} else {
				%>
				<li class="page-item disabled"><a class="page-link" href="#">Previous</a>
				</li>
				<%
				}

				// Page numbers
				for (int i = 1; i <= totalPages; i++) {
				if (i == currentPage) {
				%>
				<li class="page-item active"><a class="page-link" href="#"><%=i%></a>
				</li>
				<%
				} else {
				%>
				<li class="page-item"><a class="page-link"
					href="records?page=<%=i%>"><%=i%></a></li>
				<%
				}
				}

				// Next button
				if (currentPage < totalPages) {
				%>
				<li class="page-item"><a class="page-link"
					href="records?page=<%=currentPage + 1%>">Next</a></li>
				<%
				} else {
				%>
				<li class="page-item disabled"><a class="page-link" href="#">Next</a>
				</li>
				<%
				}
				%>
			</ul>
		</nav>
	</div>

	<!-- Bootstrap JS (optional, for certain Bootstrap features) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>