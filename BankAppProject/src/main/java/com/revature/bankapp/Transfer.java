package com.revature.bankapp;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transfer implements Serializable{

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
	
	
}
