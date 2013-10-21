package com.carpooling.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name = "Cities")

public class Cities implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Cities(){
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	//@GeneratedValue(strategy=GenerationType.TABLE,generator="rideOfferIDGen")
	private int ID;
	
	@Column(name = "Name")
	private String name;
	
	@OneToMany
	private List<RideOffers> offer;
	
	public List<RideOffers> getRideOffer(){
		return offer;
	}
	

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
