package com.example.myJournal_spring;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface JournalRepository extends JpaRepository<Journal,LocalDate>{

	String findByLocalDate(LocalDate localDate);
	
//	@Query("DELETE ALL FROM Journal WHERE localDate = :date")
//	void deleteJournalByDate(@Param("date") LocalDate localDate);
	
}
