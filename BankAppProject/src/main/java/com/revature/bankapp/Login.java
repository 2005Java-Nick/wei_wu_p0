package com.revature.bankapp;

import java.util.List;

public class Login {
	
	//Returns the index of the account if found, returns -1 if not found
	public static int login(String username, String password, List<UserAccount> accounts) {
		
		int count=0;
		for(UserAccount userAccounts : accounts) {
			
			if(userAccounts.getName().equals(username) && userAccounts.getPassword().equals(password)) {
								
				return count;
			}
			count = count + 1;
		}
		return (-1);
	}
	

	 public static UserAccount createAccount(String username, String password, List<UserAccount> accounts) {

			for(UserAccount userAccounts : accounts) {
				if(userAccounts.getName().equals(username)) {					
					return null;
				}
			}
			
			UserAccount newAccount = new UserAccount();
			newAccount.setName(username);
			newAccount.setPassword(password);
			//start everyone with a balance of 200$
			newAccount.accounts.setBalance(200);	
			return newAccount;
			
		}

}
