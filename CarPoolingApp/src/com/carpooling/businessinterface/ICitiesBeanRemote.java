package com.carpooling.businessinterface;

import java.util.List;

import javax.ejb.Remote;

import com.carpooling.entities.Cities;

@Remote
public interface ICitiesBeanRemote {

	public List<Cities> findAll();
	public Cities findCityByID(int id);
	public Cities findCityByName(String Name);

}
