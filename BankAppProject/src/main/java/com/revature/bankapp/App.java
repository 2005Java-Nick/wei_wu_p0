package com.revature.bankapp;

import org.apache.log4j.Logger;

public class App {
	private static Logger log = Logger.getRootLogger();

	public static void main(String[] args) {
		log.info("App Program Started");
		Menu start = new Menu();
		start.greetings();

	}

}
