package com.example.myJournal_spring;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
	
	private LoginRepository loginRepository;
	
	private CommonFunctions cm = new CommonFunctions();
	
	@Autowired
	public JournalRestController(JournalRepository journalRepository, LoginRepository loginRepository) {
		this.journalRepository = journalRepository;
		this.loginRepository   = loginRepository;
	}
	
	@GetMapping
	public HashMap<LocalDate,String> getAllDataFromJournal(@RequestParam Map<String,String> body){
		
		int KEY_ID=0;
		String[] keyValue = {"accessId"};
		
		if(!cm.isKeyValueExist(keyValue, body.keySet())) return null;
			
		Long accessId = Long.parseLong(body.get(keyValue[KEY_ID]));
		
		Login login = loginRepository.findByAccessId(accessId);
		
		cm.l("GET");
		
		List<Journal> journalList = journalRepository.findByLogin(login);
		
		HashMap<LocalDate,String> dateContent = new HashMap<LocalDate,String>();
		for(int i=0;i<journalList.size();i++) {
			dateContent.put(journalList.get(i).getDate(), journalList.get(i).getContent());
		}
		
		return dateContent;
	}
	
	
	@PostMapping("/post")
	public String postNewJournal(@RequestParam Map<String,String> body) {
		
		int KEY_DATE=0;
		int KEY_CONTENT = 1;
		int KEY_ID = 2;
		String[] keyValue = {"date","content","accessId"};
		
		if(!cm.isKeyValueExist(keyValue, body.keySet())) return null;
		
		cm.l("POST");
		
		Long accessId = Long.parseLong(body.get(keyValue[KEY_ID]));
		
		Login login = loginRepository.findByAccessId(accessId);
	
		if(login == null) return "ERR";
		
		int YEAR = 0;
		int MONTH = 1;
		int DATE = 2;
		String[] ymd = body.get("date").split("-");
		
		LocalDate localDate = LocalDate.of(Integer.parseInt(ymd[YEAR]), Integer.parseInt(ymd[MONTH]), Integer.parseInt(ymd[DATE]));
		
		Journal journal = journalRepository.findByLocalDate(localDate);
		
		
		if(journal == null) {
			journal = new Journal(localDate,
								      body.get(keyValue[KEY_CONTENT]),
				                     login);
		}
		else {
			journal.setContent(body.get(keyValue[KEY_CONTENT]));
		}
		
		journalRepository.save(journal);
		
		return "OK";
	}
	
	@PostMapping("/delete")
	public String deleteByDate(@RequestParam Map<String,String> data) {
		
		int KEY_JOURNAL = 0;
		int KEY_LOGIN = 1;
		String[] keyValue = {"journalId","accessId"};
		
		if(!cm.isKeyValueExist(keyValue, data.keySet())) return "ERR";
		
		Long journalId = Long.parseLong(data.get(keyValue[KEY_JOURNAL]));
		Long accessId  = Long.parseLong(data.get(keyValue[KEY_LOGIN]));
		
		Login login = loginRepository.findByAccessId(accessId);
		if(journalRepository.countByJournalIdAndLogin(journalId, login) == 0 ) return "ERR";
		
		cm.l("delete");
				
		journalRepository.deleteById(journalId);
		
		return "OK";
	}

}
