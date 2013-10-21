package com.carpooling.business.bidding;

import java.util.HashMap;



public class Bid {
	
	private int offerID;
	private HashMap bidder = new HashMap();
	
	public Bid(int offerID){
		this.offerID = offerID;
	}
	
	public Bid(int offerID, int userID, int amount){
		this.offerID = offerID;
		this.bidder.put(userID, amount);
	}
	
	public int getOfferID() {
		return offerID;
	}
	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}
	public HashMap getBidder() {
		return bidder;
	}
	public void setBidder(HashMap bidder) {
		this.bidder = bidder;
	}
	
	

}
