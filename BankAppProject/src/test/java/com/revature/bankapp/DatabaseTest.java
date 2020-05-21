package com.revature.bankapp;

import java.sql.Connection;

import org.junit.Test;

import com.revature.bankapp.dao.ConnectionFactory;

public class DatabaseTest {

	@Test
	public void test() {
		try {

			Connection conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
