package com.revature.library.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import com.revature.library.model.User;
import com.revature.library.service.LibraryService;
import com.revature.library.service.LibraryServiceImpl;



public class LibraryServiceImplTest {
	LibraryService libraryService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		libraryService=new LibraryServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		libraryService=null;
	}
	

	@Test
	public void testAddUser() {
		int userIdToTest = -999;
		List<User> originalUsers1 = libraryService.getAllUsers();
		libraryService.addUser(new User(userIdToTest,"demoName","student","demoName@com",111111111,"123","2020-02-20"));
		List<User>  originalUsers2 = libraryService.getAllUsers();

		assertEquals( originalUsers2.size(),  originalUsers1.size() + 1);

		libraryService.deleteUser(userIdToTest);
		
	}

	
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		int userIdToTest = -999;
		List<User> originalUsers1 = libraryService.getAllUsers();
		libraryService.addUser(new User(userIdToTest,"demoName","student","demoName@com",111111111,"123","2020-02-20"));
		List<User>  originalUsers2 = libraryService.getAllUsers();

		assertEquals( originalUsers2.size(),  originalUsers1.size() + 1);

		libraryService.deleteUser(userIdToTest);
	}

	@Test
	public void testGetUserById() {
		int UserIdToTest = -999;
		User addedUser = new User(UserIdToTest,"demoName","student","demoName@com",111111111,"123","2020-02-20");
		libraryService.addUser(addedUser);
		libraryService.getUserById(-999);
		libraryService.deleteUser(UserIdToTest);
	}

	@Test
	public void testGetAllUsers() {
		int userIdToTest = -999;
		List<User> originalUsers1 = libraryService.getAllUsers();
		libraryService.addUser(new User(userIdToTest,"demoName","student","demoName@com",111111111,"123","2020-02-20"));
		List<User>  originalUsers2 = libraryService.getAllUsers();

		assertEquals( originalUsers2.size(),  originalUsers1.size() + 1);

		libraryService.deleteUser(userIdToTest);
	}

	@Test
	public void testGetUserByName() {
		int UserIdToTest = -999;
		String userNametoTest="demo";
		User addedUser = new User(UserIdToTest, userNametoTest,"student","demoName@com",111111111,"123","2020-02-20");
		libraryService.addUser(addedUser);
		libraryService.getUserByName( userNametoTest);
		libraryService.deleteUser(UserIdToTest);
	}

	/*@Test
	public void testGetBookById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBookByBookName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBookByAuthorName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBookByGenre() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBook() {
		fail("Not yet implemented");
	}*/

}
