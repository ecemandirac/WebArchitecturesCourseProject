package com.carpooling.servlet;

import java.io.IOException;
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
 * Servlet implementation class DeleteOfferServlet
 */
@WebServlet("/DeleteOfferServlet")
public class DeleteOfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Context ctx;
	IRideOffersBeanRemote rideOffersBean;
	int offerID, ID;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOfferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		init(request);
		rideOffersBean.deleteOffer(offerID);
		response.sendRedirect("/CarPoolingApp/profile.jsp?ID=" + ID);
		
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
        	rideOffersBean = (IRideOffersBeanRemote) ctx.lookup(Constant.lookUpName("RideOffers"));

        	
        } catch (NamingException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        } 
        
        offerID = Integer.parseInt(request.getParameterValues("offerID")[0]);
        ID = Integer.parseInt(request.getParameterValues("ID")[0]);
        
        
        System.out.println(offerID);
		
	}

}
