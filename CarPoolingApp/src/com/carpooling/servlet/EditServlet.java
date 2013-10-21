package com.carpooling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carpooling.businessinterface.ICitiesBeanRemote;
import com.carpooling.businessinterface.IRideOffersBeanRemote;
import com.carpooling.businessinterface.IUsersBeanRemote;
import com.carpooling.constants.Constant;
import com.carpooling.entities.Users;

/**
 * Servlet implementation class EditServlet
 */

public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Context ctx;
	private Users user;
	IUsersBeanRemote userBean;
	IRideOffersBeanRemote rideOffersBean;
	ICitiesBeanRemote cityBean;
	private String oldpass, newpass, carmodel, phone;
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
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
		// TODO Auto-generated method stub
		init(request);
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        
        try {
			userBean.updateRecord(Constant.loginBean.getId(), oldpass, newpass, carmodel, phone);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        response.sendRedirect("/CarPoolingApp/profile.jsp?ID="+Constant.loginBean.getId());
		
	}
	
	public void init(HttpServletRequest request){
		

	    Properties properties = new Properties();
        properties.put(Context.URL_PKG_PREFIXES, Constant.PKG_INTERFACES);
       

        try {
        	
        	ctx = new InitialContext(properties);

        	userBean = (IUsersBeanRemote) ctx.lookup(Constant.lookUpName("Users"));
        	rideOffersBean = (IRideOffersBeanRemote) ctx.lookup(Constant.lookUpName("RideOffers"));
        	cityBean = (ICitiesBeanRemote) ctx.lookup(Constant.lookUpName("Cities"));
        	
        } catch (NamingException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        } 
        
        oldpass = request.getParameterValues("oldpassword")[0];
        newpass = request.getParameterValues("newpassword")[0];
        carmodel = request.getParameterValues("carmodel")[0];
        phone = request.getParameterValues("phone")[0];
        
        System.out.println(oldpass + " " + newpass + " " + carmodel + " " + phone);
		
	}

}
