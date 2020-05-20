package com.revature.bankapp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class LoginTest 
{
   
    @Test
    public void testLogin_AccountFoundTrue()
    {
    	String username = "test";
    	String password = "testPassword";
    	List<UserAccount> accounts = new ArrayList<UserAccount>();
    	UserAccount acc = new UserAccount(username,password);
    	accounts.add(acc);
        assertTrue(Login.login(username, password, accounts) >= 0);
    }
    
    @Test
    public void testLogin_AccountNotFoundTrue()
    {
    	String username = "test";
    	String password = "testPassword";
    	String fakeName = "tesat";
    	String fakePassword = "teesdd";
    	List<UserAccount> accounts = new ArrayList<UserAccount>();
    	UserAccount acc = new UserAccount(username,password);
    	accounts.add(acc);
        assertFalse(Login.login(fakeName, fakePassword, accounts) >= 0);
    }
    
    @Test
    public void testCreateAccount_AccountCreatedTrue()
    {
    	List<UserAccount> accounts = new ArrayList<UserAccount>();
    	String username = "test";
    	String password = "testPassword";;
    	
    	
	     UserAccount newAccount = Login.createAccount(username, password, accounts);
	    accounts.add(newAccount);		 
        assertTrue(newAccount.getName() == accounts.get(0).getName() && newAccount.getPassword() == accounts.get(0).getPassword());
    }
    
    @Test
    public void testCreateAccount_AccountAlreadyExistTrue()
    {
    	List<UserAccount> accounts = new ArrayList<UserAccount>();
    	String username = "test";
    	String password = "testPassword";;
    	String n = "test";
    	String nPass = "bfdfbhrhrdz";
    	
	    accounts.add(new UserAccount(n, nPass));	
	     UserAccount newAccount = Login.createAccount(username, password, accounts);
	    accounts.add(newAccount);		 
        assertTrue(newAccount == null);
    }
    
    
}
