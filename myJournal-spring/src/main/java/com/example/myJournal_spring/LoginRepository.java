package com.example.myJournal_spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.myJournal_spring.Login;

import jakarta.annotation.Nullable;

public interface LoginRepository extends JpaRepository<Login,Long> {

	@Nullable
	@Query(value = "SELECT username FROM Login WHERE access_id = ?1", nativeQuery = true)
	String getUsernameBasedOnAccessId(Long accessId);
	
	@Nullable
	Login findByUsername(String username);
	
	@Nullable
	Login findByAccessId(Long accessId);
	
	int countByUsername(String username);
	
	Login findByUsernameAndPassword(String username, String password);
}
