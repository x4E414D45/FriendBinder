package edu.cpp.Rafikie.data;

import java.util.ArrayList;
import java.util.List;

public class MessageDetails {
	
	private String email;
	private String name;
	private ArrayList<Receiver> receiverDetails;

	public ArrayList<Receiver> getReceiverDetails() {
		return receiverDetails;
	}
	public void setReceiverDetails(ArrayList<Receiver> receiverDetails) {
		this.receiverDetails = receiverDetails;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
