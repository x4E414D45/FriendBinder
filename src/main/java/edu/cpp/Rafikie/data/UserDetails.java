package edu.cpp.Rafikie.data;

import edu.cpp.Rafikie.util.Geocoder;

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
	private String image;
        private String lat;
        private String lng;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
        
        public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
        
        public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
}
