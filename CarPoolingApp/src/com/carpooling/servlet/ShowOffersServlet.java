package com.carpooling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carpooling.businessinterface.IBiddingBeanRemote;
import com.carpooling.businessinterface.ICitiesBeanRemote;
import com.carpooling.businessinterface.IRideOffersBeanRemote;
import com.carpooling.businessinterface.IUsersBeanRemote;
import com.carpooling.constants.Constant;
import com.carpooling.entities.RideOffers;
import com.carpooling.entities.Users;

/**
 * Servlet implementation class ShowOffersServlet
 */
@WebServlet("/ShowOffersServlet")
public class ShowOffersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Context ctx;
	private Users user;
	IUsersBeanRemote userBean;
	ICitiesBeanRemote cityBean;
	IRideOffersBeanRemote offerBean;
	private int userID, freeseats, offerID;
	private String amount;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowOffersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		init(request);
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        
        Users d = userBean.findUserByID(userID);

        
        if(Constant.loginBean !=null && d.getEmail().equals(Constant.loginBean.getUsername())){
        	List<RideOffers> offers = offerBean.findOffersByUserID(userID);
            
        	out.print("<h4>Rides Offered:</h4>" +
        			"<table id='offers' class='ui-widget ui-widget-content'>");
        	if(offers.size()==0)
            	out.print("<p>No bids made yet.</p>");
            else
            	out.print("<thead style=''><tr class='ui-widget-header '>" +
	        			"<th>From</th><th>To</th><th>Date</th><th>Time</th><th>Deadline</th><th>Details</th><th>Delete</th></tr>"+
	        		"</thead>");
        	
	        int i=0;
        
	        for(RideOffers r : offers){
	        	
	       	 out.print("<tr>" +
	       	 		"<td>" + cityBean.findCityByID(r.getDepartureID()).getName() + "</td>" +
	       	 		"<td>" + cityBean.findCityByID(r.getArrivalID()).getName() + "</td>" + 
	       	 		"<td>" + r.getDeptDate() + "</td>" +
	       	 		"<td>" + r.getDeptTime() + "</td>" +
	       	 		"<td>" + r.getDeadline() + "</td>" +
	       	 		"<td><a href='/CarPoolingApp/offer.jsp?offerID=" + r.getOfferID() +"'>Details</a></td>" +
	       	 		"<td><form action='/CarPoolingApp/DeleteOfferServlet' method='get'><input type='hidden' name='offerID' value='"+ r.getOfferID() +"'>" +
	       	 		"<input type='hidden' name='ID' value='"+ userID +"'>" +		
	       	 		"<input id='delete' type='submit' value='Delete'></form></td>"+
	       	 		"</tr>"
	       	 		); 
	            		
	       }
	        
	        out.print("</table>");
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
			cityBean = (ICitiesBeanRemote)  ctx.lookup(Constant.lookUpName("Cities"));
	    	offerBean = (IRideOffersBeanRemote) ctx.lookup(Constant.lookUpName("RideOffers"));
	    	userBean = (IUsersBeanRemote) ctx.lookup(Constant.lookUpName("Users"));
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    
		//freeseats = Integer.parseInt(request.getParameterValues("freeseats")[0]);
		
		userID = Integer.parseInt(request.getParameterValues("ID")[0]);
		
	}

}
