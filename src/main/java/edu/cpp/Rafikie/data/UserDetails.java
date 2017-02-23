package edu.cpp.Rafikie.data;

import org.apache.commons.math3.linear.RealVector;
import java.util.Map;
import java.util.HashMap;

public class UserDetails {

	private String email;
	private String about;
	private String areacode;
	private String career;
	private String dob;
	private String education;
	private String gender;
	private String[] interests;
	private Double[] vectorRepr;
	private String other;
	private String language;
	private String location;
	private String name;
	private String relationship;
	private String telnum;
        private Map<String,String> image = new HashMap<String,String>();

	/*private enum interests{Cooking,OnlineGames,Partying,Sleeping,Sports,Studying,Travelling}
	private interests userPermission;
	 */
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
	public String[] getInterests() {
		return interests;
	}

	public void setInterests(String[] interests) {
		this.interests = interests;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public Double[] getVectorRepr() {
		return vectorRepr;
	}

	public void setVectorRepr(Double[] vectorRepr) {
		this.vectorRepr = vectorRepr;
	}
        
        public String getImage(String key) {
            String link = (String)image.get(key);
            return link;
	}

	public void setImage(String key, String link) {
               this.image.put(key, link);
	}

}
