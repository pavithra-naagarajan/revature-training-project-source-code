package com.revature.library.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.library.exceptions.NegativeBookId;
import com.revature.library.model.Admin;
import com.revature.library.model.Book;
import com.revature.library.model.User;
import com.revature.library.util.DBConnection;

public class LibraryDAOImplementation implements LibraryDAO {

	final LocalDateTime localtime = LocalDateTime.now(ZoneOffset.UTC);
	final java.sql.Timestamp localTimeStamp = java.sql.Timestamp.valueOf(localtime);

	Connection connection = DBConnection.getDBConnection();
	private final String ADD_USER_QUERY = "insert into hr.users values(?,?,?,?,?,?,?)";
	private final String UPDATE_USER_QUERY = "update hr.users set userName=?,userRole=?,userMailId=?,userMobileNumber=?,userPassword=?,dateCreatedOn=? where userId=?";
	private final String GET_ALL_USERS = "select * from hr.users";

	private final String DELETE_USER_QUERY = "delete from hr.users where userId=?";
	private final String GET_USER_BY_ID = "select * from hr.users where userId=?";
	private final String GET_USER_BY_NAME = "select * from hr.users where userName=?";

	private final String ADD_BOOK_QUERY = "insert into hr.books values(?,?,?,?,?,?)";
	private final String UPDATE_BOOK_QUERY = "update hr.books set bookName=?,authorName=?,genre=?,volume=?,edition=? where bookId=?";
	private final String GET_ALL_BOOKS = "select * from hr.books";

	private final String DELETE_BOOK_QUERY = "delete from hr.books where bookId=?";
	private final String GET_BOOK_BY_ID = "select * from hr.books where bookId=?";
	private final String GET_BOOK_BY_BOOKNAME = "select * from hr.books where bookName=?";
	private final String GET_BOOK_BY_AUTHORNAME = "select * from hr.books where authorName=?";
	private final String GET_BOOK_BY_BOOKGENRE = "select * from hr.books where genre=?";

	private static Logger logger = Logger.getLogger("LibraryDAOImplementation");

