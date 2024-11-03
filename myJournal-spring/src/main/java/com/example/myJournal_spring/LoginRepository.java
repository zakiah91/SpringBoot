package com.example.myJournal_spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoginRepository extends JpaRepository<Login,String> {

	@Query(value = "SELECT username FROM Login WHERE access_id = ?1", nativeQuery = true)
	String getUsernameBasedOnAccessId(Long accessId);
	
	@Query(value = "SELECT access_id FROM Login WHERE username = ?1 AND password = ?2", nativeQuery = true)
	String getAccessId(String username, String password);
}
