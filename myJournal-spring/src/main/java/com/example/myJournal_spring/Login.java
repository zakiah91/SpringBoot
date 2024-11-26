package com.example.myJournal_spring;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Login {

	private String username;
	
	private String password;
	
	@Id	
	@Column (name="access_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accessId=0; 
	

	
	public Login() {
		
	}
	
	
	public Login(String password,String username ) {
		this.username = username;
		this.password = password;
	}
	
	
	public void setUserName(String username) {
		this.username = username;
	}
	
	
	public String getUserName() {
		return this.username;
	}
	
	
	public void setAccessId(long accessId) {
		this.accessId = accessId;
	}
	
	
	public long getAccessId() {
		return accessId;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
}
