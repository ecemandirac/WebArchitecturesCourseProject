package com.carpooling.businessinterface;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Remote;

@Remote
public interface ILoginBeanRemote {

	int authenticate(String email, String password)
			throws UnsupportedEncodingException, NoSuchAlgorithmException;

	String getUsername();

	void setUsername(String username);

	String getPassword();

	void setPassword(String password);

	boolean isAuthenticated();

	void setAuthenticated(boolean authenticated);

	boolean compareID(int userID);

	public int getId();

	public void setId(int id);

}
