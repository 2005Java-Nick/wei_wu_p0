package com.revature.bankapp;

import java.io.Serializable;

public class Account implements Serializable{
	
	private String name;
	private String password;
	public Checking accounts;
	
	public Account() {
		accounts = new Checking();
		accounts.setBalance(0);
	}
	public Account(String name, String password) {
		accounts = new Checking();
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
		
	
	
}
