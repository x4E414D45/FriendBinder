package edu.cpp.Rafikie.controller;
//import edu.cpp.Rafikie.model.Customer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cpp.Rafikie.data.FriendsWithSimilarInterests;
import edu.cpp.Rafikie.data.Image;
import edu.cpp.Rafikie.data.Login;
import edu.cpp.Rafikie.data.Notifications;
import edu.cpp.Rafikie.data.Register;
import edu.cpp.Rafikie.data.User;
import edu.cpp.Rafikie.data.UserDetails;
import edu.cpp.Rafikie.data.provider.MongoDBConnection;
import edu.cpp.Rafikie.data.provider.UserManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.core.runtime.CoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import edu.cpp.Rafikie.recommender.Interests;
import edu.cpp.Rafikie.recommender.Recommender;

@RestController
public class WebController extends WebMvcConfigurerAdapter {

	User user = new User();
	UserDetails userDetail = new UserDetails();

	@Autowired
	private UserManager userManager;

	@Autowired
	MongoDBConnection connection;

	@Autowired
	Recommender recommender;

	@Autowired
	Interests interests;

	/*	 @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/index").setViewName("index");
	        registry.addViewController("/login").setViewName("login");
	        
	 }*/
	@RequestMapping(value = "/getNotifications", method = RequestMethod.POST, consumes = "application/json")
	public List<Notifications> getNotifications(@RequestBody String email) {
		ArrayList<Notifications> notifications = userManager.getNotifications(email);
		return notifications;

	}

	@RequestMapping(value = "/fetchFriendsList", method = RequestMethod.POST, consumes = "application/json")
	public List<FriendsWithSimilarInterests> getFriends(@RequestBody String email) {
		ArrayList<FriendsWithSimilarInterests> friendsWithSimilarInterests = new ArrayList<>();
		friendsWithSimilarInterests = recommender.recommend(email);
		return friendsWithSimilarInterests;

	}

	@RequestMapping(value = "/addFriend", method = RequestMethod.POST, consumes = "application/json")
	public boolean addFriend(@RequestBody String email) throws JsonParseException, JsonMappingException, IOException {
		userManager.addFriendRequests(email);
		return false;
	}

	@RequestMapping(value = "/getEmail", method = RequestMethod.GET)
	public User getEmail() {
		return user;
	}

	@RequestMapping(value = "/fetchImage", method = RequestMethod.POST, consumes = "application/json")
	public Image getImage(@RequestBody String email) {
		Image image = userManager.fetchImage(email);

		return image;

	}

	@RequestMapping(value = "/fileUpload/{userId:.+}", method = RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	public void continueFileUpload(HttpServletRequest request, HttpServletResponse response, @PathVariable("userId") String userId) throws JsonParseException, JsonMappingException, IOException, CoreException {

		userManager.uploadImageToDatabase(request, userId);

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public boolean login(@RequestBody String login) {
		Gson gson = new GsonBuilder().create();
		Login loginDetails = gson.fromJson(login, Login.class);
		loginDetails.setPassword(userManager.passwordEncryption(loginDetails.getPassword()));
		List<String> list = userManager.isUserExist(loginDetails.getEmail());
		return list.contains(loginDetails.getPassword());
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
	public boolean register(@RequestBody String registerDetails) throws JsonParseException, JsonMappingException, IOException {
		Gson gson = new GsonBuilder().create();
		Register register = gson.fromJson(registerDetails, Register.class);
		register.setPassword(userManager.passwordEncryption(register.getPassword()));
		List<String> list = new ArrayList<>();
		list = userManager.isUserExist(register.getEmail());

		if (list.isEmpty()) {
			userManager.register(register);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/userDetails", method = RequestMethod.POST, consumes = "application/json")
	public boolean insertUserDetails(@RequestBody String userDetails) throws JsonParseException, JsonMappingException, IOException {
		userManager.insertUserDetails(userDetails);
		return false;

	}

	@RequestMapping(value = "/fetchUserDetails", method = RequestMethod.POST, consumes = "application/json")
	public UserDetails fetchUserDetails(@RequestBody String userEmail) {
		userDetail = userManager.fetchUserDetails(userEmail);
		return userDetail;
	}

}
