package com.example.myJournal_spring;

import java.util.Set;

public class CommonFunctions {
	
	public CommonFunctions() {
		
	}

    protected boolean isKeyValueExist(String[] keyValue, Set<String> key) {
		for(int i=0; i<keyValue.length;i++) {
			if(!key.contains(keyValue[i])) {
				return false;
			}
		}
		
		return true;
    }
    
	public void l(String log) {
		System.out.println(log);
	}
	
}
