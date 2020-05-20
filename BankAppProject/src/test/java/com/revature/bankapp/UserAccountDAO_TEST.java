package com.revature.bankapp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserAccountDAO_TEST {

	@Test
	public void test() {

		try {

			assertTrue(UserAccountDAO.createAccount("testttttt", "testpasss111"));
			System.out.println("WOrked!!");

		} catch (Exception e) {
			System.out.println("ERROR");
		}

	}

}
