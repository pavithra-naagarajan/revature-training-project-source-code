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
	static LibraryService libraryService = new LibraryServiceImpl();
	static boolean result;
	static User user=new User();
	static LibraryClient client =new LibraryClient();
	static UpdateSection update = new UpdateSection();
	static Book book=new Book();
	
	private static Logger logger = Logger.getLogger("LibraryManagement");

	public void userAdmin() {

		while (true) {
			logger.info("Menu List Options:");
			logger.info("1.Add User ");
			logger.info("2.Delete User ");
			logger.info("3.Update User ");
			logger.info("4.Find User By UserId");
			logger.info("5.Find user by UserName");
			logger.info("-1.EXIT");
			logger.info("Enter your choice : ");
			int choice1 = 0;
			try {
				choice1 = s.nextInt();

				

				switch (choice1) {
				case 1:
					
					logger.info("Wecome to Add User section , please enter user details to be saved:");
					user = acceptUserDetails();
					result = libraryService.addUser(user);

					logger.info(result ? "#User with user name : " + user.getUserName() + " , saved successfully"
							: "#User with user name : " + user.getUserName() + " , not saved successfully");

					break;
				case 2:
					
					logger.info("Wecome to Delete User section: Enter userId to be deleted:");

					int userId = s.nextInt();

					result = libraryService.deleteUser(userId);

					logger.info(result ? "#User with user Id : " + userId + " , deleted successfully"
							: "User is not exist for given Id");

					break;
				case 3:
					
					logger.info("Wecome to update User section , please enter userId to update ##");
					int userId1 = s.nextInt();

					
					update.updateUser(userId1);

					break;

				case 4:
					
					logger.info("Enter userId to find:");
					int userId11 = s.nextInt();

					user = libraryService.getUserById(userId11);

					logger.info(user == null ? "The user is not exist for given Id" : user);

					break;

				case 5:
					logger.info("Admin started Find user by userName ");
					logger.info("Enter userName to find:");
					s.nextLine();
					String userName = s.nextLine();
					user = libraryService.getUserByName(userName);

					logger.info(user == null ? "The user is not exist for given Name" : user);

					break;

				case -1:
					logger.info("Admin exit the personal page");
					
					client.startMainMenu();
					break;
				default:
					logger.warn("Incorrect Input!");
					break;

				}
			} catch (InputMismatchException e) {

				logger.warn("Enter appropriate choice your input is mismatched!");
				s.nextLine();
				userAdmin();

			}
		}

	}

	public void bookAdmin()

	{

		while (true) {
			logger.info("Menu List Options:");
			logger.info("1.Add Book ");
			logger.info("2.Delete Book ");
			logger.info("3.Update Book ");

			logger.info("-1.EXIT");

			logger.info("Enter your choice : ");
			int choice2 = 0;
			try {
				choice2 = s.nextInt();

				switch (choice2) {
				case 1:
					logger.info("Wecome to Add Book section , please enter book details to be saved:");
					book = acceptBookDetails();
					result = libraryService.addBook(book);

					logger.info(result ? "#Book with book name : " + book.getBookName() + " , saved successfully"
							: "#Book with book name : " + book.getBookName() + " , not saved successfully");

					break;

				case 2:
					logger.info("Wecome to Delete Book section");
					logger.info("enter bookId to be deleted:");

					int bookId = 0;

					try {

						bookId = s.nextInt();

						result = libraryService.deleteBook(bookId);

						logger.info(result ? "#Book with book Id : " + bookId + " , deleted successfully"
								: "#Book with book Id : " + bookId + " , not exist");

					} catch (NegativeBookId e) {

						logger.info(e.getMessage());
					}

					break;
				case 3:
					
					logger.info("Wecome to update Book section , please enter book details to update ##");
					book = acceptBookDetails();
					result = libraryService.updateBook(book);

					logger.info(result ? "#Book with book Name : " + book.getBookName() + " , updated successfully"
							: "#Book with book Name : " + book.getBookName() + " ,not updated successfully");

					break;

				case -1:
					logger.info("Admin exit the personal page");
					
					client.startMainMenu();
					break;
				default:
					logger.warn("Incorrect Input!");
					break;

				}
			} catch (InputMismatchException e) {

				logger.warn("Enter appropriate choice your input is mismatched!");
				s.nextLine();
				bookAdmin();
			}
		}
	}

	public void fineAdmin() {

		while (true) {
			logger.info("Menu List Options:");
			logger.info("1.view All books");
			logger.info("2.view All Users ");
			logger.info("3.Display issued book details");

			logger.info("-1.EXIT");

			logger.info("Enter your choice : ");
			int choice3 = 0;
			try {
				choice3 = s.nextInt();

				switch (choice3) {

				case 1:
					
					logger.info("Wecome to view all Books section ##");
					List<Book> books = libraryService.getAllBooks();
					if (CollectionUtils.isEmpty(books))
						logger.warn("The book list is empty");
					else {
						logger.info("###Entire list of Books:");
						
						books.stream().forEachOrdered(System.out::println);
					}
					break;
				case 2:
					
					logger.info("Wecome to view all Users section ##");
					List<User> users = libraryService.getAllUsers();

					if (CollectionUtils.isEmpty(users))
						logger.warn("The user list is empty");
					else {
						logger.info("###Entire list of users:");
						users.stream().forEachOrdered(System.out::println);
						}
					
					break;
				case 3:
					logger.info("Welcome to display issued book details section ");
					logger.info("Enter the user's id to get the details of book borrowed");
					int userId = s.nextInt();

					
					libraryService.bookDetails(userId);
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
			} catch (InputMismatchException e) {

				logger.warn("Enter appropriate choice your input is mismatched!");
				s.nextLine();
				fineAdmin();
			}

		}
	}

	private static Book acceptBookDetails() {
		logger.info("ENTER BOOK DETAILS");
		logger.info("*********************************************************************************");
		try {
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
			
		} catch (InputMismatchException e) {

			logger.warn("Enter appropriate choice your input is mismatched!");
			s.nextLine();

		}
		return book;
	}

	public static User acceptUserDetails() {
		logger.info("ENTER USER DETAILS");
		logger.info("*********************************************************************************");
		logger.info("Enter user Id : ");
		try {
			int userId = s.nextInt();
			s.nextLine();
			
			logger.info("Enter user name : ");
			String userName = s.next();
			logger.info("Enter user role : ");
			String userRole = s.next();
			logger.info("Enter user mailId : ");
			String userMailId = s.next();
			logger.info("Enter user mobileNumber : ");
			String userMobileNumber = s.nextLine();
			s.nextLine();
			logger.info("Enter user password: ");
			String userPassword = s.next();
			Date createdOn  =new Date();
			
			user = (new User(userId, userName, userRole, userMailId, userMobileNumber, userPassword, createdOn));
			
			

		}
			catch (InputMismatchException e) {

			logger.warn("Enter appropriate choice your input is mismatched!");
			s.nextLine();
		}
		return user;
		

	}
	

}
