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
import com.carpooling.businessinterface.IRideOffersBeanRemote;
import com.carpooling.businessinterface.IUsersBeanRemote;
import com.carpooling.constants.Constant;
import com.carpooling.entities.Bids;
import com.carpooling.entities.Users;

/**
 * Servlet implementation class ShowBidsServlet
 */
@WebServlet("/ShowBidsServlet")
public class ShowBidsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Context ctx;
	private Users user;
	IUsersBeanRemote userBean;
	IBiddingBeanRemote bidBean;
	IRideOffersBeanRemote offerBean;
	private int userID, freeseats, offerID;
	private String amount;
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowBidsServlet() {
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
        
        freeseats = offerBean.getOfferByID(offerID).getFreeSeats();
        List<Bids> bids = bidBean.getHighestBids(freeseats, offerID);
        
        if(bids.size()==0)
        	out.print("<p>No bids made yet.</p>");
        else
        	out.print("<thead><tr class='ui-widget-header '>" +
        			"<th>User</th><th>Amount</th></tr>" +
        			"</thead>");
        
        int size;
        if(bids.size()<freeseats) size=bids.size();
        else size = freeseats;
        		
        for(int i=0 ; i<size ; i++){
        	out.println( "<tr>" +
                    "<td><a href='/CarPoolingApp/profile.jsp?ID=" + bids.get(i).getUserID() + "'>" 
        			+ userBean.findUserByID(bids.get(i).getUserID()).getName() + "</a></td>" + 
                    "<td>" + bids.get(i).getAmount() + "</td>" + 
        			"</tr>" );
        	
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
			userBean = (IUsersBeanRemote)  ctx.lookup(Constant.lookUpName("Users"));
	    	bidBean = (IBiddingBeanRemote) ctx.lookup(Constant.lookUpName("Bidding"));
	    	offerBean = (IRideOffersBeanRemote) ctx.lookup(Constant.lookUpName("RideOffers"));
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    
		//freeseats = Integer.parseInt(request.getParameterValues("freeseats")[0]);
		offerID = Integer.parseInt(request.getParameterValues("offerID")[0]);
		
	}

}
