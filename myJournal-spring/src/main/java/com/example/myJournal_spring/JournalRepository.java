package com.example.myJournal_spring;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.annotation.Nullable;



public interface JournalRepository extends JpaRepository<Journal,Long>{

	@Nullable
	Journal findByLocalDate(LocalDate localDate);
	
	@Nullable
	List<Journal> findByLogin(Login login);
	
	int countByJournalIdAndLogin(long journalId,Login login);
	
}
