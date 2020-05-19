package com.revature.bankapp;

import java.sql.Connection;

import org.junit.Test;

public class DatabaseTest {

	@Test
	public void test() {
		try {

			Connection conn = DatabaseConnection.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