	public boolean addUser(User user) {
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(ADD_USER_QUERY);
			statement.setInt(1, user.getUserId());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getUserRole());
			statement.setString(4, user.getUserMailId());
			statement.setString(5, user.getUserMobileNumber());
			statement.setString(6, user.getUserPassword());
			statement.setTimestamp(7, localTimeStamp);
			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean val= res==0 ? false :true;
		return val;
	}

	public boolean updateUser(User user) {
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);

			statement.setString(1, user.getUserName());
			statement.setString(2, user.getUserRole());
			statement.setString(3, user.getUserMailId());
			statement.setString(4, user.getUserMobileNumber());
			statement.setString(5, user.getUserPassword());
			statement.setTimestamp(6, localTimeStamp);

			statement.setInt(7, user.getUserId());
			res = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean val= res==0 ? false :true;
		return val;
	}

	public boolean deleteUser(int userId) {
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);
			statement.setInt(1, userId);
			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean val= res==0 ? false :true;
		return val;

	}

	public User getUserById(int userId) {

		List<User> users = getAllUsers();

		User user = new User();
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID);
			statement.setInt(1, user.getUserId());
			statement.execute();
			for (User u : users) {
				if (u.getUserId() == userId) {

					user.setUserId(u.getUserId());
					user.setUserName(u.getUserName());
					user.setUserRole(u.getUserRole());
					user.setUserMobileNumber(u.getUserMobileNumber());
					user.setUserMailId(u.getUserMailId());
					user.setUserPassword(u.getUserPassword());
					user.setCreatedOn(u.getCreatedOn());
					res = 1;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (res == 1)
			return user;
		else
			return null;

	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(GET_ALL_USERS);
			while (res.next()) {
				User user = new User();
				user.setUserId(res.getInt(1));
				user.setUserName(res.getString(2));
				user.setUserRole(res.getString(3));
				user.setUserMailId(res.getString(4));
				user.setUserMobileNumber(res.getString(5));
				user.setUserPassword(res.getString(6));
				user.setCreatedOn(res.getDate(7));
				users.add(user);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return users;
	}

	public User getUserByName(String userName) {
		List<User> users = getAllUsers();
		int res = 0;
		User user = new User();
		try {
			PreparedStatement statement = connection.prepareStatement(GET_USER_BY_NAME);
			statement.setString(1, user.getUserName());
			statement.execute();
			for (User u : users) {
				if (u.getUserName().equals(userName)) {

					user.setUserId(u.getUserId());
					user.setUserName(u.getUserName());
					user.setUserRole(u.getUserRole());
					user.setUserMobileNumber(u.getUserMobileNumber());
					user.setUserMailId(u.getUserMailId());
					user.setUserPassword(u.getUserPassword());
					user.setCreatedOn(u.getCreatedOn());
					res = 1;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (res == 1)
			return user;
		else
			return null;

	}

	public Book getBookById(int bookId) {
		List<Book> books = getAllBooks();
		int res = 0;
		Book book = new Book();
		try {
			PreparedStatement statement = connection.prepareStatement(GET_BOOK_BY_ID);
			statement.setInt(1, book.getBookId());
			statement.execute();
			for (Book b : books) {
				if (b.getBookId() == bookId) {

					book.setBookId(b.getBookId());
					book.setBookName(b.getBookName());
					book.setAuthorName(b.getAuthorName());
					book.setGenre(b.getGenre());
					book.setVolume(b.getVolume());
					book.setEdition(b.getEdition());
					res = 1;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (res == 1)
			return book;
		else
			return null;

	}

	public Book getBookByBookName(String bookName) {
		List<Book> books = getAllBooks();
		int res = 0;
		Book book = new Book();
		try {
			PreparedStatement statement = connection.prepareStatement(GET_BOOK_BY_BOOKNAME);
			statement.setString(1, book.getBookName());
			statement.execute();
			for (Book b : books) {
				if (b.getBookName().equals(bookName)) {

					book.setBookId(b.getBookId());
					book.setBookName(b.getBookName());
					book.setAuthorName(b.getAuthorName());
					book.setGenre(b.getGenre());
					book.setVolume(b.getVolume());
					book.setEdition(b.getEdition());
					res = 1;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (res == 1)
			return book;
		else
			return null;

	}

	public List<Book> getBookByAuthorName(String authorName) {
		List<Book> books = getAllBooks();
		List<Book> bookList = new ArrayList<Book>();
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(GET_BOOK_BY_AUTHORNAME);
			statement.setString(1, authorName);
			statement.execute();
			for (Book b : books) {
				if (b.getAuthorName().equals(authorName)) {

					Book book = new Book();
					book.setBookId(b.getBookId());
					book.setBookName(b.getBookName());
					book.setAuthorName(b.getAuthorName());
					book.setGenre(b.getGenre());
					book.setVolume(b.getVolume());
					book.setEdition(b.getEdition());
					bookList.add(book);
					res = 1;

				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (res == 1)
			return bookList;
		else
			return null;
	}

	public List<Book> getBookByGenre(String bookGenre) {
		List<Book> books = getAllBooks();
		List<Book> bookList = new ArrayList<Book>();
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(GET_BOOK_BY_BOOKGENRE);
			statement.setString(1, bookGenre);
			statement.execute();
			for (Book b : books) {
				if (b.getGenre().equals(bookGenre)) {

					Book book = new Book();
					book.setBookId(b.getBookId());
					book.setBookName(b.getBookName());
					book.setAuthorName(b.getAuthorName());
					book.setGenre(b.getGenre());
					book.setVolume(b.getVolume());
					book.setEdition(b.getEdition());
					bookList.add(book);
					res = 1;

				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (res == 1)
			return bookList;
		else
			return null;

	}

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(GET_ALL_BOOKS);
			while (res.next()) {
				Book book = new Book();
				book.setBookId(res.getInt(1));
				book.setBookName(res.getString(2));
				book.setAuthorName(res.getString(3));
				book.setGenre(res.getString(4));
				book.setVolume(res.getInt(5));
				book.setEdition(res.getInt(6));
				books.add(book);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return books;
	}

	public boolean addBook(Book book) {
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(ADD_BOOK_QUERY);
			statement.setInt(1, book.getBookId());
			statement.setString(2, book.getBookName());
			statement.setString(3, book.getAuthorName());
			statement.setString(4, book.getGenre());
			statement.setInt(5, book.getVolume());
			statement.setInt(6, book.getEdition());

			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean val= res==0 ? false :true;
		return val;
	}

	public boolean updateBook(Book book) {
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK_QUERY);

			statement.setString(1, book.getBookName());
			statement.setString(2, book.getAuthorName());
			statement.setString(3, book.getGenre());
			statement.setInt(4, book.getVolume());
			statement.setInt(5, book.getEdition());
			statement.setInt(6, book.getBookId());
			res = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean val= res==0 ? false :true;
		return val;
	}

	public boolean deleteBook(int bookId) throws NegativeBookId {
		if (bookId < 0)
			throw new NegativeBookId("you cannot enter negative Book Id");
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_QUERY);
			statement.setInt(1, bookId);
			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean val= res==0 ? false :true;
		return val;

	}

	public List<Admin> getAllAdmins() {
		List<Admin> admins = new ArrayList<Admin>();

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery("select * from hr.admintable");
			while (res.next()) {
				Admin admin = new Admin();
				admin.setAdminId(res.getInt(1));
				admin.setAdminName(res.getString(2));
				admin.setAdminRole(res.getString(3));
				admin.setAdminPassword(res.getString(4));
				admins.add(admin);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return admins;
	}

	
	
	
	public boolean issueBook() {

		Scanner s = new Scanner(System.in);
		logger.info("Enter your Issue Id");
		int issueId = s.nextInt();
		logger.info("Enter your User Id");
		int userId = s.nextInt();
		logger.info("Enter the Book Id borrowed by user:");
		int bookId = s.nextInt();

		logger.info("your book is issued successfully");
		logger.info("*********************************************************************");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		LocalDate issue = LocalDate.now();
		LocalDate dueDate = issue.plusDays(15);

		String issueDate = issue.toString();
		String returnDate = dueDate.toString();
		boolean res = false;
		logger.info("your issue date is: " + formatter.format(date));
		logger.info("your due date is: " + returnDate);
		logger.info("*********************************************************************");
		String query = "insert into hr.issuebookdetails values(?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, issueId);
			preparedStatement.setInt(2, bookId);

			preparedStatement.setInt(3, userId);
			preparedStatement.setString(4, issueDate);
			preparedStatement.setString(5, returnDate);
			res = preparedStatement.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		boolean val= res==false ? false :true;
		return val;

	}
	
	public boolean bookDetails(int userId) {

		User user = getUserById(userId);
		int flag = 0;
		logger.info(user == null ? "User not exist for given Id" : "User Exist for given Id");
		String query = "select u.userId,u.userName,u.userMailId,u.userMobileNumber,i.issuedate,i.returndate,b.bookId,b.bookName,b.authorName,b.genre,b.volume,b.edition from  hr.issuebook i inner join hr.users u\r\n"
				+ "on i.userId=u.userId\r\n"
				+ "inner join hr.books b\r\n"
				+ "on i.bookId=b.bookId";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				logger.info("User Id: " + resultSet.getInt(1));
				logger.info("User name: " + resultSet.getString(2));
				logger.info("User email:" + resultSet.getString(3));
				logger.info("User mobileNumber:" + resultSet.getString(4));
				
				logger.info("Issue Date:" + resultSet.getString(5));
				logger.info("Return Date:" + resultSet.getString(6));
				logger.info("Book Id: " + resultSet.getInt(7));
				logger.info("Book name: " + resultSet.getString(8));
				logger.info("Author name: " + resultSet.getString(9));
				logger.info("Genre Name" + resultSet.getString(10));
				logger.info("Volume : " + resultSet.getInt(11));
				logger.info("Edition : " + resultSet.getInt(12));
				flag = 1;
			}
			logger.info((flag == 0 && user != null) ? "user not issued any book" : "");

		} catch (SQLException e) {

			e.printStackTrace();
		}
		boolean val= flag==0 ? false :true;
		return val;

	}

	@Override
	public boolean isUserExists(int userId) {
		List<User> users = getAllUsers();

		User user = new User();
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID);
			statement.setInt(1, user.getUserId());
			statement.execute();
			for (User u : users) {
				if (u.getUserId() == userId) {

					res = 1;
					break;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		boolean val= res==0 ? false :true;
		return val;
	}

	@Override
	public boolean isBookExists(int bookId) {
		List<Book> books = getAllBooks();
		int res = 0;
		Book book = new Book();
		try {
			PreparedStatement statement = connection.prepareStatement(GET_BOOK_BY_ID);
			statement.setInt(1, book.getBookId());
			statement.execute();
			for (Book b : books) {
				if (b.getBookId() == bookId) {

					res = 1;
					break;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		boolean val= res==0 ? false :true;
		return val;

	}

}
