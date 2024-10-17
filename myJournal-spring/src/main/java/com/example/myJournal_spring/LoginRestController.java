package com.example.myJournal_spring;

import java.util.List;
import java.util.Map;
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
	public boolean isLoginValid(@RequestParam Map<String,String> password) {
		
		List<Login> login = loginRepository.findAll();
		l("password = "+ password);

		if(login == null) {
			l("res = false");
			return false;
		}
		else if(login.size() >= 1) {
			if(password.get("password").equals(login.get(0).getPassword())) {
				l("res = true");
				return true;
			}
			else {
				l("res = false");
				return false;
			}
		}
		else {
			l("res = false");
			return false;
		}

	}
	
	
	@PostMapping("/update")
	public boolean updateLogin(@RequestParam Map<String,String> password) {
		
		List<Login> loginList = loginRepository.findAll();
		
		Set<String> key = password.keySet();
		
		for(String currKey : key){
			l("key ==> "+ currKey);
		}
		
		
		l("password = "+ password.get("password"));
		
		if(loginList.size() >= 1) {
			loginRepository.deleteAll();
		}
		
		Login login = new Login(password.get("password"),0000);
		loginRepository.save(login);
		
		return true;
		
	}
	
	
}
