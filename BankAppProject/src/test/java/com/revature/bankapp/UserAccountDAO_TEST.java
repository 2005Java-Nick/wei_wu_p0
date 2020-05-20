package com.revature.bankapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserAccountDAO_TEST {

	@Test
	public void test1() {

		try {

			assertTrue(UserAccountDAO.createUserAccount("testttttt", "testpasss111"));

		} catch (Exception e) {
			System.out.println("ERROR");
		}

	}

	@Test
	public void test2() {

		try {
			UserAccount acc = UserAccountDAO.getUserAccount("testUser", "testPassword");

			assertNotNull(acc);

		} catch (Exception e) {
			System.out.println("ERROR");
		}

	}

}
