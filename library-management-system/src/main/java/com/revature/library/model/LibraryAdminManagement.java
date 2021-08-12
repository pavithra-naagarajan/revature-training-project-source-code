package com.revature.library.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.revature.library.dao.LibraryDAO;
import com.revature.library.dao.LibraryDAOImplementation;
import com.revature.library.dao.UpdateSection;
import com.revature.library.exceptions.NegativeBookId;

import java.util.InputMismatchException;
import com.revature.library.service.LibraryService;
import com.revature.library.service.LibraryServiceImpl;
import com.revature.library.util.DBConnection;

public class LibraryAdminManagement {

	static Scanner s = new Scanner(System.in);
	static LibraryService libraryservice = new LibraryServiceImpl();
	static boolean result;
	static User user;

	static Book book;
	static List<Book> bookList;
	private static Logger logger = Logger.getLogger("LibraryManagement");

	public void userAdmin() {

		while (true) {
			logger.info("\nMenu List Options:");
			logger.info("1.Add User ");
			logger.info("2.Delete User ");
			logger.info("3.Update User ");
			logger.info("4.Find User By UserId");
			logger.info("5.Find user by UserName");
			logger.info("-1.EXIT");
			int choice1 = 0;
			try {
				choice1 = s.nextInt();
			} catch (InputMismatchException e) {

				logger.warn("Enter appropriate choice your input is mismatched!");
				s.nextLine();
				userAdmin();

			}
			logger.info("Enter your choice : ");

			switch (choice1) {
			case 1:
				logger.info("Admin starting add user section ");
				logger.info("Wecome to Add User section , please enter user details to be saved:");
				user = acceptUserDetails();
				result = libraryservice.addUser(user);
				if (result)
					logger.info("#User with user name : " + user.getUserName() + " , saved successfully");
				else
					logger.warn("#User with user name : " + user.getUserName() + " , not saved successfully");

				break;
			case 2:
				logger.info("Admin starting  Delete User section ");
				logger.info("Wecome to Delete User section\nEnter userId to be deleted:");

				int userId = s.nextInt();

				result = libraryservice.deleteUser(userId);
				if (result)
					logger.info("#User with user Id : " + userId + " , deleted successfully");
				else
					logger.warn("User is not exist for given Id\n#User with user Id : " + userId
							+ " , not deleted successfully");

				break;
			case 3:
				logger.info("Admin starting update User section ");
				logger.info("Wecome to update User section , please enter userId to update ##");
				int userId1 = s.nextInt();

				UpdateSection update = new UpdateSection();
				update.updateUser(userId1);

				break;

			case 4:
				logger.info("Admin starting Find user by userId ");
				logger.info("Enter userId to find:");
				int userId11 = s.nextInt();

				user = libraryservice.getUserById(userId11);
				if (user == null)
					logger.warn("The user is not exist for given Id");
				else
					logger.info(user);
				break;

			case 5:
				logger.info("Admin starting Find user by userName ");
				logger.info("Enter userName to find:");
				s.nextLine();
				String userName = s.nextLine();
				user = libraryservice.getUserByName(userName);
				if (user == null)
					logger.warn("The user is not exist for given Name");
				else
					logger.info(user);
				break;

			case -1:
				logger.info("Admin exit the personal page");
				LibraryClient client = new LibraryClient();
				client.startMainMenu();
				break;
			default:
				logger.warn("Incorrect Input!");
				break;

			}
		}

	}

	public void bookAdmin()

