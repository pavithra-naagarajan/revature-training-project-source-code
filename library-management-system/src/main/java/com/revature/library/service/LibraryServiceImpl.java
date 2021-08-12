package com.revature.library.service;

import java.util.List;


import com.revature.library.dao.LibraryDAO;
import com.revature.library.dao.LibraryDAOImplementation;
import com.revature.library.exceptions.NegativeBookId;
import com.revature.library.model.Admin;
import com.revature.library.model.Book;
import com.revature.library.model.User;

public class LibraryServiceImpl implements LibraryService{
	LibraryDAO libraryDAO=new LibraryDAOImplementation();
	@Override
	public boolean addUser(User user) {
		return libraryDAO.addUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		return libraryDAO.updateUser(user);
	}

	@Override
	public boolean deleteUser(int userId) {
		return libraryDAO.deleteUser(userId);
	}

	@Override
	public User getUserById(int userId) {
		return libraryDAO.getUserById(userId);
	}

	@Override
	public User getUserByName(String UserName) {
		return libraryDAO.getUserByName(UserName);
	}

	@Override
	public List<User> getAllUsers() {
		return libraryDAO.getAllUsers();
	}

	@Override
	public boolean addBook(Book book) {
		return libraryDAO.addBook(book);
	}

	@Override
	public boolean updateBook(Book book) {
		return libraryDAO.updateBook(book);
	}

	@Override
	public boolean deleteBook(int bookId) throws NegativeBookId {
		return libraryDAO.deleteBook(bookId);
	}

	@Override
	public Book getBookById(int bookId) {
		return libraryDAO.getBookById(bookId);
	}

	@Override
	public Book getBookByBookName(String bookName) {
		return libraryDAO.getBookByBookName(bookName);
	}

	@Override
	public List<Book> getBookByAuthorName(String authorName) {
		return libraryDAO.getBookByAuthorName(authorName);
	}

	@Override
	public List<Book> getBookByGenre(String genre) {
		return libraryDAO.getBookByGenre(genre);
	}

	@Override
	public List<Book> getAllBooks() {
		return libraryDAO.getAllBooks();
	}

	@Override
	public List<Admin> getAllAdmins() {
		
		return libraryDAO.getAllAdmins();
	}

}
