package com.revature.library.dao;

import java.util.List;


import com.revature.library.model.Book;
import com.revature.library.model.User;



public interface LibraryDAO {
	public boolean addUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(int userId);
	public User getUserById(int userId);
	public User getUserByName(String UserName);  
	public List<User> getAllUsers();
	
	public boolean addBook(Book book);
	public boolean updateBook(Book book);
	public boolean deleteBook(int bookId);
	public Book getBookById(int bookId);
	public Book getBookByBookName(String bookName);  
	public List<Book> getBookByAuthorName(String authorName); 
	public List<Book>getBookByGenre(String genre); 
	public List<Book> getAllBooks();

}

