package com.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/login")
public class LoginFilter extends HttpFilter implements Filter {
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("in the filter");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse)response;
        String uname = request.getParameter("username");
        String pass = request.getParameter("password");
        
        if("admin".equals(uname) && "secure".equals(pass))
        {
        	HttpSession session = req.getSession();
        	session.setAttribute(uname, "uname");
        	System.out.println("correct password");
        	resp.sendRedirect(req.getContextPath()+"/items/list");
        }
        else
        {
        	System.out.println("inavlid password");
        	resp.sendRedirect(req.getContextPath()+"/login.jsp?error=true");
        }
		// pass the request along the filter chain
		//chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
