package com.revature.bankapp;

import org.jasypt.digest.PooledStringDigester;

public class Encryption {

	public static String encryptString(String userPassword) {

		int cores = Runtime.getRuntime().availableProcessors();

		PooledStringDigester digester = new PooledStringDigester();
		digester.setPoolSize(cores);
		digester.setAlgorithm("SHA-1");
		digester.setIterations(50000);

		String digest = digester.digest(userPassword);
		return digest;
	}

	public static boolean authenticate(String userPassword, String storedPassword) {

		int cores = Runtime.getRuntime().availableProcessors();

		PooledStringDigester digester = new PooledStringDigester();
		digester.setPoolSize(cores);
		digester.setAlgorithm("SHA-1");
		digester.setIterations(50000);

		if (digester.matches(userPassword, storedPassword)) {
			return true;
		} else {
			return false;
		}

	}

}
