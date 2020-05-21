package com.revature.bankapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankapp.dao.AccountsDAO;
import com.revature.bankapp.dao.TransactionDAO;
import com.revature.bankapp.dao.UserAccountDAO;
import com.revature.bankapp.struct.Account;
import com.revature.bankapp.struct.Transaction;

public class Menu {

	private static Logger log = Logger.getRootLogger();
	private Scanner in;
	List<Account> accounts;
	// List<Transfer> transferLogs;
	private int accountID; // ID of the user account if login is successful

	public Menu() {
		in = new Scanner(System.in);
		accounts = new ArrayList<Account>();
		// transferLogs = new ArrayList<Transfer>();
	}

	public void greetings() {
		log.info("BankApp Menu Started");
		System.out.println("*****************************************************\n"
				+ "*                                                   *\n"
				+ "*          Welcome To Banking App v9000.1           *\n"
				+ "*                                                   *\n"
				+ "*****************************************************");
		boolean trigger = true;
		while (trigger) {
			System.out.println("*****************************************************\n"
					+ "*                                                   *\n"
					+ "*                    Main Menu                      *\n"
					+ "*                                                   *\n"
					+ "*                 -Enter A Option-                  *\n"
					+ "*                                                   *\n"
					+ "*     (1)Login     (2)Create Account    (3)Exit     *\n"
					+ "*                                                   *\n"
					+ "*****************************************************");

			int input = 0;
			System.out.println("Input: ");
			while (true) {
				try {
					input = Integer.parseInt(in.nextLine());
				} catch (Exception e) {
					System.out.println("Please enter a numerical value...");
				}

				if (input == 1) {

					loginMenu();

				} else if (input == 2) {

					System.out.println("Enter new Username: ");
					String username = in.nextLine();
					System.out.println("Enter new Password: ");
					String password = in.nextLine();

				} else if (input == 3) {
					trigger = false;
					break;
				} else {
					System.out.println("The option you choose is not an option.....");
				}
			}
		}
		System.out.println("*****************************************************\n"
				+ "*                                                   *\n"
				+ "*                                                   *\n"
				+ "*             You Have Closed The App               *\n"
				+ "*               Thank You, Good Bye                 *\n"
				+ "*                                                   *\n"
				+ "*                                                   *\n"
				+ "*                                                   *\n"
				+ "*****************************************************");
	}

	public void loginMenu() {
		log.info("Login Menu Started");
		boolean trigger = true;
		while (trigger) {
			System.out.println("*****************************************************\n"
					+ "*                                                   *\n"
					+ "*                   Login Menu                      *\n"
					+ "*                                                   *\n"
					+ "*            Enter Username and Password            *\n"
					+ "*                                                   *\n"
					+ "*     Enter 0 in any field to return to Main Menu   *\n"
					+ "*                                                   *\n"
					+ "*****************************************************");

			String username = null;
			String password = null;
			while (true) {

				System.out.println("Username: ");
				username = in.nextLine();
				if (username.equals("0")) {
					trigger = false;
					break;
				}
				System.out.println("Password: ");
				password = in.nextLine();
				if (password.equals("0")) {
					trigger = false;
					break;
				}

				accountID = UserAccountDAO.getUserAccount(username, password);
				if (accountID > 0) {
					System.out.println("*****************************************************\n"
							+ "*              Login Sucessful                      *\n"
							+ "*****************************************************");
					break;
				} else {
					System.out.println("*****************************************************\n"
							+ "*              Login Failed, Please Try Again       *\n"
							+ "*****************************************************");
				}
			}
			if (accountID > 0) {
				accountMenu();
			}
		}

		System.out.println("*****************************************************\n"
				+ "*              Returning To Main Menu                *\n"
				+ "*****************************************************");
	}

	public void accountMenu() {
		log.info("Login Menu Started");
		List<Account> accountList = AccountsDAO.getAccounts(accountID);
		List<Transaction> transactionList = TransactionDAO.getUserTransactionHistory(accountID);
		boolean trigger = true;
		while (trigger) {
			System.out.println("*****************************************************\n"
					+ "*                   Account Menu                    *\n"
					+ "*                                                   *\n"
					+ "*                  Enter A Option                   *\n"
					+ "*                                                   *\n"
					+ "* (1) Account Balances                              *\n"
					+ "* (2) Transaction History                           *\n"
					+ "* (3) Deposit                                       *\n"
					+ "* (4) Withdraw                                      *\n"
					+ "* (5) Transfer                                      *\n"
					+ "* (6) Create Account(Checking/Savings)              *\n"
					+ "* (7) Exit                                          *\n"
					+ "*****************************************************");

			int input = 0;
			System.out.println("Input: ");
			while (true) {
				try {
					input = Integer.parseInt(in.nextLine());
				} catch (Exception e) {
					System.out.println("Please enter a numerical value...");
				}

				if (input == 1) {
					accountList = AccountsDAO.getAccounts(accountID);
					System.out.println("*****************************************************\n"
							+ "*                    -Accounts-                     *\n"
							+ "*****************************************************");
					showBalances(accountList);
					break;

				} else if (input == 2) {
					System.out.println("*****************************************************\n"
							+ "*              -Transaction History-                *\n"
							+ "*****************************************************");
					transactionList = TransactionDAO.getUserTransactionHistory(accountID);
					showTransactionHistory(transactionList);
					break;

				} else if (input == 7) {
					trigger = false;
					break;
				} else {
					System.out.println("The option you choose is not an option.....");
				}
			}

		}

		System.out.println("*****************************************************\n"
				+ "*              Returning To Login Menu              *\n"
				+ "*****************************************************");
	}

	private void showBalances(List<Account> accountList) {
		for (Account a : accountList) {
			System.out.println("Account ID: " + a.getAccountID() + "  Account Type: " + a.getType() + "  Balance: $"
					+ a.getBalance());
		}
	}

	private void showTransactionHistory(List<Transaction> transactionList) {
		for (Transaction a : transactionList) {
			for (Transaction a : transactionList) {
				System.out.println("Transaction ID: " + a.getTransactionID() + "  Account ID: " + a.getAccountID()
						+ "  Payee ID: " + a.getReceiverAccountID() + "  Date/Time: " + a.getDateTime()
						+ "  Transaction Type: " + a.getType() + "  Amount: " + a.getAmount());

			}
		}
	}

	public void createAccountMenu() {
		// TODO
	}

}
