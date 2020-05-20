package com.revature.bankapp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class TransferTest 
{
   
    @Test
    public void testTransfer_AccountFoundTrue()
    {
    	List<UserAccount> accounts = new ArrayList<UserAccount>();
    	List<Transfer> transferLogs =  new ArrayList<Transfer>();
    	String username = "test";
    	String password = "testPassword";
    	String username2 = "test2";
    	String password2 = "testPassword2";
    	UserAccount acc = new UserAccount(username,password);
    	accounts.add(acc);
    	acc = new UserAccount(username2,password2);
    	accounts.add(acc);
    	
    	
    	assertTrue(Transfer.transferMoney("test2", 0, accounts, 0, transferLogs));
    }
    
    @Test
    public void testTransfer_AccountNotFoundTrue()
    {
    	List<UserAccount> accounts = new ArrayList<UserAccount>();
    	List<Transfer> transferLogs =  new ArrayList<Transfer>();
    	String username = "test";
    	String password = "testPassword";
    	String username2 = "test2";
    	String password2 = "testPassword2";
    	UserAccount acc = new UserAccount(username,password);
    	accounts.add(acc);
    	acc = new UserAccount(username2,password2);
    	accounts.add(acc);
    	
        assertFalse(Transfer.transferMoney("notExist", 0, accounts, 0, transferLogs));
    }
    
   
    
}
