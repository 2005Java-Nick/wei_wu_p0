package com.revature.bankapp.dao;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.revature.bankapp.struct.Transaction;

public class TransactionDAO_TEST {

	@Test
	public void test1() {

		try {

			assertTrue(TransactionDAO.deposit(15, BigDecimal.valueOf(5)));

		} catch (Exception e) {
			System.out.println("ERROR");
		}

	}

	@Test
	public void test2() {
		try {

			assertTrue(TransactionDAO.withdraw(14, BigDecimal.valueOf(5)));

		} catch (Exception e) {
			System.out.println("ERROR");
		}

	}

	@Test
	public void test4() {

		try {
			List<Transaction> transactionList = TransactionDAO.getUserTransactionHistory(1);
			assertTrue(!transactionList.isEmpty());
			for (Transaction a : transactionList) {
				System.out.println(a.getTransactionID() + " " + a.getAccountID() + " " + a.getReceiverAccountID() + " "
						+ a.getDateTime() + " " + a.getType() + " " + a.getAmount());

			}

		} catch (Exception e) {
			System.out.println("ERROR");
		}

	}
}
