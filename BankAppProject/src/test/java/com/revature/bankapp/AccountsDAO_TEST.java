package com.revature.bankapp;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

public class AccountsDAO_TEST {

	@Test
	public void test1() {

		try {

			assertTrue(AccountsDAO.createAccount("checking", BigDecimal.valueOf(5.52), 1));

		} catch (Exception e) {
			System.out.println("ERROR");
		}

	}

	@Test
	public void test2() {

		try {
			List<Account> accountList = AccountsDAO.getAccount(1);
			assertTrue(!accountList.isEmpty());
			for (Account a : accountList) {
				System.out.println(a.getAccountID() + " " + a.getType() + " " + a.getBalance());

			}

		} catch (Exception e) {
			System.out.println("ERROR");
		}

	}

}
