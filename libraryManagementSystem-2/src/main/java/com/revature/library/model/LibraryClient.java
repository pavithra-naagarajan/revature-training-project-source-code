package com.revature.library.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
	static LibraryService libraryService = new LibraryServiceImpl();
	static UserAccessBook access = new UserAccessBook();
	private static Logger logger = Logger.getLogger("LibraryClient");
	static Admin admin = new Admin();
	static User user = new User();

	public void startMainMenu() {

		while (true) {
			logger.info("Home page:");
			logger.info("1.Login");
			logger.info("2.Create Account");
			logger.info("-1.Exit");
			logger.info("Enter your choice:");
			int choice = 0;

			try {
				choice = s.nextInt();

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
			} catch (InputMismatchException e) {

				logger.info("Enter appropriate input values, your input is mismatched!");
				s.nextLine();
				startMainMenu();

			}

		}

	}

	private void createNewAccount() {

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
			String regex = "^[a-z0-9+_.-]+@[a-z0-9.-]+$";

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(userMailId);
			if (matcher.matches()) {

				logger.info("Enter userMobileNumber:");
				String userMobileNumber = s.nextLine();
				if (userMobileNumber.length() != 10) {
					logger.info("enter appropriate 10 digit mobile number");
					startMainMenu();
				} else {
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

					Date createdOn = new Date();
				
					User user = (new User(userId, userName, userRole, userMailId, userMobileNumber, userPassword,
							createdOn));

					logger.info("Your account is created successfully!");
					logger.info("**********************************************************************");
					libraryService.addUser(user);
				}
			}

			else {
				logger.warn("Enter appropriate emailId!");

				startMainMenu();

			}
		} catch (InputMismatchException e) {

			logger.warn("Enter appropriate choice your input is mismatched!");
			s.nextLine();
		}

	}

	private void login() {

		logger.info("Welcome to Login Section:");

		logger.info("Enter your login type:");
		logger.info("1: for login as Admin :");
		logger.info("2: for login as User: ");
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

		logger.info("Welcome to User Login Section:");
		logger.info("Please Enter the details to login:");
		logger.info("**********************************************************************");
		logger.info("Enter userId:");
		try {
			int userId = s.nextInt();
			s.nextLine();
			logger.info("Enter userPassword:");
			String userPassword = s.nextLine();

			List<User> users = libraryService.getAllUsers();

			user = users.stream()
					.filter(u -> userId == (u.getUserId()))
					.filter(u -> userPassword.equals(u.getUserPassword()))
					.findAny().orElse(null);

			if (user != null) {
				logger.info("Here your personal page! Welcome");

				access.userPage();
			} else {
				logger.warn("your user login details are not matched! try again");
				login();

			}
		} catch (InputMismatchException e) {

			logger.warn("Enter appropriate choice your input is mismatched!");
			s.nextLine();

		}

	}

	private void loginAsAdmin() {

		logger.info("Welcome to Admin Login Section:");
		logger.info("Please Enter the details to login:");
		logger.info("**********************************************************************");
		logger.info("Enter adminId:");
		try {
			int adminId = s.nextInt();
			s.nextLine();
			logger.info("Enter AdminPassword:");
			String adminPassword = s.nextLine();
			
			
			List<Admin> admins = libraryService.getAllAdmins();

			
			
			 Admin admin=admins.stream() 
					  .filter(u-> adminId==(u.getAdminId())) 
					  .filter(u->adminPassword.equals(u.getAdminPassword())) 
					  .findAny().orElse(null);

			if (admin != null) {
				System.out.println("Here your personal page! Welcome");

				access.adminPage(adminId);

			}

			else {
				logger.warn("your user login details are not matched! try again");
				login();

			}
		} catch (InputMismatchException e) {

			logger.warn("Enter appropriate choice your input is mismatched!");
			s.nextLine();

		} 
	}

}
