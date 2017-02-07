package com.websystique.springmvc.model;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;


public class User {

	private long id;
	private String name;
	private String email;
	private String password;
	private String interests;
	private RealVector vectorRepr;

	public User(){
		id=0;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
 	public RealVector getVectorRepr() {
		return vectorRepr;
	}
	public void setVectorRepr(RealVector v) {
		this.vectorRepr = v;
	}
	public User(long id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
}
