package com.revature.bankapp.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserAccountDAO_TEST {

	@Test
	public void test1() {

		try {

			assertTrue(UserAccountDAO.createUserAccount("testttttt2222", "testpasss2222"));

		} catch (Exception e) {
			System.out.println("ERROR");
		}

	}

	@Test
	public void test2() {

		try {
			int userAccountID = UserAccountDAO.getUserAccount("testttttt2222", "testpasss2222");

			assertTrue(userAccountID >= 0);

		} catch (Exception e) {
			System.out.println("ERROR");
		}

	}

}
