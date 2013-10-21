package com.carpooling.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name = "Ride_Offers")
@NamedQueries({
		
		@NamedQuery(
	    name="findOffersByDeparture",
	    query="SELECT r " +
	    "FROM Ride_Offers r " +
	    "WHERE r.deptDate = :date AND " +
	    "r.departureID = :departureID"
	    ),
	    
		@NamedQuery(
	    name="findOffersByArrival",
	    query="SELECT r " +
	    "FROM Ride_Offers r " +
	    "WHERE r.deptDate = :date AND " +
	    "r.arrivalID = :arrivalID" 
	    ),
	    
	    @NamedQuery(
	    	    name="findOffersByDeptArr",
	    	    query="SELECT r " +
	    	    "FROM Ride_Offers r " +
	    	    "WHERE r.deptDate = :date AND " +
	    	    "r.arrivalID = :arrivalID AND " +
	    	    "r.departureID = :departureID"
	    ),
		
		@NamedQuery(
				name="findOffersByDate",
	    	    query="SELECT r " +
	    	    "FROM Ride_Offers r " +
	    	    "WHERE r.deptDate = :date" 
				
				)
		
		
})


public class RideOffers implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	public RideOffers(){
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "OfferID")
	//@GeneratedValue(strategy=GenerationType.TABLE,generator="rideOfferIDGen")
	private int OfferID;
	
	@Column(name = "DriverID")
	private int driverID;
	
	@Column(name = "Date")
    @Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "Time")
    @Temporal(TemporalType.TIME)
	private Date time;
	
	@Column(name = "DeptDate")
    @Temporal(TemporalType.DATE)
	private Date deptDate;
	
	@Column(name = "DeptTime")
    @Temporal(TemporalType.TIME)
	private Date deptTime;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "DepartureID")
	private int departureID;
	
	@Column(name = "ArrivalID")
	private int arrivalID;
	
	@Column(name = "Deadline")
    @Temporal(TemporalType.TIMESTAMP)
	private Date deadline;
	
	@Column(name = "FreeSeats")
	private int freeSeats;
	
	
	@ManyToOne
	@JoinColumn(name = "driverID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Users user;
	
	@ManyToOne
	@JoinColumn(name = "departureID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Cities deptcity;
	
	@ManyToOne
	@JoinColumn(name = "arrivalID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Cities arrcity;


	public Users getDriver(){
		return user;
	}
	
	public Cities getDeptCity(){
		return deptcity;
	}
	
	public Cities getArrCity(){
		return arrcity;
	}
	

	public int getOfferID() {
		return OfferID;
	}

	public void setOfferID(int offerID) {
		OfferID = offerID;
	}

	public int getDriverID() {
		return driverID;
	}

	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}

	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDepartureID() {
		return departureID;
	}

	public void setDepartureID(int departureID) {
		this.departureID = departureID;
	}

	public int getArrivalID() {
		return arrivalID;
	}

	public void setArrivalID(int arrivalID) {
		this.arrivalID = arrivalID;
	}

	public Date getDeptDate() {
		return deptDate;
	}

	public void setDeptDate(Date deptDate) {
		this.deptDate = deptDate;
	}

	public Date getDeptTime() {
		return deptTime;
	}

	public void setDeptTime(Date deptTime) {
		this.deptTime = deptTime;
	}



	public Date getDeadline() {
		return deadline;
	}



	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}



	public int getFreeSeats() {
		return freeSeats;
	}



	public void setFreeSeats(int freeSeats) {
		this.freeSeats = freeSeats;
	}
	 
	
	
	

	

}
