package com.revature.bankapp.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.bankapp.struct.Account;

public class AccountsDAO {
	private static final String CREATE_ACCOUNT = "INSERT INTO accounts (account_type,balance,user_id) values (?,?,?)";

	public static boolean createAccount(String type, BigDecimal balance, int userID) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement;

		try {
			preparedStatement = conn.prepareStatement(CREATE_ACCOUNT);
			preparedStatement.setString(1, type);
			preparedStatement.setBigDecimal(2, balance);
			preparedStatement.setInt(3, userID);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			System.out.println("ID NOT FOUND");
			return false;
		}

		return true;
	}

	private static final String GET_ACCOUNT = "SELECT * FROM accounts WHERE accounts.user_id = ?";

	public static List<Account> getAccounts(int userID) {
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

	private static final String ACCOUNT_EXIST = "SELECT * FROM accounts WHERE accounts.id = ?";

	public static boolean accountExist(int accountID) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement;

		try {
			preparedStatement = conn.prepareStatement(ACCOUNT_EXIST);
			preparedStatement.setInt(1, accountID);

			ResultSet result = preparedStatement.executeQuery();

			if (result.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	private static final String OWNS_ACCOUNT = "SELECT * FROM accounts WHERE accounts.id = ? AND accounts.user_id = ?";

	public static boolean ownsAccount(int accountID, int userAcountID) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement;

		try {
			preparedStatement = conn.prepareStatement(OWNS_ACCOUNT);
			preparedStatement.setInt(1, accountID);
			preparedStatement.setInt(2, userAcountID);
			ResultSet result = preparedStatement.executeQuery();

			if (result.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}
}