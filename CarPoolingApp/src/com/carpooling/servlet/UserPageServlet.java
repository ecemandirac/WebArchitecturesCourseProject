package com.carpooling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carpooling.businessinterface.ICitiesBeanRemote;
import com.carpooling.businessinterface.IRideOffersBeanRemote;
import com.carpooling.businessinterface.IUsersBeanRemote;
import com.carpooling.constants.Constant;
import com.carpooling.entities.RideOffers;
import com.carpooling.entities.Users;

/**
 * Servlet implementation class DriverPageServlet
 */
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Context ctx;
	private Users user;
	IUsersBeanRemote userBean;
	IRideOffersBeanRemote rideOffersBean;
	ICitiesBeanRemote cityBean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        
        Enumeration en = request.getParameterNames();
        String paramName = (String) en.nextElement();
        
        int userID = Integer.parseInt(request.getParameter(paramName));
        
        Users d = userBean.findUserByID(userID);
        List<RideOffers> list = rideOffersBean.findOffersByUserID(userID);
       
        
        out.print("{ \"name\": \"" + d.getName() + 
        		"\", \"age\": \"" +  d.getAge() + 
        		"\", \"gender\": \"" + (d.getGender()==0 ? "Female" : "Male")+ 
        		"\", \"carmodel\": \"" +  d.getCarModel() + 
        		"\", \"phone\": \"" +  d.getPhone() + "\""   
        		
        		);
        
        
        Cookie[] cookies = request.getCookies();
        String user = "";
    	
    	if (cookies != null){
    		for (Cookie ck : cookies) {
    			if ("user".equals(ck.getName())) {
    				user = ck.getValue();

    			}
    		}
    	}
    	
    	if(Constant.loginBean !=null && d.getEmail().equals(Constant.loginBean.getUsername())){
    	    
	        if(Constant.loginBean.compareID(Integer.parseInt(request.getParameterValues("ID")[0]))){
				out.print(", \"addbutton\": \"<div class='new-offer-container'>" +
				"<input type='button' id='dialog_link' value='Add new offer'>"+
				"</div>"+
				"\", \"editbutton\": \"<div class='new-offer-container'>" +
				"<input type='button' id='editbutton' value='Edit your profile'>"+
				"</div>\"");
	        }
    	}
    	out.print("}");
        out.close();
        
        System.out.println(
        		"{ \"name\": \"" + d.getName() + 
        		"\", \"age\": \"" +  d.getAge() + 
        		"\", \"gender\": \"" + (d.getGender()==0 ? "Female" : "Male")+ 
        		"\", \"carmodel\": \"" +  d.getCarModel() + 
        		"\", \"phone\": \"" +  d.getPhone() + 
        		
				"\", \"addbutton\": \"<div class='new-offer-container'>" +
				"<input type='button' id='dialog_link' value='Add new offer'>"+
				"</div>" + 
				"\", \"editbutton\": \"<div class='new-offer-container'>" +
				"<input type='button' id='editbutton' value='Edit your profile'>"+
				"</div>\""
        		);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void init(){
		

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

		
	}
	
	public Users getUserByID(String id){
		
		user = userBean.findUserByID(Integer.parseInt(id));
        
		return user;
	}
	
	

}
