package com.carpooling.business;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.carpooling.businessinterface.IRideOffersBeanRemote;
import com.carpooling.entities.Cities;
import com.carpooling.entities.RideOffers;
import com.carpooling.entities.Users;

/**
 * Session Bean implementation class RideOffersBean
 */
@Stateless
@LocalBean
public class RideOffersBean implements IRideOffersBeanRemote {


	private int Min = 10000;
	private int Max = 99999;
		
	
	@PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public RideOffersBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<RideOffers> findAllOffers() {
		// TODO Auto-generated method stub
		
		String select = "Select r from " + RideOffers.class.getName() + " r";
		
		Query query = entityManager.createQuery(select);
		
		List<RideOffers> rideOffersList = (List<RideOffers>) query.getResultList();
		

		return rideOffersList;
				
	}
	
	
	
	

	@Override
	public int publishOffer(int driverID,  int departureID, int arrivalID, String description,
			String deptDate, String deptTime, String deadline, int freeSeats) throws ParseException {

		
		
		
		String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		
		
		DateFormat formatterDate, formatterTime, formatterDeadline;
		formatterDate = new SimpleDateFormat("yyyy-MM-dd");
		formatterTime = new SimpleDateFormat("HH:mm:ss");
		formatterDeadline = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		RideOffers rideOffer = new RideOffers();
		rideOffer.setDate((Date) formatterDate.parse(date));
		rideOffer.setTime((Date) formatterTime.parse(time));
		rideOffer.setArrivalID(arrivalID);
		rideOffer.setDepartureID(departureID);
		rideOffer.setDescription(description);
		rideOffer.setDeptDate((Date) formatterDate.parse(deptDate));
		rideOffer.setDeptTime((Date) formatterTime.parse(deptTime));
		rideOffer.setDriverID(driverID);
		rideOffer.setDeadline((Date) formatterDeadline.parse(deadline));
		rideOffer.setFreeSeats(freeSeats);
		
		//System.out.println("*"+formatterDeadline.format(rideOffer.getDeadline())+"*");
		
		entityManager.persist(rideOffer);
		entityManager.flush();
		
		
		Query query = entityManager.createNativeQuery("SELECT LAST_INSERT_ID()");
		int lastID = ((BigInteger) query.getSingleResult()).intValue();
		/*
		//Get last inserted ID
		

		//Create Bid table for the newly inserted ride offer using this ID
		query = null;
		String tableName = "BID_" + String.valueOf(lastID);
		String createTable = "CREATE TABLE " + tableName + " (" +
					"ID INT NOT NULL AUTO_INCREMENT, " +
					"UserID INT NOT NULL, " +
					"Amount INT NOT NULL, " +
					"Increment INT," +
					"PRIMARY KEY (ID), " +
					"CONSTRAINT " + tableName + "_USER_FK " +
							"FOREIGN KEY " + tableName + "_USER_FK (UserID) REFERENCES Drivers (ID) " +
							"ON DELETE CASCADE ON UPDATE CASCADE" +
							")";
		System.out.println(createTable);
		
		entityManager.createNativeQuery(createTable).executeUpdate();
		*/
		return lastID;
	}
	
	

	
	@Override
	public RideOffers getOfferByID(int OfferID) {
		// TODO Auto-generated method stub
		
		
			return (RideOffers) entityManager.find(RideOffers.class, OfferID);

	}
	
	private int genRandomNumber(){
		
		int rnd = Min + (int) Math.random() * (Max - Min);
		
		return rnd;
	}

	@Override
	public List<RideOffers> findOffersBy(String strDate, int departureID, int arrivalID) throws ParseException{
		// TODO Auto-generated method stub
		DateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date;

		date = (Date) formatter.parse(strDate);
		List<RideOffers> offers = new ArrayList<RideOffers>();

		if(arrivalID == -1 && departureID != -1){
			offers = (List<RideOffers>) entityManager.createNamedQuery(
				    "findOffersByDeparture")
				    .setParameter("date", date)
				    .setParameter("departureID", departureID)
				    .getResultList();
			
		}else if(departureID == -1 && arrivalID != -1){
			
			offers = (List<RideOffers>) entityManager.createNamedQuery(
				    "findOffersByArrival")
				    .setParameter("date", date)
				    .setParameter("arrivalID", arrivalID)
				    .getResultList();
			
		}else if(departureID != -1 && arrivalID != -1){
			
			offers = (List<RideOffers>) entityManager.createNamedQuery(
				    "findOffersByDeptArr")
				    .setParameter("date", date)
				    .setParameter("departureID", departureID)
				    .setParameter("arrivalID", arrivalID)
				    .getResultList();
			
		}
		else if(departureID == -1 && arrivalID == -1 ){
			
			
			offers = (List<RideOffers>) entityManager.createNamedQuery(
						    "findOffersByDate")
						    .setParameter("date", date)
						    .getResultList();
			
		}
		
		
		

		
		
		return offers;
	}
	
	@Override public List<RideOffers> findOffersByUserID(int userID){
		
		List<RideOffers> list = new ArrayList<RideOffers>();
		
		try{
			Query query = entityManager.createQuery("SELECT d FROM Ride_Offers d WHERE d.driverID = :driverID").setParameter("driverID", userID);
			list = (List<RideOffers>) query.getResultList();
			
		}catch (NoResultException nre){
			return null;
		}	
		
		if(list == null) return null;
		else{
			return list;
		}
		
	}

	@Override
	public List findAllOffersJoin() {
		
		String select3 = "SELECT r.OfferID, c1.name FROM " + RideOffers.class.getName() + " r" +
				", " + Cities.class.getName() + " c1 WHERE r.departureID = 13 AND r.departureID = c1.ID";
		
		List result = entityManager.createQuery(select3).getResultList();  

		return result;
        
		
	}
	
	

	@Override
	public int deleteOffer(int offerID) {
		
		RideOffers offerToRemove = entityManager.find(RideOffers.class, offerID);
		
		if(offerToRemove == null) return 0;
		else {
			entityManager.remove(offerToRemove);
			/*
			String deleteQuery = "DROP TABLE BID_"+ offerID;
			entityManager.createNativeQuery(deleteQuery).executeUpdate();
			*/
			return 1;
		}
		
	}
	
	
	
	

	
	
	
}
