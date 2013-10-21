package com.carpooling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpooling.businessinterface.ILoginBeanRemote;
import com.carpooling.businessinterface.IUsersBeanRemote;
import com.carpooling.constants.Constant;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Context ctx;
	private Properties properties = new Properties();

	String name, email, password, carmodel, referer, phone;
	int  age, gender;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		

		
        

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        
		init(request);
		
    	try {
			ctx = new InitialContext(properties);
        	IUsersBeanRemote userBean = (IUsersBeanRemote) ctx.lookup(Constant.lookUpName("Users"));
        	
        	System.out.println(userBean);
        	
        	int success = userBean.addNewRecord(0, name, age, gender, phone, email, password, carmodel);

        	if(success == 1){

        		Constant.loginBean = (ILoginBeanRemote) ctx.lookup(Constant.lookUpName("Login") + "?stateful");
        		Constant.loginBean.setAuthenticated(true);
        		Constant.loginBean.setUsername(email);
        		Constant.loginBean.setPassword(password);
        		int userID = userBean.findUserByEmail(email).getID();
        		Constant.loginBean.setId(userID);
        		
        		HttpSession session = request.getSession();
                session.setAttribute("user", email);
                System.out.println("registration successful.");
            	response.setContentType("text/html");
            	Cookie cookie = new Cookie("user", email);
            	cookie.setMaxAge(30*60);
            	response.addCookie(cookie);
                //response.sendRedirect("/CarPoolingApp/profile.jsp?ID=" + userBean.findUserByEmail(email).getID());
        		
        		out.print("Welcome " + name + "! Registration is successful.");
        		out.print("<p><a href='"+ referer + "'>Go to the previous page</a></p>");
        		
        		
        	}
        	else{
        		out.println("User already exists. Please retry.");
        	}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init(HttpServletRequest request){
		
		name = request.getParameterValues("name")[0];
		age = Integer.parseInt(request.getParameterValues("age")[0]);
		gender = Integer.parseInt(request.getParameter("gender"));
		email = request.getParameterValues("email")[0];
		password = request.getParameterValues("password")[0];
		carmodel = request.getParameterValues("carmodel")[0];
		phone = request.getParameterValues("phone")[0];
		referer = //request.getParameterValues("referer")[0];
				request.getHeader("referer");
		properties.put(Context.URL_PKG_PREFIXES, Constant.PKG_INTERFACES);
	        

	}

}
