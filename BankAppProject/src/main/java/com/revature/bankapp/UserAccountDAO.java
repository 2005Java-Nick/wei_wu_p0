package com.revature.bankapp;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserAccountDAO {

	private static final String CREATE_ACCOUNT = "INSERT INTO user_account (username,user_password) values (?,?)";

	public static boolean createAccount(String username, String password) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement;

		try {
			preparedStatement = conn.prepareStatement(CREATE_ACCOUNT);
			preparedStatement.setString(1, "'" + username + "'");
			preparedStatement.setString(2, "'" + password + "'");
			System.out.println("TEST1");
			preparedStatement.executeUpdate();
			System.out.println("TEST2");

		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
