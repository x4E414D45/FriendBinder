package com.websystique.springmvc.controller;
 
import java.util.List;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springmvc.core.MongoDBConnection;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.UserService;
 
@RestController
public class HelloWorldRestController {
 
    @Autowired
    UserService userService; 
    @Autowired
    MongoDBConnection connection;//Service which will do all data retrieval/manipulation work
 
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public boolean createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
    	user.setPassword(userService.passwordEncryption(user.getPassword()));
        System.out.println("Creating User " + user.getEmail());
 
        if (userService.isUserExist(user)) {
            System.out.println("A User with mail id " + user.getEmail() + " already exist");
        }
        //System.out.println("check");
        else
        userService.saveUser(user);
        return true;
    }
    
    @RequestMapping(value = "/user/exist", method = RequestMethod.GET)
    public boolean userExist(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        if (userService.isUserExist(user)) {
        	return true;
        }
        return false;
    }
    
    @RequestMapping(value = "/similarity", method = RequestMethod.GET)
    public double similarity() {
    	Double d_a[] = {1.0, 2.0, 3.0};
    	Double d_b[] = {2.0, 2.0, 5.0};
    	RealVector a = new ArrayRealVector(d_a);
    	RealVector b = new ArrayRealVector(d_b);
    	return a.cosine(b);
    }
 
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public void searchUser(@RequestBody User user, UriComponentsBuilder ucBuilder)
    {
        if(userService.isUserExist(user))
        {
            System.out.println(user.getName());
        }
    }
 
}