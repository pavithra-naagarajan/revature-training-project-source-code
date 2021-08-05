package com.revature.library.model;

public class LibraryHome {
	public static void main(String[] args) {
		System.out.println("            Welcome to the Library Management System");
		System.out.println("*********************************************************************************");
		LibraryClient client=new LibraryClient();
		client.startMainMenu();
	}
}
