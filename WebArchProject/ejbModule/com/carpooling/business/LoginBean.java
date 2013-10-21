package com.carpooling.business;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.carpooling.businessinterface.ILoginBeanRemote;
import com.carpooling.entities.Users;

/**
 * Session Bean implementation class LoginBean
 */
/**
 * @author ece
 *
 */
@Stateful
@LocalBean
public class LoginBean implements ILoginBeanRemote {

    /**
     * Default constructor. 
     * 
     * 
     */
	@PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
	
	int count=0;
	private String username, password;
	private int id;
	private boolean authenticated=false;
	
    public LoginBean() {
        // TODO Auto-generated constructor stub
    	System.out.println("Login Bean no:" + Math.random() + " is created." );
    	
    }
    
    @Override
	public String getUsername() {
		return username;
	}
    
    @Override
	public void setUsername(String username) {
		this.username = username;
	}
    
    @Override
	public String getPassword() {
		return password;
	}
    
    @Override
	public void setPassword(String password) {
		this.password = password;
	}
	
    @Override
	public boolean isAuthenticated() {
		return authenticated;
	}
    
    @Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
    
    @Override
    public boolean compareID(int userID){
    	
    	Users user = entityManager.find(Users.class, userID);
    	//System.out.println(user.getEmail() + ",  email:" + username);
    	
    	if(username.equals(user.getEmail())) return true;
    	else return false;
    }
    
    
    @Override
	public int getId() {
		return id;
	}

    @Override
	public void setId(int id) {
		this.id = id;
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
		
		username=email;
		this.password = password;
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
	
    

}
