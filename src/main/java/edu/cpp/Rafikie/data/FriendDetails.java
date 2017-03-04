package edu.cpp.Rafikie.data;

public class FriendDetails {
	private String email;
	private String name;
	private String image;
	private boolean isAdded;
	private boolean isIgnored;
	private boolean isBlocked;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isAdded() {
		return isAdded;
	}
	public void setAdded(boolean isAdded) {
		this.isAdded = isAdded;
	}
	public boolean isIgnored() {
		return isIgnored;
	}
	public void setIgnored(boolean isIgnored) {
		this.isIgnored = isIgnored;
	}
	public boolean isBlocked() {
		return isBlocked;
	}
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	

}
