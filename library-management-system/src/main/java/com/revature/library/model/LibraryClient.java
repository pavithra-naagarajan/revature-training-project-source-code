package com.revature.library.model;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import java.util.InputMismatchException;

import com.revature.library.service.LibraryService;
import com.revature.library.service.LibraryServiceImpl;

public class LibraryClient {
	static Scanner s = new Scanner(System.in);
	LibraryService libraryService = new LibraryServiceImpl();

	private static Logger logger = Logger.getLogger("LibraryClient");

	public void startMainMenu() {

		while (true) {
			logger.info("Home page:");
			logger.info("\n1.Login\n2.Create Account\n-1.Exit");
			logger.info("Enter your choice:");
			int choice = 0;

			try {
				choice = s.nextInt();
			} catch (InputMismatchException e) {

				logger.warn("Enter appropriate input values, your input is mismatched!");
				s.nextLine();
				startMainMenu();

			}

			switch (choice) {
			case 1:
				login();
				break;
			case 2:
				createNewAccount();
				break;

			case -1:
				logger.info("Thanks for using library Application!");
				System.exit(0);
				break;
			default:
				logger.warn("Incorrect input!........");
				break;

			}

		}

	}

	private void createNewAccount() {
		logger.info("CreateNewAccount is activated ");
		logger.info("Welcome to Account creation:");
		logger.info("**********************************************************************");
		logger.info("Enter userId:");
		try {
			int userId = s.nextInt();
			s.nextLine();
			logger.info("Enter userName:");
			String userName = s.nextLine();
			logger.info("Enter userRole:");
			String userRole = s.nextLine();
			logger.info("Enter userMailId:");
			String userMailId = s.nextLine();
			String regex = "^(.+)@(.+)$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(userMailId);
			if (matcher.matches()) {
				logger.info("success");
				logger.info("Enter userMobileNumber:");
				int userMobileNumber = s.nextInt();
				s.nextLine();
				logger.info("Enter userPassword:");
				String userPassword = s.nextLine();
				logger.info("Enter userPassword again to confirm:");
				String userPasswordAgain = s.nextLine();
				if (userPassword.equals(userPasswordAgain)) {
					logger.info("password verification is success!");
				} else {
					logger.warn("your confirm password is not matched with input password,try again!");

					startMainMenu();
				}
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date dateCreated = new Date();
				String dateCreatedOn = formatter.format(dateCreated);
				User user = (new User(userId, userName, userRole, userMailId, userMobileNumber, userPassword,
						dateCreatedOn));

				logger.info("Your account is created successfully!");
				logger.info("**********************************************************************");
				libraryService.addUser(user);
			}

			else {
				logger.warn("Enter appropriate emailId!");

				startMainMenu();

			}
		} catch (InputMismatchException e) {

			logger.warn("Enter appropriate input values, your input is mismatched!");

		}

	}

	private void login() {
		logger.info("Login is activated ");
		logger.info("Welcome to Login Section:");

		logger.info("Enter your login type:\n1: for login as Admin :\n2: for login as User: ");
		logger.info("**********************************************************************");

		int loginType = 0;
		try {
			loginType = s.nextInt();
		} catch (InputMismatchException e) {

			logger.warn("Enter appropriate choice your input is mismatched!");
			s.nextLine();

		}

		switch (loginType) {
		case 1:
			loginAsAdmin();
			break;
		case 2:
			loginAsUser();
			break;

		default:
			logger.warn("Incorrect type! Please enter the appropriate type........");

			startMainMenu();

			break;

		}
	}

	private void loginAsUser() {
		logger.info("UserLogin is activated ");
		logger.info("Welcome to User Login Section:");
		logger.info("Please Enter the details to login:");
		logger.info("**********************************************************************");
		logger.info("Enter userId:");
		try {
			int userId = s.nextInt();
			s.nextLine();
			logger.info("Enter userPassword:");
			String userPassword = s.nextLine();
			int flag = 0;
			List<User> users = libraryService.getAllUsers();

			for (User user : users) {
				if (user.getUserId() == userId && (user.getUserPassword().equals(userPassword))) {
					logger.info("your Login is finished successfully");
					flag = 1;
					break;
				}
			}

			if (flag == 1) {
				logger.info("Here your personal page! Welcome");
				UserAccessBook access = new UserAccessBook();
				access.userPage();
			} else {
				logger.warn("your user login details are not matched! try again");
				login();

			}
		} catch (InputMismatchException e) {

			logger.warn("Enter appropriate choice your input is mismatched!");

		}

	}

	private void loginAsAdmin() {
		logger.info("Admin Login is activated " + new Date());
		logger.info("Welcome to Admin Login Section:");
		logger.info("Please Enter the details to login:");
		logger.info("**********************************************************************");
		logger.info("Enter adminId:");
		try {
			int adminId = s.nextInt();
			s.nextLine();
			logger.info("Enter AdminPassword:");
			String adminPassword = s.nextLine();

			int flag = 0;
			List<Admin> admins = libraryService.getAllAdmins();

			for (Admin admin : admins) {
				if ((admin.getAdminId() == adminId) && (admin.getAdminPassword().equals(adminPassword))) {
					logger.info("your Login is finished successfully");
					logger.info("your role is :" + admin.getAdminRole());
					flag = 1;
				}
			}
			if (flag == 1) {
				System.out.println("Here your personal page! Welcome");
				UserAccessBook access = new UserAccessBook();

				access.adminPage(adminId);

			} else {
				logger.warn("your user login details are not matched! try again");
				login();

			}
		} catch (InputMismatchException e) {

			logger.warn("Enter appropriate choice your input is mismatched!");

		}

	}

}
