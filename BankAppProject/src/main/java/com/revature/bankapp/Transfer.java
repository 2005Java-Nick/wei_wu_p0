package com.revature.bankapp;

import java.time.LocalDateTime;
import java.util.List;

public class Transfer {

	private LocalDateTime timeOfTransfer;
	private String sender;
	private String receiver;
	private int amount;

	public Transfer(String sender, String receiver, int amount) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		timeOfTransfer = LocalDateTime.now();
	}

	public LocalDateTime getTimeOfTransfer() {
		return timeOfTransfer;
	}

	public void setTimeOfTransfer(LocalDateTime timeOfTransfer) {
		this.timeOfTransfer = timeOfTransfer;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	// Searches for the account of the person to send money to, once found,
	// transfers the money and updates balance
	// and creates a transfer log
	// returns false if account was not found
	public static boolean transferMoney(String toAccount, int amount, List<UserAccount> accounts, int accountID,
			List<Transfer> transferLogs) {

		for (UserAccount userAccounts : accounts) {
			if (userAccounts.getName().equals(toAccount)) {
				userAccounts.accounts.setBalance(userAccounts.accounts.getBalance() + amount);
				accounts.get(accountID).accounts.setBalance(accounts.get(accountID).accounts.getBalance() - amount);

				Transfer trans = new Transfer(accounts.get(accountID).getName(), toAccount, amount);
				transferLogs.add(trans);
				return true;
			}
		}
		return false;
	}

}
