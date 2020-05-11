package com.revature.bankapp;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu implements Serializable{
	private Scanner in;
	List<Account> accounts;
	List<Transfer> transferLogs;
	int accountID; //the index of the users account in List<Account> accounts
	public Menu() {
		in = new Scanner(System.in);
		accounts = new ArrayList<Account>();
		transferLogs = new ArrayList<Transfer>();
		loadData();
	}
	
	private void saveData() {
		try {
			Logger.save(accounts, transferLogs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    private void loadData() {
		try {
			Logger log = Logger.load();
			accounts = new ArrayList<Account>(log.accounts);
			transferLogs = new ArrayList<Transfer>(log.transferLogs);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	public void greetings() {
		 boolean trigger = true;	 
		 while(trigger) {
		 System.out.println("Enter 1 to login or enter 2 to create account.");
		 System.out.println("Enter any other number to exit.");
		 System.out.println("Input: ");
		 int input = Integer.parseInt(in.nextLine());
		 if(input == 1) {
			 
			    System.out.println("Username: ");
				String username = in.nextLine();
				System.out.println("Password: ");
				String password = in.nextLine();
			 
			 
			 
			 if(Login.login(username,password, accounts) >= 0) {
				 System.out.println("Login successful...");
				 mainMenu();
			 }else {
				 System.out.println("Login failed");
			 }
			 
			 
		 }else if (input == 2) {
			
			    System.out.println("Enter new Username: ");
				String username = in.nextLine();
				System.out.println("Enter new Password: ");
				String password = in.nextLine();
			 Account newAccount = Login.createAccount(username, password, accounts);
			 if(newAccount != null) {
				 System.out.println("Account Created Successfully...");	
				 accounts.add(newAccount);
				 accountID = accounts.size()-1;
				 saveData();
				 mainMenu();
			 }else {
				 System.out.println("User already exist...");
			 }
			 
			 
		 }
		 else {
			 trigger = false;
		 }
		} 
		 System.out.println("You have exitted..");
	}
	
	
	
   
    
    public void mainMenu() {
    	 boolean trigger = true;	 
 		while(trigger) {
    	 System.out.println("Enter 1 to Transfer Money.");
    	 System.out.println("Enter any other number to return to last menu.");
		 System.out.println("Input: ");
		 int input = Integer.parseInt(in.nextLine());
		 if(input == 1) {
			 System.out.println("Enter name of person to send money: ");
			 String Username = in.nextLine();
			 System.out.println("Enter Amount: ");
			 int amount = Integer.parseInt(in.nextLine());
			 if(transferMoney(Username, amount)) {
				 System.out.println("Transfer successful...");
			 }	
			 else {
				 System.out.println("Transfer Failed");
			 }
		 }
		 else {
			 trigger = false;
		 }
 		}
    }
	
    //Searches for the account of the person to send money to, once found, transfers the money and updates balance
    //and creates a transfer log
    //returns false if account was not found
    public boolean transferMoney(String toAccount, int amount) {
    	for(Account userAccounts : accounts) {
			if(userAccounts.getName().equals(toAccount)) {
				userAccounts.accounts.setBalance(userAccounts.accounts.getBalance() + amount);
				accounts.get(accountID).accounts.setBalance(accounts.get(accountID).accounts.getBalance() - amount);
				
				Transfer trans = new Transfer(accounts.get(accountID).getName(), toAccount, amount);
				transferLogs.add(trans);
				saveData();
				return true;
			}
		}
    	return false;
    }
    
    
	
}
