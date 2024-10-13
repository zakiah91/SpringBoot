package com.example.myJournal_spring;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalService {
	
	@Autowired
	private JournalRepository journalRepository;
	
	public List<Journal> findAll(){
		return journalRepository.findAll();
	}
	
	public Journal saveJournal(Journal journal) {
		return journalRepository.save(journal);
	}
	
//	public void deleteByDate(LocalDate localDate) {
//		journalRepository.deleteJournalByDate(localDate);
//	}
	
	

}
