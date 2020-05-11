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
    	List<Account> accounts = new ArrayList<Account>();
    	Account acc = new Account(username,password);
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
    	List<Account> accounts = new ArrayList<Account>();
    	Account acc = new Account(username,password);
    	accounts.add(acc);
        assertFalse(Login.login(fakeName, fakePassword, accounts) >= 0);
    }
    
    @Test
    public void testCreateAccount_AccountCreatedTrue()
    {
    	List<Account> accounts = new ArrayList<Account>();
    	String username = "test";
    	String password = "testPassword";;
    	
    	
	     Account newAccount = Login.createAccount(username, password, accounts);
	    accounts.add(newAccount);		 
        assertTrue(newAccount.getName() == accounts.get(0).getName() && newAccount.getPassword() == accounts.get(0).getPassword());
    }
    
    @Test
    public void testCreateAccount_AccountAlreadyExistTrue()
    {
    	List<Account> accounts = new ArrayList<Account>();
    	String username = "test";
    	String password = "testPassword";;
    	String n = "test";
    	String nPass = "bfdfbhrhrdz";
    	
	    accounts.add(new Account(n, nPass));	
	     Account newAccount = Login.createAccount(username, password, accounts);
	    accounts.add(newAccount);		 
        assertTrue(newAccount == null);
    }
    
    
}
