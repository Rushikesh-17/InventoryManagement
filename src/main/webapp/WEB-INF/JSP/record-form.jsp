<%@page import="com.Model.Record"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Record Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Add Record</h2>
        <form action="insert">
            <input type="hidden" id="id" name="id" value="${record.recordId}">

            <div class="mb-3">
                <label for="user" class="form-label">User:</label>
                <input type="text" class="form-control" id="user" name="user" value="${record.user}" required>
            </div>

            <div class="mb-3">
                <label for="itemName" class="form-label">Item:</label>
                <input type="text" class="form-control" id="itemName" name="itemName" value="${record.itemName}" required>
            </div>

            <div class="mb-3">
                <label for="usedQuantity" class="form-label">Quantity:</label>
                <input type="text" class="form-control" id="usedQuantity" name="usedQuantity" value="${record.usedQuantity}" required>
            </div>

            <div class="mb-3">
                <label for="action" class="form-label">Action:</label>
                <select class="form-select" id="action" name="action" required>
                    <option value="">Select Action</option>
                    <option value="used" ${record.action == 'used' ? 'selected' : ''}>Used</option>
                    <option value="added" ${record.action == 'added' ? 'selected' : ''}>Added</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Add Record</button>
        </form>
    </div>

</body>
</html>
