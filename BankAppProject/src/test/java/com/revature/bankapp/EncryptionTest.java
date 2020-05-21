package com.revature.bankapp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EncryptionTest {

	@Test
	public void testEncriptionChecker() {
		String s = Encryption.encryptString("pass");
		assertTrue(Encryption.authenticate("pass", s));

	}

}
