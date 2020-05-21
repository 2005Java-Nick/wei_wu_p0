package com.revature.bankapp.struct;

import java.math.BigDecimal;

public class Transaction {

	private int transactionID;
	private int accountID;
	private int receiverAccountID;
	private String dateTime;
	private String type;
	private BigDecimal amount;

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public int getReceiverAccountID() {
		return receiverAccountID;
	}

	public void setReceiverAccountID(int receiverAccountID) {
		this.receiverAccountID = receiverAccountID;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
