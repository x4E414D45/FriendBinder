package com.websystique.springmvc.controller;
 
import java.util.List;

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

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.UserService;
 
@RestController
public class HelloWorldRestController {
 
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
 
    
   
     
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public boolean createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getEmail());
 
        if (userService.isUserExist(user)) {
            System.out.println("A User with mail id " + user.getEmail() + " already exist");
        }
        
        userService.saveUser(user);
        return true;
    }
 
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public boolean createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Deleting User " + user.getEmail());
     
        userService.deleteUser(user);
          return true;
    }
     
    
 
}
