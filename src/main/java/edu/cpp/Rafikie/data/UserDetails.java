package edu.cpp.Rafikie.data;


import java.util.ArrayList;

public class UserDetails {
	private String about;
	private String areacode;
	private String career;
	private String dob;
	private String education;
	private String gender;
	private interests interests;
	
	/*private enum interests{Cooking,OnlineGames,Partying,Sleeping,Sports,Studying,Travelling}
	private interests userPermission;
	*/


public interests getInterests() {
		return interests;
	}
	public void setInterests(interests interests) {
		this.interests = interests;
	}
	/*	public interests getUserPermission() {
		return userPermission;
	}*/
/*	public void setUserPermission(interests userPermission) {
		this.userPermission = userPermission;
	}*/
	//private ArrayList<String> interests;
/*	public ArrayList<String> getInterests() {
		return interests;
	}
	public void setInterests(ArrayList<String> interests) {
		this.interests = interests;
	}*/
	private String email;
	private String other;
	private String language;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String location;
	private String name;
	private String relationship;
	private String telnum;
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		 this.areacode = areacode;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	
	
	
	
	

}
