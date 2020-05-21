package com.revature.bankapp;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import com.revature.bankapp.dao.ConnectionFactory;

public class DatabaseTest {

	@Test
	public void databaseConnectionTest() {

		try {
			Connection conn = ConnectionFactory.getConnection();
			assertNotNull(conn);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
