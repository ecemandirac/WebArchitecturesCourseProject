package com.carpooling.business;

import java.util.List;

import com.carpooling.businessinterface.ICitiesBeanRemote;
import com.carpooling.entities.Cities;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class CitiesBean
 */
@Stateless
@LocalBean
public class CitiesBean implements ICitiesBeanRemote {

	@PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
	
	
    /**
     * Default constructor. 
     */
    public CitiesBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Cities> findAll() {
		// TODO Auto-generated method stub
		String selectAll = "Select c from " + Cities.class.getName() + " c";
		
		List<Cities> returnList = (List<Cities>) entityManager.createQuery(selectAll).getResultList();
		
		if(returnList == null)
		System.out.println("null list");
		if(returnList.size() == 0)
			System.out.println("size is 0");
		
		System.out.println("hi there!");
		
		return returnList;
	}

	@Override
	public Cities findCityByID(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Cities.class, id);
	}

	@Override
	public Cities findCityByName(String name) {
		// TODO Auto-generated method stub
		
		String select = "Select c from " + Cities.class.getName() + " c where c.name='" + name + "'";
		return (Cities) entityManager.createQuery(select).getResultList().get(0);
	}

}
