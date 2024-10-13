package com.example.myJournal_spring;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin //to prevent cross-origin fail error when access using browser
@RequestMapping("/journal")
public class JournalRestController {
	
	private JournalRepository journalRepository;
	
	@Autowired
	public JournalRestController(JournalRepository journalRepository) {
		this.journalRepository = journalRepository;
	}
	
	@GetMapping
	public List<Journal> getAllDataFromJournal(){
		System.out.println("GET");
		return journalRepository.findAll();
	}
	
	
	//@PostMapping(consumes= {"application/json"},produces= {"application/json"})
	@PostMapping("/post")
	public Journal postNewJournal(@RequestParam Map<String,String> body) {
		//Journal j = null;
		Set<String> bodyKey = body.keySet();
		
		for(String key:bodyKey) {
			System.out.println(key);
		}
		
		System.out.println("POST");
		
		String[] ymd = body.get("date").split("-");
		
		Journal journal = new Journal(LocalDate.of(Integer.parseInt(ymd[0]), Integer.parseInt(ymd[1]), Integer.parseInt(ymd[2])),body.get("content"));
		//return "MESSAGE SENT";
		return journalRepository.save(journal);
	}
	
	@PostMapping("/delete")
	public void deleteByDate(@RequestParam Map<String,String> localDate) {
		
		System.out.println("delete");
		
		String[] ymd = localDate.get("date").split("-");
		
		journalRepository.deleteById(LocalDate.of(Integer.parseInt(ymd[0]), Integer.parseInt(ymd[1]), Integer.parseInt(ymd[2])));
	}

}
