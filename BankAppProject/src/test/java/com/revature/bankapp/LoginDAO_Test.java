package com.revature.bankapp;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class LoginDAO_Test {

	@Test
	public void test() {

		try {
			UserAccount acc = LoginDAO.getAccount("testUser", "testPassword");
			System.out.println(acc.getName());

			assertNotNull(acc);

		} catch (Exception e) {
			System.out.println("ERROR");
		}

	}

}
