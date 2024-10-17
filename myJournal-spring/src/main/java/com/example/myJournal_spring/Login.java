package com.example.myJournal_spring;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Login {

	@Id
	private long userId;
	
	private String password;
	
	public Login() {
		
	}
	
	public Login(String password,long userId) {
		this.userId = userId;
		this.password = password;
	}
	
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
}
