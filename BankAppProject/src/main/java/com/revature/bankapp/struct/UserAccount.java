package com.revature.bankapp.struct;

public class UserAccount {

	private String name;
	private String password;
	private int userID;

	public UserAccount() {

	}

	public UserAccount(String name, String password, int userID) {
		this.name = name;
		this.password = password;
		this.userID = userID;
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

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}
