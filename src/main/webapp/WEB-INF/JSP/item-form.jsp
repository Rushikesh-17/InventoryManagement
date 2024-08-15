<%@page import="com.Model.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Item Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@include file="navbar.jsp"  %>

    <div class="container mt-5">
        <% 
            Item i = (Item) request.getAttribute("item");
            if(i == null){
        %>  
        <h2 class="mb-4">Add Item</h2>
        <form action="insert">
            <input type="hidden" id="id" name="id" value="${item.id}">

            <div class="mb-3">
                <label for="name" class="form-label">Name:</label>
                <input type="text" class="form-control" id="name" name="name" value="${item.name}" required>
            </div>
            <div class="mb-3">
                <label for="measure" class="form-label">Measure:</label>
                <input type="text" class="form-control" id="measure" name="measure" value="${item.measure}" required>
            </div>
            <div class="mb-3">
                <label for="category" class="form-label">Category:</label>
                <input type="text" class="form-control" id="category" name="category" value="${item.category}" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Item</button>
        </form>
        <% 
            } else {
        %>
        <h2 class="mb-4">Update Item</h2>
        <form action="update">
            <input type="hidden" id="id" name="id" value="${item.id}">

            <div class="mb-3">
                <label for="name" class="form-label">Name:</label>
                <input type="text" class="form-control" id="name" name="name" value="${item.name}" required>
            </div>
            <div class="mb-3">
                <label for="measure" class="form-label">Measure:</label>
                <input type="text" class="form-control" id="measure" name="measure" value="${item.measure}" required>
            </div>
            <div class="mb-3">
                <label for="category" class="form-label">Category:</label>
                <input type="text" class="form-control" id="category" name="category" value="${item.category}" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Item</button>
        </form>
        <% 
            }
        %>
    </div>

    <!-- Bootstrap JS (Optional) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
