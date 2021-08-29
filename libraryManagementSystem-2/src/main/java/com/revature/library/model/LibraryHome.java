package com.revature.library.model;

import java.util.Date;
import java.util.InputMismatchException;

import org.apache.log4j.Logger;

public class LibraryHome {
	private static Logger logger = Logger.getLogger("LibraryHome");
	static LibraryClient client =new LibraryClient();
	public static void main(String[] args) {
		
		logger.info("            Welcome to the Library Management System");
		logger.info("*********************************************************************************");
		

		
		client.startMainMenu();

	}
}
