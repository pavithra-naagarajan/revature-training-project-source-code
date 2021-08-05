package com.revature.library.model;

import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.library.dao.LibraryDAO;
import com.revature.library.dao.LibraryDAOImplementation;

public class LibraryManagement {

	static int choice = 0;
	static Scanner s = new Scanner(System.in);
	static LibraryDAO libraryDAO = new LibraryDAOImplementation();
	static boolean result;
	static User user;
	static Book book;
	static List<Book> bookList;
	private static Logger logger = Logger.getLogger("LibraryManagement");

	public static void startMenu() {

		
		while(true) {
			System.out.println("\nMenu List Options:");
			System.out.println("1.Add User ");
			System.out.println("2.Delete User ");
			System.out.println("3.Update User ");
			System.out.println("4.Add book ");
			System.out.println("5.Delete book ");
			System.out.println("6.Update book ");
			System.out.println("7.Find User By UserId");
			System.out.println("8.view All Books");
			System.out.println("9.View All users");
			System.out.println("10.Find user by UserName");
			
			System.out.println("-1.E X I T ");

			System.out.println("Enter your choice : ");
			choice = s.nextInt();

			switch (choice) {
			case 1:
				logger.info("Admin starting add user section "+new Date());
				System.out.println("Wecome to Add User section , please enter user details to be saved:");
				user = acceptUserDetails();
				result = libraryDAO.addUser(user);
				if (result)
					logger.info("#User with user name : " + user.getUserName() + " , saved successfully");
				else
					logger.info("#User with user name : " + user.getUserName() + " , not saved successfully");

				break;
			case 2:
				logger.info("Admin starting  Delete User section "+new Date());
				System.out.println("Wecome to Delete User section\nEnter userId to be deleted:");

				int userId=s.nextInt();
				result = libraryDAO.deleteUser(userId);
				if (result)
					logger.info("#User with user Id : " +userId + " , deleted successfully");
				else
					logger.info("#User with user Id : " + userId + " , not deleted successfully");

				break;
			case 3:
				logger.info("Admin starting update User section "+new Date());
				System.out.println("Wecome to update User section , please enter user details to update ##");
				user = acceptUserDetails();
				result = libraryDAO.updateUser(user);
				if (result)
					logger.info("#User with user Name : " + user.getUserName() + " , updated successfully");
				else
					logger.info("#User with user Name : " + user.getUserName() + " ,not updated successfully");

				break;
			case 4:
				logger.info("Admin starting  Add Book section "+new Date());
				System.out.println("Wecome to Add Book section , please enter book details to be saved:");
				book = acceptBookDetails();
				result = libraryDAO.addBook(book);
				if (result)
					logger.info("#Book with book name : " + book.getBookName() + " , saved successfully");
				else
					logger.info("#Book with book name : " + book.getBookName() + " , not saved successfully");

				break;
			case 5:
				logger.info("Admin starting Delete book section "+new Date());
				System.out.println("Wecome to Delete Book section\nenter bookId to be deleted:");

				int bookId=s.nextInt();
				
				result = libraryDAO.deleteBook(bookId);
				if (result)
					logger.info("#Book with book Id : " + bookId + " , deleted successfully");
				else
					logger.info("#Book with book Id : " + bookId + " , not deleted successfully");

				break;
				
			case 6:
				logger.info("Admin starting  update Book section" +new Date());
				System.out.println("Wecome to update Book section , please enter book details to update ##");
				book = acceptBookDetails();
				result = libraryDAO.updateBook(book);
				if (result)
					logger.info("#Book with book Name : " + book.getBookName() + " , updated successfully");
				else
					logger.info("#Book with book Name : " + book.getBookName() + " ,not updated successfully");

				break;
				
				
			case 7:
				logger.info("Admin starting Find user by userId "+new Date());
				System.out.println("Enter userId to find:");
				int userId1=s.nextInt();
				user=libraryDAO.getUserById(userId1);
				System.out.println(user);
				break;
			case 8:
				logger.info("Admin starting view all books "+new Date());
				System.out.println("Wecome to view all Books section ##");
				List<Book> books = libraryDAO.getAllBooks();
				System.out.println("###Entire list of Books:");
				for(Book book:books) {
					System.out.println(book);
				}
				break;
			case 9:
				logger.info("Admin starting view all users" +new Date());
				System.out.println("Wecome to view all Users section ##");
				List<User> users = libraryDAO.getAllUsers();
				System.out.println("###Entire list of users:");
				for(User user:users) {
					System.out.println(user);
				}
				break;
			
			case 10:
				logger.info("Admin starting Find user by userName");
				System.out.println("Enter userName to find:");
				s.nextLine();
				String userName=s.nextLine();
				user=libraryDAO.getUserByName(userName);
				System.out.println(user);
				break;
			
				
			case -1:
				logger.info("Admin exit the personal page" +new Date());
				LibraryClient client=new LibraryClient();
				client.startMainMenu();
				System.exit(0);
				
				break;
			}
		}


	}

	private static void issueBook() {
		System.out.println("your book is issued successfully");
		System.out.println("*********************************************************************");
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		LocalDate currentDate=LocalDate.now();
		LocalDate returnDate=currentDate.plusDays(15);
		System.out.println("your issue date is: "+formatter.format(date));
		System.out.println("your due date is: "+returnDate);
		System.out.println("*********************************************************************");
	}



	private static Book acceptBookDetails() {
		System.out.println("ENTER BOOK DETAILS");
		System.out.println("*********************************************************************************");
		System.out.println("Enter book Id : ");
		int bookId = s.nextInt();
		s.nextLine();
		System.out.println("Enter book name : ");
		String bookName = s.nextLine();
		System.out.println("Enter Author name: ");
		String authorName = s.nextLine();
		System.out.println("Enter book genre : ");
		String genre = s.next();
		System.out.println("Enter book volume : ");
		int volume = s.nextInt();
		System.out.println("Enter book edition: ");
		int edition = s.nextInt();

		book = new Book(bookId, bookName, authorName, genre, volume, edition);
		return book;
	}

	public static User acceptUserDetails() {
		System.out.println("ENTER USER DETAILS");
		System.out.println("*********************************************************************************");
		System.out.println("Enter user Id : ");
		int userId = s.nextInt();
		System.out.println("Enter user name : ");
		String userName = s.next();
		System.out.println("Enter user role : ");
		String userRole = s.next();
		System.out.println("Enter user mailId : ");
		String userMailId = s.next();
		System.out.println("Enter user mobileNumber : ");
		int userMobileNumber = s.nextInt();
		System.out.println("Enter user password: ");
		String userPassword = s.next();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreated = new Date();
		String dateCreatedOn = formatter.format(dateCreated);
		user = (new User(userId, userName, userRole, userMailId, userMobileNumber, userPassword, dateCreatedOn));
		return user;
	}

	
}
