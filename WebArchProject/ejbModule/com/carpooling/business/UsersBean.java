package com.carpooling.business;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.carpooling.businessinterface.IUsersBeanRemote;
import com.carpooling.entities.Users;

/**
 * Session Bean implementation class UsersBean
 */
@Stateless
@LocalBean
public class UsersBean implements IUsersBeanRemote {

	
	@PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public UsersBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Users findUserByID(int id) {
		// TODO Auto-generated method stub
		return (Users) entityManager.find(Users.class, id);
	}
	
	@Override
	public Users findUserByEmail(String email){
		
		Query query = entityManager.createQuery("SELECT u FROM Users u where u.email=:email").setParameter("email", email);
		return (Users) query.getSingleResult();
	}

	@Override
	public int addNewRecord(int id, String name, int age, int gender, String phone, 
			String email, String password, String carModel) throws UnsupportedEncodingException, NoSuchAlgorithmException {

		Users driver = new Users();
		
		try{
		Query query = entityManager.createQuery("SELECT d FROM Users d WHERE d.email = :email").setParameter("email", email);
		driver = (Users) query.getSingleResult();
		}
		catch (NoResultException nre){
			//Ignore this because as per your logic this is ok!
		
		
			StringBuffer sb = new StringBuffer();
			Users newDriver = new Users();
			//newDriver.setID(id);
			newDriver.setName(name);
			newDriver.setAge(age);
			newDriver.setGender(gender);
			newDriver.setPhone(phone);
			newDriver.setEmail(email);
			newDriver.setCarModel(carModel);
			
			
			byte[] bytesOfMessage;
			
			bytesOfMessage = password.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);

			
			for(byte b : thedigest) {
	            sb.append(String.format("%02x", b));
	        }
			
			newDriver.setPassword(sb.toString());

			System.out.println("original: " + password + "md5 password: " + sb.toString());
			
			entityManager.persist(newDriver);
			entityManager.flush();
			
			return 1;
		}
		
		
			return 0;
		
	}

	@Override
	public int deleteRecord(int id) {

		Users driver = (Users) entityManager.find(Users.class, id);
		if(driver == null){
			
			return 0;
		}
		else{
			entityManager.remove(driver);
			return 1;
		}
	}

	@Override
	public int authenticate(String email, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		Users user = new Users();
		System.out.println("email: " + email + " password: " + password);
		
		try{
			Query query = entityManager.createQuery("SELECT d FROM Users d WHERE d.email = :email").setParameter("email", email);
			user = (Users) query.getSingleResult();
		}
		catch (NoResultException nre){
			return 0;
		}
		
		StringBuffer sb = new StringBuffer();
    	byte[] bytesOfMessage;
		
		bytesOfMessage = password.getBytes("UTF-8");
		MessageDigest md;
		
			md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			for(byte b : thedigest) {
	            sb.append(String.format("%02x", b));
			}

		
		if(user.getPassword().equals(sb.toString()))
			return 1;
		
		else
			return -1;
		
	}
	
	@Override
	public void updateRecord(int ID, String oldpassword, String newpassword, String carmodel, String phone) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		
		StringBuffer sb = new StringBuffer();
    	byte[] bytesOfMessage;
		
		bytesOfMessage = newpassword.getBytes("UTF-8");
		MessageDigest md;
		
			md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			for(byte b : thedigest) {
	            sb.append(String.format("%02x", b));
			}
		entityManager.createNativeQuery("UPDATE Users" +
				" SET Password='"+ sb.toString() + "', " +
				"Car_model='" + carmodel + "', " +
						"Phone='" + phone + "' WHERE ID="+String.valueOf(ID)).executeUpdate();
		
		
	}
	
	
	
	
	
	

}
