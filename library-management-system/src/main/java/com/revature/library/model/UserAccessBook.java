package com.revature.library.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.commons.collections.*;
import com.revature.library.dao.LibraryDAO;
import com.revature.library.dao.LibraryDAOImplementation;
import com.revature.library.service.LibraryService;
import com.revature.library.service.LibraryServiceImpl;
import com.revature.library.util.DBConnection;

public class UserAccessBook {

	static Scanner s = new Scanner(System.in);
	static LibraryService libraryservice = new LibraryServiceImpl();
	static boolean result;
	User user;
	Book book;
	static List<Book> bookList;
	private static Logger logger = Logger.getLogger("ClientAccessBook");

	public void userPage() {
		while (true) {
			logger.info("\nMenu List Options:");
			logger.info("1.search by BookId");
			logger.info("2.search by BookName");
			logger.info("3.search Book by AuthorName");
			logger.info("4.search Book by BookGenre");
			logger.info("5.Edit details of user-update");
			logger.info("-1.Exit");
			int choice = 0;
			try {
				choice = s.nextInt();
			} catch (InputMismatchException e) {

				logger.warn("Enter appropriate choice your input is mismatched!");
				s.nextLine();
				userPage();
			}

			switch (choice) {
			case 1:
				logger.info("user starting search book by Id " + new Date());
				logger.info("Enter BookId to find:");
				int bookId1 = s.nextInt();

				book = libraryservice.getBookById(bookId1);
				if (book == null)
					logger.warn("The book is not available");
				else {
					logger.info(book);
					logger.info("To issue Book-press I\nTo skip Issue process=press S");
					s.nextLine();
					String choice1 = s.nextLine();
					switch (choice1) {
					case "I":
						LibraryDAO libraryDAO = new LibraryDAOImplementation();
						libraryDAO.issueBook();
						break;
					case "S":
						logger.info("Your Issue process is skipped");
						break;
					default:
						logger.info("Invalid Input");
						break;
					}
				}

				break;
			case 2:
				logger.info("user starting search book by BookName " + new Date());
				logger.info("Enter BookName to find:");
				s.nextLine();
				String bookName = s.nextLine();
				book = libraryservice.getBookByBookName(bookName);
				if (book == null)
					logger.warn("The book is not available");
				else {
					logger.info(book);
					logger.info("To issue Book-press I\nTo skip Issue process=press S");

					String select = s.nextLine();
					switch (select) {
					case "I":
						LibraryDAO libraryDAO = new LibraryDAOImplementation();
						libraryDAO.issueBook();
						break;
					case "S":
						logger.info("Your Issue process is skipped");
						break;
					default:
						logger.info("Invalid Input");
						break;
					}
				}
				break;

			case 3:
				logger.info("user starting search book by AuthorName " + new Date());
				logger.info("Enter AuthorName to find a book:");
				s.nextLine();
				String authorName = s.nextLine();
				bookList = libraryservice.getBookByAuthorName(authorName);
				if (CollectionUtils.isEmpty(bookList))
					logger.info("The books are not available for given author name");
				else {

					for (Book book : bookList) {
						System.out.println(book);
					}
				}
				break;
			case 4:
				logger.info("user starting search book by GenreName " + new Date());
				logger.info("Enter BookGenre to find a book:");
				s.nextLine();
				String genre = s.nextLine();
				bookList = libraryservice.getBookByGenre(genre);
				if (CollectionUtils.isEmpty(bookList))
					logger.info("The books are not available for given genre name");
				else {

					for (Book book : bookList) {
						System.out.println(book);
					}
				}
				break;
			case 5:
				logger.info("user starting Edit details of user section " + new Date());
				logger.info("Enter Edit details of user section :");
				user = LibraryAdminManagement.acceptUserDetails();
				result = libraryservice.updateUser(user);
				if (result)
					logger.info("your details get updated successfully!");
				else
					logger.warn("your details not updated successfully!");
				break;
			case -1:
				LibraryClient client = new LibraryClient();

				client.startMainMenu();

				break;

			}
		}
	}

	public void adminPage(int adminId) {
		LibraryAdminManagement lib = new LibraryAdminManagement();
		if (adminId == 5534)
			lib.userAdmin();
		else if (adminId == 4444)
			lib.fineAdmin();
		else
			lib.bookAdmin();

	}

}
