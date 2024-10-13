package com.example.myJournal_spring;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Journal {
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	int id;
	
	@Id
	LocalDate localDate;
	
	String content;
	
	public Journal() {
		
	}
	
	public Journal(LocalDate localDate, String content) {
		this.localDate = localDate;
		this.content = content;
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
	



}
