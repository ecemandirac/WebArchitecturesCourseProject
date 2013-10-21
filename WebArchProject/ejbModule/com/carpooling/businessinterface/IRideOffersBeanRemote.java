package com.carpooling.businessinterface;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Remote;

import com.carpooling.entities.RideOffers;


@Remote
public interface IRideOffersBeanRemote {
	
	public List<RideOffers> findAllOffers();
	public List<RideOffers> findOffersBy(String Date, int destID, int arrivalID) throws ParseException;
	public RideOffers getOfferByID(int offerID);
	public List<Object> findAllOffersJoin();
	public int deleteOffer(int offerID);
	List<RideOffers> findOffersByUserID(int userID);
	public int publishOffer(int driverID, int departureID, int arrivalID,
			String description, String deptDate, String deptTime,
			String deadline, int freeSeats) throws ParseException;
	
	
	

}
