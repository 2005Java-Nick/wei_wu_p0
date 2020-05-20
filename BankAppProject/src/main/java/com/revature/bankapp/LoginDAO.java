package com.revature.bankapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

	private static final String GET_ACCOUNT = "SELECT * FROM user_account WHERE user_account.username = ? AND user_account.user_password = ?";

	public static UserAccount getAccount(String username, String password) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement;
		UserAccount acc = null;
		try {
			preparedStatement = conn.prepareStatement(GET_ACCOUNT);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			System.out.println("TEST1");
			ResultSet result = preparedStatement.executeQuery();
			System.out.println("TEST2");
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
