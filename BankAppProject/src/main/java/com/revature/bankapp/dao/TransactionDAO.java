package com.revature.bankapp.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.bankapp.struct.Transaction;

public class TransactionDAO {
	private static final String DEPOSIT = "CALL deposit(?,?)";

	public static boolean deposit(int accountID, BigDecimal amount) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement;

		try {
			preparedStatement = conn.prepareStatement(DEPOSIT);
			preparedStatement.setInt(1, accountID);
			preparedStatement.setBigDecimal(2, amount);
			preparedStatement.executeUpdate();

		} catch (Exception e) {

			return false;
		}

		return true;
	}

	private static final String WITHDRAW = "CALL withdraw(?,?)";

	public static boolean withdraw(int accountID, BigDecimal amount) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement;

		try {
			preparedStatement = conn.prepareStatement(WITHDRAW);
			preparedStatement.setInt(1, accountID);
			preparedStatement.setBigDecimal(2, amount);
			preparedStatement.executeUpdate();

		} catch (Exception e) {

			return false;
		}

		return true;
	}

	private static final String TRANSFER = "CALL transfer(?,?,?)";

	public static boolean transfer(int fromAccountID, BigDecimal amount, int toAccountID) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement;

		try {
			preparedStatement = conn.prepareStatement(TRANSFER);
			preparedStatement.setInt(1, fromAccountID);
			preparedStatement.setBigDecimal(2, amount);
			preparedStatement.setInt(3, toAccountID);
			preparedStatement.executeUpdate();

		} catch (Exception e) {

			return false;
		}

		return true;
	}

	private static final String getUser_TRANSACTION_HISTORY = "SELECT * FROM getUserTransactionHistory(?)";

	public static List<Transaction> getUserTransactionHistory(int userID) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement;

		List<Transaction> transactionList = new ArrayList<Transaction>();
		Transaction trans;

		try {
			preparedStatement = conn.prepareStatement(getUser_TRANSACTION_HISTORY);
			preparedStatement.setInt(1, userID);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				trans = new Transaction();
				trans.setTransactionID(result.getInt("id"));
				trans.setAccountID(result.getInt("user_account_id"));
				trans.setReceiverAccountID(result.getInt("receiver_account_id"));
				trans.setDateTime(result.getString("transaction_date"));
				trans.setType(result.getString("transaction_type"));
				trans.setAmount(result.getBigDecimal("transaction_amount"));
				transactionList.add(trans);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return transactionList;
	}

}
