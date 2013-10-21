package com.carpooling.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity(name = "Users")

public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Users(){
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private int ID;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Age")
	private int age;
	
	@Column(name = "Gender")
	private int gender;
	
	@Column(name = "Phone")
	private String phone;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Car_model")
	private String carModel;
	
	
	@OneToMany(mappedBy = "driverID")
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCarModel() {
		return carModel;
	}


	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}


	

}
