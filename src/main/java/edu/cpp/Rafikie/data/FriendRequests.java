package edu.cpp.Rafikie.data;

import java.util.ArrayList;

public class FriendRequests {
	
	private String email;
	private ArrayList<FriendDetails> requests;
	
	public ArrayList<FriendDetails> getRequests() {
		return requests;
	}
	public void setRequests(ArrayList<FriendDetails> requests) {
		this.requests = requests;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
