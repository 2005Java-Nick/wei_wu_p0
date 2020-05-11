package com.revature.bankapp;

import java.util.List;

public class Login {
	
	//Returns the index of the account if found, returns -1 if not found
	public static int login(String username, String password, List<Account> accounts) {
		
		int count=0;
		for(Account userAccounts : accounts) {
			
			if(userAccounts.getName().equals(username) && userAccounts.getPassword().equals(password)) {
								
				return count;
			}
			count = count + 1;
		}
		return (-1);
	}
	

	 public static Account createAccount(String username, String password, List<Account> accounts) {

			for(Account userAccounts : accounts) {
				if(userAccounts.getName().equals(username)) {					
					return null;
				}
			}
			
			Account newAccount = new Account();
			newAccount.setName(username);
			newAccount.setPassword(password);
			//start everyone with a balance of 200$
			newAccount.accounts.setBalance(200);	
			return newAccount;
			
		}

}
