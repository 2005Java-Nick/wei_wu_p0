package com.revature.bankapp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EncryptionTest {

	@Test
	public void test1() {

		String s = Encryption.encryptString("pass");

		assertTrue(Encryption.authenticate("pass", s));

	}

}
