package com.revature.bankapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Menu {

	private static final Logger LOGGER = LogManager.getLogger(Menu.class);
	private Scanner in;
	List<UserAccount> accounts;
	List<Transfer> transferLogs;
	int accountID; // the index of the users account in List<Account> accounts

	public Menu() {
		in = new Scanner(System.in);
		accounts = new ArrayList<UserAccount>();
		transferLogs = new ArrayList<Transfer>();
		LOGGER.debug("-BankApp Started-");
	}

	public void greetings() {
		boolean trigger = true;
		while (trigger) {
			System.out.println("Enter 1 to login or enter 2 to create account.");
			System.out.println("Enter any other number to exit.");
			System.out.println("Input: ");
			int input = Integer.parseInt(in.nextLine());
			if (input == 1) {

				System.out.println("Username: ");
				String username = in.nextLine();
				System.out.println("Password: ");
				String password = in.nextLine();

				accountID = Login.login(username, password, accounts);
				if (accountID >= 0) {
					System.out.println("Login successful...");
					mainMenu();
				} else {
					System.out.println("Login failed");
				}

			} else if (input == 2) {

				System.out.println("Enter new Username: ");
				String username = in.nextLine();
				System.out.println("Enter new Password: ");
				String password = in.nextLine();
				UserAccount newAccount = Login.createAccount(username, password, accounts);
				if (newAccount != null) {
					System.out.println("Account Created Successfully...");
					accounts.add(newAccount);
					accountID = accounts.size() - 1;

					mainMenu();
				} else {
					System.out.println("User already exist...");
				}

			} else {
				trigger = false;
			}
		}
		System.out.println("You have exitted..");
	}

	public void mainMenu() {
		boolean trigger = true;
		while (trigger) {
			System.out.println("Current Balance: " + accounts.get(accountID).accounts.getBalance());
			System.out.println("Enter 1 to Transfer Money.");
			System.out.println("Enter any other number to return to last menu.");
			System.out.println("Input: ");
			int input = Integer.parseInt(in.nextLine());
			if (input == 1) {
				System.out.println("Enter name of person to send money: ");
				String Username = in.nextLine();
				System.out.println("Enter Amount: ");
				int amount = Integer.parseInt(in.nextLine());
				if (Transfer.transferMoney(Username, amount, accounts, accountID, transferLogs)) {

					System.out.println("Transfer successful...");
				} else {
					System.out.println("Transfer Failed");
				}
			} else {
				trigger = false;
			}
		}
	}

}
