package com.revature.bankapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class LoggerAccounts implements Serializable{

	public List<Account> accounts;
	public List<Transfer> transferLogs;
	
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Transfer> getTransferLogs() {
		return transferLogs;
	}

	public void setTransferLogs(List<Transfer> transferLogs) {
		this.transferLogs = transferLogs;
	}

	
	public static void save(List<Account> accounts, List<Transfer> transferLogs) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Accounts.ser")); 	
		out.writeObject(accounts);
		out.close();
		
		ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream("Transfers.ser")); 
		out2.writeObject(transferLogs);
		out2.close();
	}
	
    @SuppressWarnings("unchecked")
	public static LoggerAccounts load() throws IOException, ClassNotFoundException {
    	//wraps both list of objects into one object
    	LoggerAccounts log = new LoggerAccounts();
    	try {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("Accounts.ser"));
		log.accounts = (List<Account>)in.readObject();
		in.close();
    	}finally{
    		ObjectInputStream in2 = new ObjectInputStream(new FileInputStream("Transfers.ser"));
    		log.transferLogs = (List<Transfer>)in2.readObject();
    		in2.close();
    	}
		
		return log;
	}
    
}
