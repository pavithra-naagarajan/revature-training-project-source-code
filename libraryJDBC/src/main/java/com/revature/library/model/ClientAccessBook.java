package com.revature.library.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.library.dao.LibraryDAO;
import com.revature.library.dao.LibraryDAOImplementation;

public class ClientAccessBook {
	int choice = 0;
	static Scanner s = new Scanner(System.in);
	LibraryDAO libraryDAO = new LibraryDAOImplementation();
	static boolean result;
	User user;
	Book book;
	static List<Book> bookList;
	private static Logger logger = Logger.getLogger("ClientAccessBook");

	public void personalPageForUser() {
		while (true) {
			System.out.println("\nMenu List Options:");
			System.out.println("1.search by BookId");
			System.out.println("2.search by BookName");
			System.out.println("3.search Book by AuthorName");
			System.out.println("4.search Book by BookGenre");
			System.out.println("5.Edit details of user-update");
			System.out.println("-1.Exit");

			int choice = s.nextInt();

			switch (choice) {
			case 1:
				logger.info("user starting search book by Id " + new Date());
				System.out.println("Enter BookId to find:");
				int bookId1 = s.nextInt();
				book = libraryDAO.getBookById(bookId1);
				System.out.println(book);
				System.out.println("To issue Book-press I\nTo skip Issue process=press S");
				s.nextLine();
				String choice1 = s.nextLine();
				switch (choice1) {
				case "I":
					issueBook();
					break;
				case "S":
					System.out.println("Your Issue process is skipped");
					break;
				default:
					System.out.println("Invalid Input");
					break;
				}

				break;
			case 2:
				logger.info("user starting search book by BookName " + new Date());
				System.out.println("Enter BookName to find:");
				s.nextLine();
				String bookName = s.nextLine();
				book = libraryDAO.getBookByBookName(bookName);
				System.out.println(book);
				System.out.println("To issue Book-press I\nTo skip Issue process=press S");

				String select = s.nextLine();
				switch (select) {
				case "I":
					issueBook();
					break;
				case "S":
					System.out.println("Your Issue process is skipped");
					break;
				default:
					System.out.println("Invalid Input");
					break;
				}
				break;

			case 3:
				logger.info("user starting search book by AuthorName " + new Date());
				System.out.println("Enter AuthorName to find a book:");
				s.nextLine();
				String authorName = s.nextLine();
				bookList = libraryDAO.getBookByAuthorName(authorName);
				for (Book book : bookList) {
					System.out.println(book);
				}
				break;
			case 4:
				logger.info("user starting search book by GenreName " + new Date());
				System.out.println("Enter BookGenre to find a book:");
				s.nextLine();
				String genre = s.nextLine();
				bookList = libraryDAO.getBookByGenre(genre);
				for (Book book : bookList) {
					System.out.println(book);
				}
				break;
			case 5:
				logger.info("user starting Edit details of user section " + new Date());
				System.out.println("Enter Edit details of user section :");
				user = LibraryManagement.acceptUserDetails();
				libraryDAO.updateUser(user);
				System.out.println("your details get updated successfully!");
				break;
			case -1:
				System.out.println("##Thanks for using !");
				System.exit(0);
				break;
			}
		}
	}

	private static void issueBook() {
		System.out.println("your book is issued successfully");
		System.out.println("*********************************************************************");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		LocalDate currentDate = LocalDate.now();
		LocalDate returnDate = currentDate.plusDays(15);
		System.out.println("your issue date is: " + formatter.format(date));
		System.out.println("your due date is: " + returnDate);
		System.out.println("*********************************************************************");
	}

	public void personalPageForAdmin() {
		LibraryManagement lib = new LibraryManagement();
		lib.startMenu();

	}

}
