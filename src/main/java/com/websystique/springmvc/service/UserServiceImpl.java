package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static Hashtable<String, User> map =new Hashtable<String,User>();

	@Override
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		String s=user.getEmail();
		map.put(user.getEmail(),user);
		System.out.println(map);
	}
	
	public boolean isUserExist(User user)
	{
		if (map!=null)
		{
		if(map.containsKey(user.getEmail()))
		return true;
		}
			return false;
		
	}
	
	public void deleteUser(User user) {
		map.remove(user.getEmail());
		System.out.println(map);
	}
	
}
