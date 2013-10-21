package com.carpooling.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Bids")

public class Bids implements Serializable{
	
	private static final long serialVersionUID = -112312312L;
	
	public Bids(){
		super();
	}

	@Id
	@Column(name = "ID")
	private int ID;
	
	@Column(name = "OfferID")
	private int offerID;
	
	@Column(name = "UserID")
	private int userID;
	
	@Column(name = "Amount")
	private int amount;
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getOfferID() {
		return offerID;
	}
	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
}
