package com.revature.library.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.library.model.LibraryAdminManagement;
import com.revature.library.model.User;
import com.revature.library.service.LibraryService;
import com.revature.library.service.LibraryServiceImpl;
import com.revature.library.util.DBConnection;

public class UpdateSection {
	Logger logger = Logger.getLogger("UpdateSection");
	static Scanner s = new Scanner(System.in);
	Connection connection = DBConnection.getDBConnection();
	static User user = new User();
	static boolean result;
	static LibraryService libraryService = new LibraryServiceImpl();

	public void updateUser(int userId1) {
		int res = 0;
		logger.info(libraryService.isUserExists(userId1) ? res = 1 : res <= 0);
		if (res == 1) {
			logger.info("Enter the attribute to update");
			logger.info("1. userName");
			logger.info("2. emailId");
			logger.info("3. mobileNumber");
			logger.info("4. password");
			logger.info("5. To edit entire details");
			int adminChoice = s.nextInt();

			switch (adminChoice) {
			case 1:

				logger.info("Enter new name to update");
				s.nextLine();
				String newName = s.nextLine();
				String query = "update  hr.users set userName=? where userId=?";
				PreparedStatement preparedStatement;
				int executeResult = 0;
				try {
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, newName);
					preparedStatement.setInt(2, userId1);
					executeResult = preparedStatement.executeUpdate();
				} catch (SQLException e) {

					e.printStackTrace();
				}

				logger.info(executeResult > 0 ? "User's name is updated" : "");
				;

				break;

			case 2:

				logger.info("Enter new emailId to update");
				s.nextLine();
				String newEmailId = s.nextLine();
				String query1 = "update  hr.users set userMailId=? where userId=?";
				PreparedStatement preparedStatement1;
				int executeResult1 = 0;
				try {
					preparedStatement1 = connection.prepareStatement(query1);
					preparedStatement1.setString(1, newEmailId);
					preparedStatement1.setInt(2, userId1);
					executeResult1 = preparedStatement1.executeUpdate();
				} catch (SQLException e) {

					e.printStackTrace();
				}

				logger.info(executeResult1 > 0 ? "User's email Id is updated" : "");

				break;

			case 3:

				logger.info("Enter new MobileNumber to update");
				s.nextLine();
				int newMobileNumber = s.nextInt();
				String query11 = "update hr.users set userMobileNumber=? where userId=?";
				PreparedStatement preparedStatement11;
				int executeResult11 = 0;
				try {
					preparedStatement11 = connection.prepareStatement(query11);
					preparedStatement11.setInt(1, newMobileNumber);
					preparedStatement11.setInt(2, userId1);
					executeResult11 = preparedStatement11.executeUpdate();
				} catch (SQLException e) {

					e.printStackTrace();
				}

				logger.info(executeResult11 > 0 ? "User's mobileNumber is updated" : "");

				break;

			case 4:
				logger.info("Enter newPassword to update");
				s.nextLine();
				String newPassword = s.nextLine();
				String query2 = "update  hr.users set userPassword=? where userId=?";
				PreparedStatement preparedStatement2;
				int executeResult2 = 0;
				try {
					preparedStatement2 = connection.prepareStatement(query2);
					preparedStatement2.setString(1, newPassword);
					preparedStatement2.setInt(2, userId1);
					executeResult2 = preparedStatement2.executeUpdate();
				} catch (SQLException e) {

					e.printStackTrace();
				}

				logger.info(executeResult2 > 0 ? "password is updated" : "");

				break;
			case 5:
				logger.info("To edit all the details");

				LibraryAdminManagement library = new LibraryAdminManagement();
				user = library.acceptUserDetails();
				result = libraryService.updateUser(user);

				logger.info(result ? "#User with user Name : " + user.getUserName() + " , updated successfully"
						: "#User with userName : " + user.getUserName() + " ,not updated successfully");

				break;
			default:
				logger.warn("Incorrect Input!....");
				break;

			}
		} else {
			logger.warn("User not exist for given user id!");
		}

	}

}

		
	
