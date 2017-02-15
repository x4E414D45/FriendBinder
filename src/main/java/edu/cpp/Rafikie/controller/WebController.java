package edu.cpp.Rafikie.controller;
//import edu.cpp.Rafikie.model.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import edu.cpp.Rafikie.data.Register;
import edu.cpp.Rafikie.data.User;
import edu.cpp.Rafikie.data.UserDetails;
import edu.cpp.Rafikie.data.Login;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.cpp.Rafikie.App;
import edu.cpp.Rafikie.data.provider.UserManager;

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {
	
	User user=new User();
	UserDetails userDetail=new UserDetails();


	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value = "/getEmail", method = RequestMethod.GET)
	public User getEmail()
	{
		return user;
		
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes="application/json")
	public User test( @RequestBody String login) {
		//User user=new User();
		 Gson gson = new GsonBuilder().create();
		 Login loginDetails=gson.fromJson(login, Login.class);
		 loginDetails.setPassword(userManager.passwordEncryption(loginDetails.getPassword()));
		 List<String> list=userManager.isUserExist(loginDetails.getEmail());
		 if (list.contains(loginDetails.getPassword())) {
			 user.setName(list.get(2));
			 return user;
			 //return true;
			     
		     }
		 return user;
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes="application/json")
	public  boolean register( @RequestBody String registerDetails) {
	System.out.println("test");
	 Gson gson = new GsonBuilder().create();
	 Register register=gson.fromJson(registerDetails, Register.class);
	 register.setPassword(userManager.passwordEncryption(register.getPassword()));
	 List<String> list=new ArrayList<>();
	 list=userManager.isUserExist(register.getEmail());
	 
	 if (list.isEmpty()) {
		   userManager.register(register);
		   user.setName(register.getEmail());
	        return true;
		
        
     }
	 else
	 {
		 return false;

	 }
		 
	
	}
	
	@RequestMapping(value = "/userDetails", method = RequestMethod.POST, consumes="application/json")
	public  boolean insertUserDetails( @RequestBody String userDetails) throws JsonParseException, JsonMappingException, IOException{
	
		userManager.insertUserDetails(userDetails);
		//userManager.insertUserDetails(userDetails);
	//Gson gson = new GsonBuilder().create();
	 //userDetail=gson.fromJson(userDetails, UserDetails.class);
	return false;	 
	
	}
	@RequestMapping(value = "/fetchUserDetails", method = RequestMethod.POST,consumes="application/json")
	public UserDetails fetchUserDetails(@RequestBody String userEmail)
	{
		userDetail=userManager.fetchUserDetails(userEmail);
		return userDetail;
	}
	
	
		
	@RequestMapping(value = "/cs580/submit", method = RequestMethod.GET)
	String alert() {
		return "<html><body onload = \"alert('Successfully submitted the custom form')\"> </body></html>";
		
	}
	
	
	@RequestMapping(value = "/cs580/display", method = RequestMethod.GET)
	String getWebPageItems() throws IOException{
	Document doc = Jsoup.connect("http://localhost:8080/cs580/home").get();
	Elements newsHeadLines = doc.select("div ul li");
	System.out.println(newsHeadLines);
	return newsHeadLines.toString();
	 
	 
	}

	}

	

	 
	 

	
