package com.example.myJournal_spring;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MyJournalSpringApplication  {

	public static void main(String[] args) {
		SpringApplication.run(MyJournalSpringApplication.class, args);
		
	}

	
	public void print(String s) {
		System.out.println(s);
	}
	

	
	
	//auto fill in the data
	@Bean
	public CommandLineRunner commandLineRunner(JournalRepository journalRepository) {
		
		return (args)->{
			 
			
			Journal journal1 = new Journal(LocalDate.of(2024, 9, 17),"content1");
			Journal journal2 = new Journal(LocalDate.of(2024, 9, 16),"content2");
			Journal journal3 = new Journal(LocalDate.of(2024, 9, 15),"content3");
			 
			journalRepository.save(journal1);
			journalRepository.save(journal2);
			journalRepository.save(journal3);
			
			List<Journal> journalList = journalRepository.findAll();
			
			for(int i=0; i<journalList.size();i++) {
				print("content = " + journalList.get(i).getContent() + " , "+ journalList.get(i).getDate().toString());
			}
			
			print("======================");

			journalRepository.deleteById(LocalDate.of(2024, 9, 15));
			
			journalList.clear();
			
			journalList = journalRepository.findAll();
			
			for(int i=0; i<journalList.size();i++) {
				print("content = " + journalList.get(i).getContent() + " , "+ journalList.get(i).getDate().toString());
			}
			
			journalRepository.save(new Journal(LocalDate.of(2024, 9, 17),"content11112222"));
			
			print("======================");

			journalList.clear();
			
			journalList = journalRepository.findAll();
			
			for(int i=0; i<journalList.size();i++) {
				print("content = " + journalList.get(i).getContent() + " , "+ journalList.get(i).getDate().toString());
			}
			
			
		};
		
	}
	
}
