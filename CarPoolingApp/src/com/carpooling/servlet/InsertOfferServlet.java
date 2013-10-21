package com.carpooling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
import com.carpooling.entities.Users;

/**
 * Servlet implementation class InsertOfferServlet
 */
public class InsertOfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Context ctx;
	
	private int offerID, userID, freeseats, deptID, arrID;
	private String user, date, time, description, deadline, departure, arrival;
	
	private IBiddingBeanRemote bidBean;
	private IRideOffersBeanRemote offersBean;
	private IUsersBeanRemote userBean;
	private ICitiesBeanRemote cityBean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertOfferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		init(request);
		
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
			
        
        
        
        try {
			int latestid = offersBean.publishOffer(Constant.loginBean.getId(), deptID, arrID, description, date, time, deadline,freeseats);
			
			
			out.print(latestid);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
       

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void init(HttpServletRequest request){
		
		Properties properties = new Properties();
		properties.put(Context.URL_PKG_PREFIXES, Constant.PKG_INTERFACES);

		try {
			ctx = new InitialContext(properties);
			
			offersBean = (IRideOffersBeanRemote) ctx.lookup(Constant.lookUpName("RideOffers"));
			cityBean = (ICitiesBeanRemote) ctx.lookup(Constant.lookUpName("Cities"));
	    	
	    	
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
		date = request.getParameterValues("date")[0];
		time = request.getParameterValues("time")[0];
		departure = request.getParameterValues("from")[0];
		arrival = request.getParameterValues("to")[0];
		description = request.getParameterValues("description")[0];
		deadline = request.getParameterValues("deadline1")[0]+ " " + request.getParameterValues("deadline2")[0];
		
		freeseats= Integer.parseInt(request.getParameterValues("freeseats")[0]);
		
		
		deptID = cityBean.findCityByName(departure).getID();
		arrID = cityBean.findCityByName(arrival).getID();
		
	}

}
