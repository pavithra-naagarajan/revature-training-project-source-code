package com.revature.library.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.library.model.Book;
import com.revature.library.model.User;
import com.revature.library.util.DBConnection;


public class LibraryDAOImplementation implements LibraryDAO {

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

	public boolean addUser(User user) {
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(ADD_USER_QUERY);
			statement.setInt(1, user.getUserId());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getUserRole());
			statement.setString(4, user.getUserMailId());
			statement.setInt(5, user.getUserMobileNumber());
			statement.setString(6, user.getUserPassword());
			statement.setString(7, user.getDateCreatedOn());
			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (res == 0)
			return false;
		else
			return true;
	}

	public boolean updateUser(User user) {
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);

			statement.setString(1, user.getUserName());
			statement.setString(2, user.getUserRole());
			statement.setString(3, user.getUserMailId());
			statement.setInt(4, user.getUserMobileNumber());
			statement.setString(5, user.getUserPassword());
			statement.setString(6, user.getDateCreatedOn());
			statement.setInt(7, user.getUserId());
			res = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (res == 0)
			return false;
		else
			return true;
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
		if (res == 0)
			return false;
		else
			return true;

	}

	public User getUserById(int userId) {

		List<User> users = getAllUsers();

		User user = new User();
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
					user.setUserPassword(u.getDateCreatedOn());
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return user;
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
				user.setUserMobileNumber(res.getInt(5));
				user.setUserPassword(res.getString(6));
				user.setDateCreatedOn(res.getString(7));
				users.add(user);
				

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return users;
	}

	public User getUserByName(String userName) {
		List<User> users = getAllUsers();

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
					user.setUserPassword(u.getDateCreatedOn());
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return user;
	}

	public Book getBookById(int bookId) {
		List<Book> books = getAllBooks();

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
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return book;
	}

	public Book getBookByBookName(String bookName) {
		List<Book> books = getAllBooks();

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
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return book;
	}

	public List<Book> getBookByAuthorName(String authorName) {
		List<Book> books = getAllBooks();
		List<Book> bookList = new ArrayList<Book>();
		
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
					
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return bookList;
	}

	public List<Book> getBookByGenre(String bookGenre) {
		List<Book> books = getAllBooks();
		List<Book> bookList = new ArrayList<Book>();
		
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
					
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return bookList;
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
		if (res == 0)
			return false;
		else
			return true;
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
		if (res == 0)
			return false;
		else
			return true;
	}

	public boolean deleteBook(int bookId) {
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_QUERY);
			statement.setInt(1, bookId);
			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (res == 0)
			return false;
		else
			return true;

	}

}
