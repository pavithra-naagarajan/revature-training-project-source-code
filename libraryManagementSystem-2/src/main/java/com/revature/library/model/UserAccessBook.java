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
	static LibraryService libraryService=new LibraryServiceImpl();
	static LibraryAdminManagement lib=new LibraryAdminManagement();
	static boolean result;
	static User user=new User();
	static Book book=new Book();
	static List<Book> bookList;
	private static Logger logger = Logger.getLogger("ClientAccessBook");

	public void userPage() {
		while (true) {
			logger.info("Menu List Options:");
			logger.info("1.search by BookId");
			logger.info("2.search by BookName");
			logger.info("3.search Book by AuthorName");
			logger.info("4.search Book by BookGenre");
			logger.info("5.Edit details of user-update");
			logger.info("-1.Exit");
			int choice = 0;
			try {
				choice = s.nextInt();

				switch (choice) {
				case 1:
					
					logger.info("Enter BookId to find:");
					int bookId1 = s.nextInt();

					book = libraryService.getBookById(bookId1);
					if (book == null)
						logger.warn("The book is not available");
					else {
						logger.info(book);
						logger.info("To issue Book-press I");
						logger.info("To skip Issue process-press S");
						s.nextLine();
						String choice1 = s.nextLine();
						switch (choice1) {
						case "I":
						
							libraryService.issueBook();
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
					
					logger.info("Enter BookName to find:");
					s.nextLine();
					String bookName = s.nextLine();
					book = libraryService.getBookByBookName(bookName);
					if (book == null)
						logger.warn("The book is not available");
					else {
						logger.info(book);
						logger.info("To issue Book-press I");
						logger.info("To skip Issue process-press S");

						String select = s.nextLine();
						switch (select) {
						case "I":
							
							libraryService.issueBook();
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
					
					logger.info("Enter AuthorName to find a book:");
					s.nextLine();
					String authorName = s.nextLine();
					bookList = libraryService.getBookByAuthorName(authorName);
					if (CollectionUtils.isEmpty(bookList))
						logger.info("The books are not available for given author name");
					else {

						bookList.stream().forEachOrdered(System.out::println);
						}
					
					break;
				case 4:
					
					logger.info("Enter BookGenre to find a book:");
					s.nextLine();
					String genre = s.nextLine();
					bookList = libraryService.getBookByGenre(genre);
					if (CollectionUtils.isEmpty(bookList))
						logger.info("The books are not available for given genre name");
					else {

						bookList.stream().forEachOrdered(System.out::println);
						}
					
					break;
				case 5:
					
					logger.info("Enter Edit details of user section :");
					user = LibraryAdminManagement.acceptUserDetails();
					result = libraryService.updateUser(user);

					logger.info(result ? "your details get updated successfully!"
							: "your details not updated successfully!");

					break;
				case -1:
					LibraryClient client = new LibraryClient();

					client.startMainMenu();

					break;
				default:
					logger.info("Incorrect Input");
					break;
				}
			} catch (InputMismatchException e) {

				logger.warn("Enter appropriate choice your input is mismatched!");
				s.nextLine();
				userPage();
			}
		}

	}

	public void adminPage(int adminId) {
		
		
		
		if (adminId == 5534)
			lib.userAdmin();
		else if (adminId == 4444)
			lib.fineAdmin();
		else
			lib.bookAdmin();

	}

}
