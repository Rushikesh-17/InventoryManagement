<%@page import="com.Model.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Add/Update Item</h2>
<% 
	Item i = (Item) request.getAttribute("item");
	if(i == null){
%>
    <form action="insert" >	
    <input type="hidden" id="id" name="id" value="${item.id}">

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${item.name}" required><br>
        <!-- 
        <label for="name">Quantity:</label>
        <input type="text" id="quantity" name="quantity" value="${item.quantity}" required><br>
         -->
        <label for="name">Measure:</label>
        <input type="text" id="measure" name="measure" value="${item.measure}" required><br>
        
        <label for="name">category:</label>
        <input type="text" id="category" name="category" value="${item.category}" required><br>
        <input type="submit" name="submit">
	</form>
<% 
	}else{
%>
	<form action="update" >	<input type="hidden" id="id" name="id" value="${item.id}">

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${item.name}" required><br>
        
        <label for="name">Quantity:</label>
        <input type="text" id="quantity" name="quantity" value="${item.quantity}" required><br>
        
        <label for="name">Measure:</label>
        <input type="text" id="measure" name="measure" value="${item.measure}" required><br>
        
        <label for="name">category:</label>
        <input type="text" id="category" name="category" value="${item.category}" required><br>
        <input type="submit" name="submit">
	</form>
<% 
	}
	
%><!--
        <input type="hidden" id="id" name="id" value="${item.id}">

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${item.name}" required><br>
        
        <label for="name">Quantity:</label>
        <input type="text" id="quantity" name="quantity" value="${item.quantity}" required><br>
        
        <label for="name">Measure:</label>
        <input type="text" id="measure" name="measure" value="${item.measure}" required><br>
        
        <label for="name">category:</label>
        <input type="text" id="category" name="category" value="${item.category}" required><br>
        <input type="submit" name="submit">
	</form>
	-->
</body>
</html>