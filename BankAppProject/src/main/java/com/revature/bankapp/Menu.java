package com.revature.bankapp;

import java.math.BigDecimal;
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
	// List<Transfer> transferLogs;
	private int userAccountID; // ID of the user account if login is successful

	public Menu() {
		in = new Scanner(System.in);
		// transferLogs = new ArrayList<Transfer>();
	}

	public void greetings() {
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
					log.warn("Bad User Input");
				}

				if (input == 1) {

					loginMenu();
					break;
				} else if (input == 2) {

					createUserAccountMenu();
					break;

				} else if (input == 3) {
					trigger = false;
					System.out.println("*****************************************************\n"
							+ "*                                                   *\n"
							+ "*                                                   *\n"
							+ "*             You Have Closed The App               *\n"
							+ "*               Thank You, Good Bye                 *\n"
							+ "*                                                   *\n"
							+ "*                                                   *\n"
							+ "*                                                   *\n"
							+ "*****************************************************");
					return;
				} else {
					System.out.println("The option you choose is not an option.....");
					log.warn("Bad User Input");
				}
			}
		}

	}

	public void loginMenu() {
		boolean trigger = true;
		while (trigger) {
			System.out.println("*****************************************************\n"
					+ "*                                                   *\n"
					+ "*                   Login Menu                      *\n"
					+ "*                                                   *\n"
					+ "*            Enter Username And Password            *\n"
					+ "*                                                   *\n"
					+ "*     Enter 0 In Any Field To Return To Main Menu   *\n"
					+ "*                                                   *\n"
					+ "*****************************************************");

			String username = null;
			String password = null;
			while (true) {

				System.out.println("Username: ");
				username = in.nextLine();
				if (username.equals("0")) {
					trigger = false;
					return;
				}
				System.out.println("Password: ");
				password = in.nextLine();
				if (password.equals("0")) {
					trigger = false;
					return;
				}

				userAccountID = UserAccountDAO.getUserAccount(username, password);
				if (userAccountID > 0) {
					System.out.println("*****************************************************\n"
							+ "*              Login Sucessful                      *\n"
							+ "*****************************************************");
					log.warn("LOGIN SUCESSFUL");
					break;
				} else {
					System.out.println("*****************************************************\n"
							+ "*              Login Failed, Please Try Again       *\n"
							+ "*****************************************************");
					log.warn("LOGIN FAIL");
				}
			}
			if (userAccountID > 0) {
				accountMenu();
			}
		}

		System.out.println("*****************************************************\n"
				+ "*              Returning To Main Menu                *\n"
				+ "*****************************************************");
	}

	public void accountMenu() {
		List<Account> accountList;
		List<Transaction> transactionList;
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
					+ "* (7) Change Password                               *\n"
					+ "* (8) Exit                                          *\n"
					+ "*****************************************************");

			int input = 0;
			while (true) {
				System.out.println("Input: ");
				try {
					input = Integer.parseInt(in.nextLine());
				} catch (Exception e) {
					System.out.println("Please enter a numerical value...");
					log.warn("Bad User Input");
				}

				if (input == 1) {
					accountList = AccountsDAO.getAccounts(userAccountID);
					showBalances(accountList);
					break;

				} else if (input == 2) {
					transactionList = TransactionDAO.getUserTransactionHistory(userAccountID);
					showTransactionHistory(transactionList);
					break;

				} else if (input == 3) {
					depositMenu();
					break;

				} else if (input == 4) {
					withdrawMenu();
					break;

				} else if (input == 5) {
					transferMenu();
					break;

				} else if (input == 6) {
					createAccountMenu();
					break;

				} else if (input == 7) {
					changePasswordMenu();
					break;

				} else if (input == 8) {
					System.out.println("*****************************************************\n"
							+ "*              Returning To Login Menu              *\n"
							+ "*****************************************************");
					trigger = false;
					return;
				} else {
					System.out.println("The option you choose is not an option.....");
					log.warn("Bad User Input");
				}
			}

		}

	}

	private void changePasswordMenu() {
		System.out.println("*****************************************************\n"
				+ "*              Password Changer Menu                *\n"
				+ "*                Enter 0 To Exit                    *\n"
				+ "*****************************************************");
		String password = null;

		System.out.println("New Password: ");
		password = in.nextLine();
		if (password.equals("0")) {
			return;
		} else {
			UserAccountDAO.changeUserPassword(userAccountID, password);
			System.out.println("--Password Has Successfully Changed--");
			log.info("Password Changed");
		}

	}

	private void showBalances(List<Account> accountList) {
		System.out.println("*****************************************************\n"
				+ "*                  -Account List-                   *\n"
				+ "*****************************************************");
		for (Account a : accountList) {
			System.out.println("Account ID: " + a.getAccountID() + "  Account Type: " + a.getType() + "  Balance: $"
					+ a.getBalance());
		}
	}

	private void showTransactionHistory(List<Transaction> transactionList) {
		System.out.println("*****************************************************\n"
				+ "*              -Transaction History-                *\n"
				+ "*****************************************************");
		for (Transaction a : transactionList) {
			System.out.println("Transaction ID: " + a.getTransactionID() + "  Account ID: " + a.getAccountID()
					+ "  Payee ID: " + a.getReceiverAccountID() + "  Date/Time: " + a.getDateTime()
					+ "  Transaction Type: " + a.getType() + "  Amount: " + a.getAmount());
		}
	}

	private void depositMenu() {
		System.out.println("*****************************************************\n"
				+ "*                  -Deposit Menu-                   *\n"
				+ "*     Enter Amount To Deposit                       *\n"
				+ "*     Enter Account Id To Deposit                   *\n"
				+ "*     Enter 0 To Return To Previous Menu            *\n"
				+ "*****************************************************");

		double amount = -1;
		int accountID = -1;
		boolean trigger = true;

		while (trigger) {
			System.out.println("Amount: ");
			try {
				amount = Double.parseDouble(in.nextLine());
			} catch (Exception e) {
				System.out.println("Please enter a numerical value...");
				log.warn("Bad User Input");
			}
			if (amount == 0) {
				trigger = false;
				return;
			} else if (amount > 0) {
				break;
			}
		}

		while (trigger) {
			System.out.println("Account ID: ");
			try {
				accountID = Integer.parseInt(in.nextLine());
			} catch (Exception e) {
				System.out.println("Please enter a numerical value...");
				log.warn("Bad User Input");
			}
			if (AccountsDAO.accountExist(accountID) == false) {
				System.out.println("Account Does NOT Exist...");
				log.warn("ACCOUNT NOT EXIST");
			} else if (accountID == 0) {
				trigger = false;
				return;
			} else if (accountID > 0) {
				break;
			}
		}

		if (amount > 0 && accountID > 0 && AccountsDAO.accountExist(accountID)) {
			TransactionDAO.deposit(accountID, BigDecimal.valueOf(amount));
			System.out.println("--Deposit Successful--");
		} else {
			System.out.println("--Deposit Failed--");
			log.warn("DEPOSIT FAILED");
		}

	}

	private void withdrawMenu() {
		System.out.println("*****************************************************\n"
				+ "*                  -Withdraw Menu-                  *\n"
				+ "*     Enter Amount To Withdraw                      *\n"
				+ "*     Enter Account Id To Withdraw                  *\n"
				+ "*     Enter 0 To Return To Previous Menu            *\n"
				+ "*****************************************************");

		int accountID = -1;
		boolean trigger = true;
		double amount = -1;
		while (trigger) {
			System.out.println("Amount: ");
			try {
				amount = Double.parseDouble(in.nextLine());
			} catch (Exception e) {
				System.out.println("Please enter a numerical value...");
				log.warn("Bad User Input");
			}
			if (amount == 0) {
				return;
			} else if (amount > 0) {
				break;
			}
		}

		while (trigger) {
			System.out.println("Account ID: ");
			try {
				accountID = Integer.parseInt(in.nextLine());
			} catch (Exception e) {
				System.out.println("Please enter a numerical value...");
				log.warn("Bad User Input");
			}
			if (accountID == 0) {
				return;
			} else if (accountID > 0) {
				if (AccountsDAO.ownsAccount(accountID, userAccountID) == false) {
					System.out.println("You Do NOT Own That Account...");
					log.warn("Bad User Input");
				} else {
					break;
				}
			}
		}

		if (accountID > 0 && amount > 0 && AccountsDAO.ownsAccount(accountID, userAccountID)) {
			TransactionDAO.withdraw(accountID, BigDecimal.valueOf(amount));
			System.out.println("--Withdraw Successful--");
		}

	}

	private void transferMenu() {
		System.out.println("*****************************************************\n"
				+ "*                  -Transfer Menu-                  *\n"
				+ "*     Enter Amount To Transfer                      *\n"
				+ "*     Enter Your Account ID                         *\n"
				+ "*     Enter Payee Account ID                        *\n"
				+ "*     Enter 0 In Any Field To Return                *\n"
				+ "*****************************************************");

		double amount = -1;
		int fromAccountID = -1;
		int payeeAccountID = -1;
		boolean trigger = true;
		while (trigger) {
			System.out.println("Amount: ");
			try {
				amount = Double.parseDouble(in.nextLine());
			} catch (Exception e) {
				System.out.println("Please enter a numerical value...");
				log.warn("Bad User Input");
			}
			if (amount > 0) {
				break;
			} else if (amount == 0) {
				return;
			}
		}

		while (trigger) {
			System.out.println("Your Account ID: ");
			try {
				fromAccountID = Integer.parseInt(in.nextLine());
			} catch (Exception e) {
				System.out.println("Please enter a numerical value...");
				log.warn("Bad User Input");
			}
			if (AccountsDAO.ownsAccount(fromAccountID, userAccountID) == false) {
				System.out.println("You Do NOT Own That Account...");
				log.warn("Bad User Input");
			} else if (fromAccountID > 0) {
				break;
			} else if (fromAccountID == 0) {
				return;
			}
		}

		while (trigger) {
			System.out.println("Payee Account ID: ");
			try {
				payeeAccountID = Integer.parseInt(in.nextLine());
			} catch (Exception e) {
				System.out.println("Please enter a numerical value...");
				log.warn("Bad User Input");
			}
			if (AccountsDAO.accountExist(payeeAccountID) == false) {
				System.out.println("Payee Account Does NOT Exist...");
				log.warn("Bad User Input");
			} else if (payeeAccountID > 0) {
				break;
			} else if (payeeAccountID == 0) {
				return;
			}
		}

		if (amount > 0 && fromAccountID > 0 && payeeAccountID > 0) {
			TransactionDAO.transfer(fromAccountID, BigDecimal.valueOf(amount), payeeAccountID);
			System.out.println("--Transfer Successful--");
			trigger = false;
		}
	}

	public void createAccountMenu() {
		System.out.println("*****************************************************\n"
				+ "*             -Create Account Menu-                 *\n"
				+ "*                                                   *\n"
				+ "*     Enter (1) For Checking Account                *\n"
				+ "*     Enter (2) For Savings Account                 *\n"
				+ "*     Enter 0 To Return To Previous Menu            *\n"
				+ "*****************************************************");

		int input = -1;
		while (true) {
			System.out.println("Input: ");
			try {
				input = Integer.parseInt(in.nextLine());
			} catch (Exception e) {
				System.out.println("Please enter a numerical value...");
				log.warn("Bad User Input");
			}

			if (input == 1) {

				AccountsDAO.createAccount("Checking", BigDecimal.valueOf(0), userAccountID);
				System.out.println("--Checking Account Created Successfully--");
				break;

			} else if (input == 2) {
				AccountsDAO.createAccount("Savings", BigDecimal.valueOf(0), userAccountID);
				System.out.println("--Savings Account Created Successfully--");
				break;

			} else if (input == 0) {
				break;
			}
		}
	}

	public void createUserAccountMenu() {
		System.out.println("*****************************************************\n"
				+ "*             -Create User Account Menu-            *\n"
				+ "*                                                   *\n"
				+ "*     Enter a Username and Password                 *\n"
				+ "*     Enter 0 To Return To Previous Menu            *\n"
				+ "*****************************************************");

		String username = null;
		String password = null;
		while (true) {

			while (true) {
				System.out.println("Username: ");
				username = in.nextLine();
				if (UserAccountDAO.checkUserExist(username) == true) {
					System.out.println("Username Already Exist...");
					log.warn("Bad User Input");
				} else if (username.equals("0")) {
					return;
				} else {
					break;
				}
			}

			System.out.println("Password: ");
			password = in.nextLine();
			if (password.equals("0")) {
				return;
			}

			try {
				UserAccountDAO.createUserAccount(username, password);

			} catch (Exception e) {
			}
			userAccountID = UserAccountDAO.getUserAccount(username, password);
			if (userAccountID > 0) {
				System.out.println("*****************************************************\n"
						+ "*       Account Created - Login Sucessful           *\n"
						+ "*****************************************************");
				break;
			} else {
				System.out.println("*****************************************************\n"
						+ "*              Login Failed, Please Try Again       *\n"
						+ "*****************************************************");
				log.warn("LOGIN FAIL");
			}
		}
		if (userAccountID > 0) {
			accountMenu();
		}

	}

}
