<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <button type="submit">Login</button>
        </div>
    </form>

    <% 
        String error = request.getParameter("error");
        if ("true".equals(error)) {
    %>
        <p style="color: red;">Invalid username or password. Please try again.</p>
    <% 
        } 
    %>
</body>
</html>
