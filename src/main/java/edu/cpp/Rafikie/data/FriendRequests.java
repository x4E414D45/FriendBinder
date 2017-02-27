package edu.cpp.Rafikie.data;

import java.util.ArrayList;

public class FriendRequests {
	
	private String email;
	private ArrayList<Image> requests;
	
	public ArrayList<Image> getRequests() {
		return requests;
	}
	public void setRequests(ArrayList<Image> requests) {
		this.requests = requests;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
