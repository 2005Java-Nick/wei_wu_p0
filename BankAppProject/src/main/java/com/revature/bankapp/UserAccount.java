package com.revature.bankapp;

public class UserAccount {

	private String name;
	private String password;
	public Account accounts;

	public UserAccount() {

	}

	public UserAccount(String name, String password) {
		accounts = new Account();
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
