package com.carpooling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carpooling.businessinterface.ICitiesBeanRemote;
import com.carpooling.businessinterface.IUsersBeanRemote;
import com.carpooling.businessinterface.IRideOffersBeanRemote;
import com.carpooling.constants.Constant;
import com.carpooling.entities.Cities;
import com.carpooling.entities.RideOffers;

/**
 * Servlet implementation class RideOffersServlet
 */

public class RideOffersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Context ctx;
	private String date, departure, arrival;
	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RideOffersServlet() {
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
			
        Properties properties = new Properties();
        properties.put(Context.URL_PKG_PREFIXES, Constant.PKG_INTERFACES);
        
        
        date = request.getParameterValues("date")[0];
        departure = request.getParameterValues("departure")[0];
        arrival = request.getParameterValues("arrival")[0];

        
       
        try {
        	
        	ctx = new InitialContext(properties);

        	IRideOffersBeanRemote bean = (IRideOffersBeanRemote) ctx.lookup(Constant.lookUpName("RideOffers"));
        	ICitiesBeanRemote cityBean = (ICitiesBeanRemote) ctx.lookup(Constant.lookUpName("Cities"));
        	IUsersBeanRemote driverBean = (IUsersBeanRemote) ctx.lookup(Constant.lookUpName("Users"));
        	
        	int deptID, arrvID;
        	if(departure == "") deptID = -1; 
        	else deptID = cityBean.findCityByName(departure).getID();
        	
        	if(arrival == "") arrvID = -1;
        	else arrvID = cityBean.findCityByName(arrival).getID();
        	
        	
        	System.out.println("RideOffersServlet: " + date + " " + deptID +" " + arrvID);
        	List<RideOffers> rideOffersList = bean.findOffersBy(date, deptID, arrvID);

        	if(rideOffersList.size() == 0) 
        		out.println("<h3>No such offer</h3>");
        	
        	for(RideOffers r : rideOffersList){
        		
        		//out.println("<p>Date: " + r.getDate().toString() + " ,  DriverID: " +  r.getDriverID() + " , description: " + r.getDescription() + " </p>");
        		out.println("<h3>Offer no: "+ r.getOfferID() + "</h3>" + 
        					"<div><form action='profile.jsp'>" +
        					"<ul><li>Posted on: " + r.getDate() +
        					"</li><li>Posted at: " + r.getTime() +
        					"</li><li>Number of Free Seats: " + r.getFreeSeats() +
        					" </li><li> Description: " + r.getDescription() + 
        					" </li><input type='hidden' name='ID' value='"+ r.getDriverID() +"'>" +
        					"<li><input type='submit' value='User Profile'></li></form>" +
        					"<form action='offer.jsp'>" +
        					"<input type='hidden' name='offerID' value='"+ r.getOfferID() +"'>" +
        					"<li><input type='submit' value='Get more details'></li></form>" +
        					"</ul></form>" +
        					"</div>");
        	}
        	
        	
         	out.close();




        } catch (NamingException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
			
        
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}
	
	
	private String createJSONArray(HttpServletRequest request, List<Cities> list){
		
		StringBuffer arrayObj = new StringBuffer("[");
        String query = request.getParameter("term");
        System.out.println(query);
        query = query.toLowerCase();
        for(int i=0; i<list.size(); i++) {
        	String country = list.get(i).getName().toLowerCase();
        	if(country.startsWith(query)) {
        		// arrayObj.append("{value: \"" + COUNTRIES[i] + "\", label: \"" + COUNTRIES[i] +"\"},");
        		arrayObj.append("\"" + list.get(i).getName()  + "\", ");
        	}
        }


        //arrayObj.deleteCharAt(arrayObj.lastIndexOf(","));
        arrayObj.replace(arrayObj.length()-2, arrayObj.length()-1, "]");
        //arrayObj.append("]");
        return arrayObj.toString();
		
	}
	
	
	

}
