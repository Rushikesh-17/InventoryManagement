package com.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Simply redirect to the login page if a GET request is made to /login
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
//
//
//        // Implement your authentication logic here
//        if ("admin".equals(username) && "admin".equals(password)) {
//            // User is valid, store in session
//            HttpSession session = request.getSession();
//            session.setAttribute("user", username);
//
//            // Redirect to ItemController
//            response.sendRedirect(request.getContextPath() + "/items/list");
//        } else {
//            // Invalid login, redirect back to login page
//            response.sendRedirect(request.getContextPath() + "/login.jsp?error=true");
//        }
    }
}

