package com.example.myJournal_spring;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.example.myJournal_spring.Login;



@Entity
public class Journal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long journalId=0;
	
	private LocalDate localDate;
	
	private String content;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name= "access_id")
	private Login login;
	
	
	public Journal() {
		
	}
	
	public Journal(LocalDate localDate, String content, Login login) {
		this.localDate = localDate;
		this.content = content;
		this.login = login;
	}
	
	public void setDate(LocalDate localDate) {
		this.localDate = localDate;
	}
	
	public LocalDate getDate() {
		return localDate;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public Login getLogin() {
		return login;
	}
	
	public void setLogin(Login login) {
		this.login = login;
	}
	



}
