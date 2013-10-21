package com.carpooling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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

import com.carpooling.businessinterface.IBiddingBeanRemote;
import com.carpooling.businessinterface.ICitiesBeanRemote;
import com.carpooling.businessinterface.IRideOffersBeanRemote;
import com.carpooling.businessinterface.IUsersBeanRemote;
import com.carpooling.constants.Constant;
import com.carpooling.entities.RideOffers;

/**
 * Servlet implementation class OfferPageServlet
 */
@WebServlet("/OfferPageServlet")
public class OfferPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Context ctx;
	IUsersBeanRemote userBean;
	IRideOffersBeanRemote rideOffersBean;
	ICitiesBeanRemote cityBean;
	IBiddingBeanRemote bidBean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OfferPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        
        Enumeration en = request.getParameterNames();
        String paramName = (String) en.nextElement();
        
        
        //delete previous offerIDs
        Cookie[] cookies = request.getCookies();
        String prefValue = "";
    	
    	if (cookies != null){
    		for (Cookie ck : cookies) {
    			if ("offerID".equals(ck.getName())) {
    				ck.setMaxAge(0);

    			}
    		}
    	}
        
        Cookie cookie = new Cookie("offerID", request.getParameter(paramName));
        cookie.setMaxAge(30 * 60);
        response.addCookie(cookie);
        
        int offerID = Integer.parseInt(request.getParameter(paramName));
        RideOffers r = rideOffersBean.getOfferByID(offerID);
        
        out.print("{ \"from\": \"" + cityBean.findCityByID(r.getDepartureID()).getName() + 
        		"\", \"to\": \"" +  cityBean.findCityByID(r.getArrivalID()).getName() + 
        		"\", \"date\": \"" + r.getDeptDate()+ 
        		"\", \"time\": \"" +  r.getDeptTime() + 
        		"\", \"driver\": \"<a href='/CarPoolingApp/profile.jsp?ID=" + r.getDriverID() +"'>"+ userBean.findUserByID(r.getDriverID()).getName()+"</a>"+ 
        		"\", \"freeseats\": \"" +  r.getFreeSeats() + 
        		"\", \"description\": \"" + r.getDescription() + 
        		"\", \"deadline\": \"" +  r.getDeadline()  +
        		/*"\", \"bids\": \"<form id='bid'>" +
        				"<input type='text' name='amount'>" +
        				"<input type='submit' id='submit' name='submit' value='Bid'" +
        				"</form>" +*/
        				"\"}" 
        		);
        out.close();
        
        
         
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
        	bidBean = (IBiddingBeanRemote) ctx.lookup(Constant.lookUpName("Bidding"));
        	
        } catch (NamingException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        } 
	}
	

}
