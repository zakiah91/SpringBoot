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
	
	private CommonFunctions cm = new CommonFunctions();
	
	
	@Autowired
	public LoginRestController(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}
	
	@GetMapping
	public String test() {
		System.out.println("test");
		return "test";
	}
	

	
	@PostMapping("/isValid")
	public String isLoginValid(@RequestParam Map<String,String> tryLogin) {
		
		key = tryLogin.keySet();
		
		int KEY_USER = 0;
		int KEY_PWD = 1;
		String[] keyValue =  {"usrName","pwd"};
		long accessId=-1;
		
		if(!cm.isKeyValueExist(keyValue,key)) {
			return "ERR";
		}
		
		Login login = loginRepository.findByUsernameAndPassword(tryLogin.get(keyValue[KEY_USER]), tryLogin.get(keyValue[KEY_PWD]));
		accessId = login.getAccessId();
	    cm.l("accessId = "+ Long.toString(accessId));
		
		
		if(accessId == -1) {
			return "ERR";
		}
		
		cm.l("accessId = "+accessId );
		return Long.toString(accessId);

	}


	
	@PostMapping("/create")
	public boolean createNewAcc(@RequestParam Map<String,String> accDetails) {
		
		key = accDetails.keySet();
		int KEY_PWD = 0;
		int KEY_NAME = 1;
		String[] keyValue = {"pwd","usrName"};
		
		if(!cm.isKeyValueExist(keyValue,key)) {
			return false;
		}
		
		
		Login login = loginRepository.findByUsername(accDetails.get(keyValue[KEY_NAME]));
		
		if(login != null) {
			return false;
		}
		
		login = new Login(accDetails.get(keyValue[KEY_PWD]), accDetails.get(keyValue[KEY_NAME]));
		
		loginRepository.save(login);
		
		return true;
		
	}
	
	@PostMapping("/update")
	public boolean updatePassword(@RequestParam Map<String,String> password) {
		
		key = password.keySet();
		
		int KEY_PWD = 0;
		int KEY_ID = 1;
		String[] keyValue= {"password", "accessId"};
		
		if(!cm.isKeyValueExist(keyValue,key)) {
			return false;
		}
		
		String userName = "";
		Long accessId = Long.parseLong(password.get(keyValue[KEY_ID]));
		
		userName = loginRepository.getUsernameBasedOnAccessId(accessId);
		if( userName == null ){
			return false;
		}
		
		cm.l("password = "+ password.get("password"));
		
		Login login = loginRepository.findByUsername(userName);
		login.setPassword(password.get(keyValue[KEY_PWD]));
		loginRepository.save(login);
		
		return true;
		
	}
	
	
}
