package com.carpooling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpooling.businessinterface.ILoginBeanRemote;
import com.carpooling.businessinterface.IUsersBeanRemote;
import com.carpooling.constants.Constant;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Context ctx;
	private Properties properties = new Properties();
	
	String email, password, referer;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		 doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd=null;
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        
		init(request);
		
		try {
			ctx = new InitialContext(properties);
        	IUsersBeanRemote userBean = (IUsersBeanRemote) ctx.lookup(Constant.lookUpName("Users"));
        	Constant.loginBean = (ILoginBeanRemote) ctx.lookup(Constant.lookUpName("Login") + "?stateful");
        	        	
        	int success = userBean.authenticate(email, password);
        	
        	System.out.println("success code: " + success);
        	switch(success){
        		
        	case 1: {
        		
        		System.out.println("Login successful!");
        		
        		Constant.loginBean.setAuthenticated(true);
        		Constant.loginBean.setUsername(email);
        		Constant.loginBean.setPassword(password);
        		int userID = userBean.findUserByEmail(email).getID();
        		Constant.loginBean.setId(userID);
        		
        		
        		HttpSession session = request.getSession();
                session.setAttribute("user", email);
                
            	response.setContentType("text/html");
            	Cookie cookie = new Cookie("user", email);
            	cookie.setMaxAge(30*60);
            	response.addCookie(cookie);
               // response.sendRedirect("/CarPoolingApp/profile.jsp?ID=" + userBean.findUserByEmail(email).getID());
            	response.sendRedirect(referer);
        	}
        		break;
        	case 0: {
        		//rd = request.getRequestDispatcher("/loginError.jsp");
        		response.sendRedirect("/CarPoolingApp/loginError.jsp");
        		System.out.println("No such user! Please retry.");
        	}
        		break;
        	case -1: {
        		//rd = request.getRequestDispatcher("/loginError.jsp");
        		response.sendRedirect("/CarPoolingApp/loginError.jsp");

        		System.out.println("Wrong password! please retry.");
        	}
        		break;
        	}
			
        	//rd.forward(request, response);
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void init(HttpServletRequest request){
		
		email = request.getParameterValues("email")[0];
		password = request.getParameterValues("password")[0];
		referer = request.getHeader("referer");
		properties.put(Context.URL_PKG_PREFIXES, Constant.PKG_INTERFACES);
		
		
	    
	}
	
	
}
