package com.carpooling.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpooling.constants.Constant;

public class AuthFilter implements Filter{

	private String LOGIN_ACTION_URI;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        ((HttpServletResponse) response).setHeader("Cache-control", "no-cache, no-store");
        ((HttpServletResponse) response).setHeader("Pragma", "no-cache");
        ((HttpServletResponse) response).setHeader("Expires", "-1");
        
		HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("user");
        
        if (request.getParameter("logout") != null && Constant.loginBean != null) {
        	
        	Constant.loginBean.setAuthenticated(false);
        	Constant.loginBean.setUsername(null);
        	Constant.loginBean.setPassword(null);
        	Constant.loginBean.setId(-1);
        	
            Constant.loginBean = null;
        	Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        	
        	if (cookies != null){
        		for (Cookie ck : cookies) {
        			if ("offerID".equals(ck.getName())) {
        				ck.setValue(null);
        				ck.setMaxAge(0);
        				
        			}
        			else if ("user".equals(ck.getName())) {
        				ck.setValue(null);
        				ck.setMaxAge(0);
        				
        				

        			}
        		}
        	}
        	
        	System.out.println("logout");
        	
        	cookies = ((HttpServletRequest) request).getCookies();
			if (cookies != null) {
				for (Cookie ck : cookies) {
					if ("user".equals(ck.getName())) {
						email = ck.getValue();
						System.out.println("email: " + email + " expiry time: " + ck.getMaxAge());
					}
				}
			}
        	
        	response.setContentType("text/html");

        	((HttpServletResponse) response).setHeader("Cache-Control", "no-cache, no-store");
        	((HttpServletResponse) response).setHeader("Pragma", "no-cache");
        	
        	session.invalidate();
        	((HttpServletResponse) response).sendRedirect("/CarPoolingApp");
            /*RequestDispatcher rd = req.getRequestDispatcher("/");
            rd.forward(request, response);*/
            return;
         }
        
        System.out.println("email : " + email);
         
        if (Constant.loginBean == null && !LOGIN_ACTION_URI.equals(req.getRequestURI())){
            /*RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);*/
        	out.print("<p>You have to be logged in.</p>");
        	
            return;
        }
         
        chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		LOGIN_ACTION_URI = fConfig.getInitParameter("loginActionURI");
	}

}
