package com.revature.bankapp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
	private static final String DEPOSIT = "UPDATE accounts SET balance";

	public static boolean deposit(int userID, BigDecimal amount) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement;

		try {
			preparedStatement = conn.prepareStatement(CREATE_ACCOUNT);
			preparedStatement.setString(1, type);
			preparedStatement.setBigDecimal(2, balance);
			preparedStatement.setInt(3, id);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			System.out.println("ID NOT FOUND");
			return false;
		}

		return true;
	}

	private static final String GET_ACCOUNT = "SELECT * FROM accounts WHERE accounts.user_id = ?";

	public static List<Account> getAccount(int userID) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement;

		List<Account> accountList = new ArrayList<Account>();
		Account account;

		try {
			preparedStatement = conn.prepareStatement(GET_ACCOUNT);
			preparedStatement.setInt(1, userID);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				account = new Account();
				account.setType(result.getString("account_type"));
				account.setBalance(result.getBigDecimal("balance"));
				account.setAccountID(result.getInt("id"));
				accountList.add(account);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return accountList;
	}
}
