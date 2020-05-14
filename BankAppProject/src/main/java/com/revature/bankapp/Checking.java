package com.revature.bankapp;

import java.io.Serializable;

public class Checking implements Serializable{
	private double balance;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
