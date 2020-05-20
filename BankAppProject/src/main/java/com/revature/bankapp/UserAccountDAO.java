package com.revature.bankapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserAccountDAO {

	private static final String CREATE_UserACCOUNT = "INSERT INTO user_account (username,user_password) values (?,?)";

	public static boolean createUserAccount(String username, String password) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement;

		try {
			preparedStatement = conn.prepareStatement(CREATE_UserACCOUNT);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private static final String GET_UserACCOUNT = "SELECT * FROM user_account WHERE user_account.username = ? AND user_account.user_password = ?";

	public static UserAccount getUserAccount(String username, String password) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement;
		UserAccount acc = null;
		try {
			preparedStatement = conn.prepareStatement(GET_UserACCOUNT);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet result = preparedStatement.executeQuery();

			if (result.next()) {
				acc = new UserAccount();
				acc.setName(result.getString("username"));
				acc.setPassword(result.getString("user_password"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return acc;
	}

}
