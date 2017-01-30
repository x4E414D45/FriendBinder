package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.User;



public interface UserService {
	
	
	void saveUser(User user);

	boolean isUserExist(User user);


	String passwordEncryption(String password);
	
	
}
