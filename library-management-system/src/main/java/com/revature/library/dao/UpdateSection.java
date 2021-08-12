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
import com.revature.library.util.DBConnection;

public class UpdateSection {
	Logger logger=Logger.getLogger("UpdateSection");
	static Scanner s=new Scanner(System.in);
	Connection connection = DBConnection.getDBConnection();
	static User user;
	static boolean result;
	
	public void updateUser(int userId1) {
	
		System.out.println("\nEnter the attribute to update \n1 userName\n2 emailId\n3 mobileNumber\n4 password \n5 To edit entire details");
		int adminChoice=s.nextInt();
		
		
		switch(adminChoice)
		{ 
		case 1:
		
			System.out.println("Enter new name to update");
			s.nextLine();
			String newName=s.nextLine();
			String query="update  hr.users set userName=? where userId=?";
			PreparedStatement preparedStatement;
			int executeResult=0;
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, newName);
				preparedStatement.setInt(2, userId1);
				executeResult=preparedStatement.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			if(executeResult>0)
				logger.info("User's name is updated");
			else
				logger.warn("Invalid User id!!");
			break;
	
		case 2:
		
			System.out.println("Enter new emailId to update");
			s.nextLine();
			String newEmailId=s.nextLine();
			String query1="update  hr.users set userMailId=? where userId=?";
			PreparedStatement preparedStatement1;
			int executeResult1=0;
			try {
				preparedStatement1 = connection.prepareStatement(query1);
				preparedStatement1.setString(1, newEmailId);
				preparedStatement1.setInt(2, userId1);
				executeResult1=preparedStatement1.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			if(executeResult1>0)
				logger.info("Email Id is updated");
			else
				logger.warn("Invalid user id!!");
			break;
		
		case 3:
		
			System.out.println("Enter new MobileNumber to update");
			s.nextLine();
			int newMobileNumber=s.nextInt();
			String query11="update hr.users set userMobileNumber=? where userId=?";
			PreparedStatement preparedStatement11;
			int executeResult11=0;
			try {
				preparedStatement11 = connection.prepareStatement(query11);
				preparedStatement11.setInt(1, newMobileNumber);
				preparedStatement11.setInt(2, userId1);
				executeResult11=preparedStatement11.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
			if(executeResult11>0)
				logger.info("mobile Number is updated");
			else
				logger.warn("Invalid user user id!!");
			break;
	
		case 4:
			System.out.println("Enter newPassword to update");
			s.nextLine();
			String newPassword=s.nextLine();
			String query2="update  hr.users set userPassword=? where userId=?";
			PreparedStatement preparedStatement2;
			int executeResult2=0;
			try {
				preparedStatement2 = connection.prepareStatement(query2);
				preparedStatement2.setString(1, newPassword);
				preparedStatement2.setInt(2, userId1);
				executeResult2=preparedStatement2.executeUpdate();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			if(executeResult2>0)
				logger.info("password is updated");
			else
				logger.warn("Invalid user user id!!");
			break;
		case 5:
			System.out.println("To edit all the details");
			LibraryDAO libraryDAO=new LibraryDAOImplementation();
			LibraryAdminManagement library =new LibraryAdminManagement();
			user=library.acceptUserDetails();
			result = libraryDAO.updateUser(user);
			if (result)
				logger.info("#User with user Name : " + user.getUserName() + " , updated successfully");
			else
				logger.warn("#User with userName : " + user.getUserName() + " ,not updated successfully");
			break;
		default:
			break;
		
		}
		
	}
	

}
