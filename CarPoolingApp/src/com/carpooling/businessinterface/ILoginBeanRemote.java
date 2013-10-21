package com.carpooling.businessinterface;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Remote;

@Remote
public interface ILoginBeanRemote {

	public int authenticate(String email, String password)
			throws UnsupportedEncodingException, NoSuchAlgorithmException;

	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);

	public boolean isAuthenticated();

	public void setAuthenticated(boolean authenticated);
	
	public boolean compareID(int userID);
	
	public int getId();

	public void setId(int id);

}
