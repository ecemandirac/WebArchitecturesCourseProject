package com.carpooling.businessinterface;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Remote;

import com.carpooling.entities.Users;

@Remote
public interface IUsersBeanRemote {
	
	public Users findUserByID(int id);
	int addNewRecord(int id, String name, int age, int gender, String cityID,
			String email, String password, String carModel) throws UnsupportedEncodingException, NoSuchAlgorithmException;
	public int deleteRecord(int id);
	public int authenticate(String email, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;
	public Users findUserByEmail(String email);
	public void updateRecord(int ID, String oldpassword, String newpassword, String carmodel,
			String phone) throws NoSuchAlgorithmException, UnsupportedEncodingException;


}
