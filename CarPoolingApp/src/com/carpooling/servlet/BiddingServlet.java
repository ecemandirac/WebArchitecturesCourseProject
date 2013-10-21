package com.carpooling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import com.carpooling.businessinterface.IBiddingBeanRemote;
import com.carpooling.businessinterface.IRideOffersBeanRemote;
import com.carpooling.businessinterface.IUsersBeanRemote;
import com.carpooling.constants.Constant;
import com.carpooling.entities.RideOffers;
import com.carpooling.entities.Users;

/**
 * Servlet implementation class BiddingServlet
 */

public class BiddingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Context ctx;
	
	private int offerID, userID, amount, increment;
	private String user;
	
	private IBiddingBeanRemote bidBean;
	private IRideOffersBeanRemote offersBean;
	private IUsersBeanRemote userBean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BiddingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		init();
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
		
        
        
        Cookie[] cookies = request.getCookies();
    	
    	if (cookies != null){
    		for (Cookie ck : cookies) {
    			if ("offerID".equals(ck.getName())) {
    				offerID = Integer.parseInt(ck.getValue());

    			}
    			
    			else if("user".equals(ck.getName())){
    				user = ck.getValue();
    			}
    		}
    	}
    	
    	System.out.println("user: " + user);
    	
    	if(Constant.loginBean !=null){
    	Users u = userBean.findUserByEmail(Constant.loginBean.getUsername());
        userID = u.getID();
        
       // offerID=Integer.parseInt(request.getParameterValues("offerID")[0]);
       // userID=Integer.parseInt(request.getParameterValues("userID")[0]);
        amount=Integer.parseInt(request.getParameterValues("amount")[0]);
        
        System.out.println("form submitted: " + amount + " euro bidded.");
       try{ 	
       
        	RideOffers offer = offersBean.getOfferByID(offerID);
        	Date deadline = offer.getDeadline();
        	Date curTime = Calendar.getInstance().getTime();
        	
        	System.out.println(deadline + " " + curTime);
        	if(curTime.before(deadline)){
        	
	        	
	        	int returnVal = bidBean.bid(offerID, userID, amount);
	        	
	        	if(returnVal == 1)
	        	out.println("User " + userID + " has bidded " + amount + " euro for the offer " + offerID);
	        	
	        	else out.println("Please bid with higher than " + bidBean.getInstantHighestBid(offerID));
        	}
        	else out.println("You cannot bid anymore the deadline " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(deadline) + " has passed.");
        	
        
        } catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	}else{
    		
    		out.println("You have to be logged in. \nNot registered yet? <a href='/CarPoolingApp/registration.jsp'>Sign up</a>");
    	}

	
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
			
			offersBean = (IRideOffersBeanRemote) ctx.lookup(Constant.lookUpName("RideOffers"));
	    	bidBean = (IBiddingBeanRemote) ctx.lookup(Constant.lookUpName("Bidding"));
	    	userBean = (IUsersBeanRemote)  ctx.lookup(Constant.lookUpName("Users"));
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
		
	}

}