	{

		while (true) {
			logger.info("\nMenu List Options:");
			logger.info("1.Add Book ");
			logger.info("2.Delete Book ");
			logger.info("3.Update Book ");

			logger.info("-1.EXIT");

			logger.info("Enter your choice : ");
			int choice2 = 0;
			try {
				choice2 = s.nextInt();
			} catch (InputMismatchException e) {

				logger.warn("Enter appropriate choice your input is mismatched!");
				s.nextLine();
				bookAdmin();
			}

			switch (choice2) {
			case 1:
				logger.info("Admin starting  Add Book section ");
				logger.info("Wecome to Add Book section , please enter book details to be saved:");
				book = acceptBookDetails();
				result = libraryservice.addBook(book);
				if (result)
					logger.info("#Book with book name : " + book.getBookName() + " , saved successfully");
				else
					logger.warn("#Book with book name : " + book.getBookName() + " , not saved successfully");

				break;

			case 2:
				logger.info("Admin starting Delete book section ");
				logger.info("Wecome to Delete Book section\nenter bookId to be deleted:");

				int bookId = 0;

				try {

					bookId = s.nextInt();

					result = libraryservice.deleteBook(bookId);
					if (result)
						logger.info("#Book with book Id : " + bookId + " , deleted successfully");
					else
						logger.warn("#Book not exist \nBook with book Id : " + bookId + " , not deleted successfully");

				} catch (NegativeBookId e) {

					logger.info(e.getMessage());
				}

				break;
			case 3:
				logger.info("Admin starting  update Book section");
				logger.info("Wecome to update Book section , please enter book details to update ##");
				book = acceptBookDetails();
				result = libraryservice.updateBook(book);
				if (result)
					logger.info("#Book with book Name : " + book.getBookName() + " , updated successfully");
				else
					logger.warn("#Book with book Name : " + book.getBookName() + " ,not updated successfully");
				break;

			case -1:
				logger.info("Admin exit the personal page");
				LibraryClient client = new LibraryClient();
				client.startMainMenu();
				break;
			default:
				logger.warn("Incorrect Input!");
				break;

			}
		}
	}

	public void fineAdmin() {

		while (true) {
			logger.info("\nMenu List Options:");
			logger.info("1.view All books");
			logger.info("2.view All Users ");
			logger.info("3.Display issued book details");

			logger.info("-1.EXIT");

			logger.info("Enter your choice : ");
			int choice3 = 0;
			try {
				choice3 = s.nextInt();
			} catch (InputMismatchException e) {

				logger.warn("Enter appropriate choice your input is mismatched!");
				s.nextLine();
				fineAdmin();
			}

			switch (choice3) {

			case 1:
				logger.info("Admin starting view all books ");
				logger.info("Wecome to view all Books section ##");
				List<Book> books = libraryservice.getAllBooks();
				if (CollectionUtils.isEmpty(books))
					logger.warn("The book list is empty");
				else {
					logger.info("###Entire list of Books:");
					for (Book book : books) {
						System.out.println(book);
					}
				}
				break;
			case 2:
				logger.info("Admin starting view all users");
				logger.info("Wecome to view all Users section ##");
				List<User> users = libraryservice.getAllUsers();

				if (CollectionUtils.isEmpty(users))
					logger.warn("The user list is empty");
				else {
					logger.info("###Entire list of users:");
					for (User user : users) {
						logger.info(user);
					}
				}
				break;
			case 3:
				logger.info("Admin starting display issued book details section ");
				logger.info("\nEnter the user's id to get the details of book borrowed");
				int userId = s.nextInt();

				LibraryDAO libraryDAO = new LibraryDAOImplementation();
				libraryDAO.bookDetails(userId);
				break;
			case -1:
				logger.info("Admin exit the personal page");
				LibraryClient client = new LibraryClient();
				client.startMainMenu();
				break;
			default:
				logger.warn("Incorrect Input!");
				break;
			}
		}
	}

	private static Book acceptBookDetails() {
		logger.info("ENTER BOOK DETAILS");
		logger.info("*********************************************************************************");
		logger.info("Enter book Id : ");
		int bookId = s.nextInt();
		s.nextLine();
		logger.info("Enter book name : ");
		String bookName = s.nextLine();
		logger.info("Enter Author name: ");
		String authorName = s.nextLine();
		logger.info("Enter book genre : ");
		String genre = s.next();
		logger.info("Enter book volume : ");
		int volume = s.nextInt();
		logger.info("Enter book edition: ");
		int edition = s.nextInt();

		book = new Book(bookId, bookName, authorName, genre, volume, edition);
		return book;
	}

	public static User acceptUserDetails() {
		logger.info("ENTER USER DETAILS");
		logger.info("*********************************************************************************");
		logger.info("Enter user Id : ");
		int userId = s.nextInt();
		s.nextLine();
		logger.info("Enter user name : ");
		String userName = s.next();
		logger.info("Enter user role : ");
		String userRole = s.next();
		logger.info("Enter user mailId : ");
		String userMailId = s.next();
		logger.info("Enter user mobileNumber : ");
		int userMobileNumber = s.nextInt();
		s.nextLine();
		logger.info("Enter user password: ");
		String userPassword = s.next();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreated = new Date();
		String dateCreatedOn = formatter.format(dateCreated);
		user = (new User(userId, userName, userRole, userMailId, userMobileNumber, userPassword, dateCreatedOn));
		return user;
	}

}
