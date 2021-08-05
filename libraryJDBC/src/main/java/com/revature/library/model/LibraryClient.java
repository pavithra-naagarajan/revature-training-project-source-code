package com.revature.library.model;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.revature.library.dao.LibraryDAO;
import com.revature.library.dao.LibraryDAOImplementation;

public class LibraryClient {
	static Scanner s = new Scanner(System.in);
	LibraryDAO libraryDAO = new LibraryDAOImplementation();
	int adminId = 5534;
	String adminPassword = "admin123";
	User user;
	private static Logger logger = Logger.getLogger("LibraryClient");

	public void startMainMenu() {
		while(true) {
		System.out.println("Home page:");
		System.out.println("\n1.Login\n2.Create Account\n0.Exit");
		System.out.println("Enter your choice:");
		int choice = s.nextInt();
		s.nextLine();
		
			switch (choice) {
			case 1:
				login();
				break;
			case 2:
				createNewAccount();
				break;

			case 0:
				System.out.println("Thanks for using Banking Application!");
				System.exit(0);
				break;
			default:
				System.out.println("Incorrect input!........");
				break;

			}
		}
	}

	private void createNewAccount() {
		System.out.println("Welcome to Account creation:");
		System.out.println("**********************************************************************");
		System.out.println("Enter userId:");
		int userId = s.nextInt();
		s.nextLine();
		System.out.println("Enter userName:");
		String userName = s.nextLine();
		System.out.println("Enter userRole:");
		String userRole = s.nextLine();
		System.out.println("Enter userMailId:");
		String userMailId = s.nextLine();
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userMailId);
		if (matcher.matches()) {
			System.out.println("success");
		System.out.println("Enter userMobileNumber:");
		int userMobileNumber = s.nextInt();
		s.nextLine();
		System.out.println("Enter userPassword:");
		String userPassword = s.nextLine();
		System.out.println("Enter userPassword again to confirm:");
		String userPasswordAgain = s.nextLine();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreated = new Date();
		String dateCreatedOn = formatter.format(dateCreated);
		user = (new User(userId, userName, userRole, userMailId, userMobileNumber, userPassword, dateCreatedOn));

		System.out.println("Your account is created successfully!");
		System.out.println("**********************************************************************");
		libraryDAO.addUser(user);
		}else
		{
			System.out.println("Enter appropriate emailId!");
			startMainMenu();
		}

	}

	private void login() {
		System.out.println("Welcome to Login Section:");

		System.out.println("Please Enter the details to login:");
		System.out.println("Enter your login type:\nA for login as Admin :\nU for login as User: ");
		System.out.println("**********************************************************************");

		String loginType = s.nextLine();

		switch (loginType) {
		case "A":
			loginAsAdmin();
			break;
		case "U":
			loginAsUser();
			break;

		default:
			System.out.println("Incorrect type! Please enter the appropriate type........");
			startMainMenu();
			break;

		}

	}

	private void loginAsUser() {
		System.out.println("Welcome to User Login Section:");
		System.out.println("Please Enter the details to login:");
		System.out.println("**********************************************************************");
		System.out.println("Enter userId:");
		int userId = s.nextInt();
		s.nextLine();
		System.out.println("Enter userPassword:");
		String userPassword = s.nextLine();
		int flag = 0;
		List<User> users = libraryDAO.getAllUsers();
		for (User user : users) {
			if ((user.getUserId() == userId) && (user.getUserPassword().equals(userPassword))) {
				System.out.println("your Login is finished successfully");
				flag = 1;
			}
		}
		if (flag == 1) {
			System.out.println("Here your personal page! Welcome");
			ClientAccessBook access = new ClientAccessBook();
			access.personalPageForUser();
		}
		if (flag == 0) {
			System.out.println("your user login details are not matched! try again");
			login();

		}

	}

	private void loginAsAdmin() {
		System.out.println("Welcome to Admin Login Section:");
		System.out.println("Please Enter the details to login:");
		System.out.println("**********************************************************************");
		System.out.println("Enter adminId:");
		int inputAdminId = s.nextInt();
		s.nextLine();
		System.out.println("Enter AdminPassword:");
		String inputAdminPassword = s.nextLine();
		int flag = 0;
		if ((inputAdminId == adminId) && (inputAdminPassword.equals(adminPassword))) {
			System.out.println("your Login is finished successfully");
			flag = 1;
		}

		if (flag == 1) {
			System.out.println("Here your personal page! Welcome");
			ClientAccessBook access = new ClientAccessBook();
			access.personalPageForAdmin();
		}
		if (flag == 0) {
			System.out.println("your admin login details are not matched! try again");

		}

	}

}
