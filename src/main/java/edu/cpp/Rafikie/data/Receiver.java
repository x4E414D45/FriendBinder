package edu.cpp.Rafikie.data;

import java.util.ArrayList;

public class Receiver {
	
	private String receiverEmail;
	private String receiverName;
	private int count;
	private ArrayList<Message> message;
	
	public ArrayList<Message> getMessage() {
		return message;
	}
	public void setMessage(ArrayList<Message> message) {
		this.message = message;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	private ArrayList<Message> messages;
	public String getReceiverEmail() {
		return receiverEmail;
	}
	public void setReceiverEmail(String receiver) {
		this.receiverEmail = receiver;
	}
	public ArrayList<Message> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

}
