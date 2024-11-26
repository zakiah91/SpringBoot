package com.example.myJournal_spring;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class MyJournalSpringApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MyJournalSpringApplication.class, args);
		
	}

	
	public void print(String s) {
		System.out.println(s);
	}
	

	
	
	//zakiah25/11:auto fill in the data
	@Bean
	@Transactional
	public CommandLineRunner commandLineRunner(JournalRepository journalRepository, LoginRepository loginRepository) {
		
		return (args)->{
			
			String username = "userA";
			
			print(Integer.toString(loginRepository.countByUsername(username)));
			
			
			
			if(loginRepository.countByUsername(username) > 0)
				return;
			
			//zakiah2511: the value of the password here is md5(12345)
			Login login = new Login("827ccb0eea8a706c4c34a16891f84e7b",username);
			loginRepository.save(login);
			
			Journal journal = new Journal();
			
			journal = new Journal(LocalDate.of(2024, 9, 17),"content1",login);
			journalRepository.save(journal);
			
			journal = new Journal(LocalDate.of(2024, 9, 16),"content2",login);
			journalRepository.save(journal);
			
			journal = new Journal(LocalDate.of(2024, 9, 15),"content3",login);
			journalRepository.save(journal);
						
		};
		
	}
	
}
