package edu.cpp.Rafikie.data.provider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.cpp.Rafikie.data.Register;
import edu.cpp.Rafikie.data.User;
import edu.cpp.Rafikie.data.UserDetails;

public interface UserManager {
	public String register(Register register);
	public List isUserExist(String email);
	public String passwordEncryption(String password);
	public void insertUserDetails(String result)throws JsonParseException, JsonMappingException, IOException;
	public UserDetails fetchUserDetails(String email);public String convertImageToBase64(HttpServletRequest request);
	public String convertImageToBase64(HttpServletRequest request);
	public void uploadImageToDatabase(HttpServletRequest request,String image)throws JsonParseException, JsonMappingException, IOException;
	public Image fetchImage(String email);
	public boolean addFriendRequests(String email) throws JsonParseException, JsonMappingException, IOException;
	public ArrayList<Notifications> getNotifications(String email);
	

}
