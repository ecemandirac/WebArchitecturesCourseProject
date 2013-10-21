package com.carpooling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.carpooling.businessinterface.IRideOffersBeanRemote;
import com.carpooling.constants.Constant;
import com.carpooling.entities.Cities;

/**
 * Servlet implementation class HelloWorld
 */
//@WebServlet("/HelloWorld")
public class FindCityServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Context ctx; 
    
    
    private static final String[] COUNTRIES = new String[] {
        "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
        "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
        "Armenia", "Aruba", "Australia", "Austria"};
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindCityServlet() {
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
			
        Properties properties = new Properties();
        properties.put(Context.URL_PKG_PREFIXES, Constant.PKG_INTERFACES);


        try {
        	
        	ctx = new InitialContext(properties);

        	ICitiesBeanRemote cityBean = (ICitiesBeanRemote) ctx.lookup(Constant.lookUpName("Cities"));

        	List<Cities> cityList = cityBean.findAll();


        	String arrayObj = createJSONArray(request, cityList);
        	System.out.println(arrayObj);
        	out.println(arrayObj);
        	out.close();



        } catch (NamingException e) {
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
	
	
	
	//IRideOffersBeanRemote bean = (IRideOffersBeanRemote) ctx.lookup(lookupName);
	//List<RideOffers> offersList = bean.findAllOffers();

	/*for (RideOffers r : offersList){

			System.out.println("Offer ID: " + r.getOfferID() +
					" DriverID: " + r.getDriverID() + 
					" Date: " + r.getDate() +
					" Time: " + r.getTime() +
					" From: " + r.getDepartureID() +
					" To: " + r.getArrivalID()
					);
		}*/


}
