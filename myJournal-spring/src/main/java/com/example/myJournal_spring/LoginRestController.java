package com.example.myJournal_spring;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginRestController {
	
	private LoginRepository loginRepository;
	 
	private Set<String> key = null;
	
	
	@Autowired
	public LoginRestController(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}
	
	@GetMapping
	public String test() {
		System.out.println("test");
		return "test";
	}
	
	public void l(String log) {
		System.out.println(log);
	}
	
	@PostMapping("/isValid")
	public String isLoginValid(@RequestParam Map<String,String> tryLogin) {
		
		key = tryLogin.keySet();
		String[] keyValue =  {"usrName","pwd"};
		String accessId;
		
		if(!isKeyValueExist(keyValue,key)) {
			return "ERR";
		}
		
		accessId = loginRepository.getAccessId(tryLogin.get(keyValue[0]), tryLogin.get(keyValue[1]));
		
		if(accessId == null || accessId.isBlank()) {
			return "ERR";
		}
		
		l("accessId = "+accessId );
		return accessId;

	}

    private boolean isKeyValueExist(String[] keyValue, Set<String> key) {
		for(int i=0; i<keyValue.length;i++) {
			if(!key.contains(keyValue[i])) {
				return false;
			}
		}
		
		return true;
    }
	
	@PostMapping("/create")
	public boolean createNewAcc(@RequestParam Map<String,String> accDetails) {
		
		key = accDetails.keySet();
		String[] keyValue = {"pwd","usrName","accessId"};
		
		if(!isKeyValueExist(keyValue,key)) {
			return false;
		}
		
		Optional<Login> login = loginRepository.findById(accDetails.get(keyValue[1]));
		
		if (login.isEmpty()) {
			
			Login newLogin = new Login(accDetails.get(keyValue[0]), accDetails.get(keyValue[1]), Long.parseLong(accDetails.get(keyValue[2])) );
			
			loginRepository.save(newLogin);
			
			return true;
		}
		
		return false;
	}
	
	@PostMapping("/update")
	public boolean updateLogin(@RequestParam Map<String,String> password) {
		
		key = password.keySet();
		String[] keyValue= {"password, accessId"};
		String userName = "";
		Long accessId = Long.parseLong(password.get(keyValue[1]));
		
		if(!isKeyValueExist(keyValue,key)) {
			return false;
		}
		
		userName = loginRepository.getUsernameBasedOnAccessId(accessId);
		if( userName == null || userName.isBlank()){
			return false;
		}
		
		l("password = "+ password.get("password"));
		
		Login login = new Login(password.get(keyValue[0]), userName, accessId );
		loginRepository.save(login);
		
		return true;
		
	}
	
	
}
