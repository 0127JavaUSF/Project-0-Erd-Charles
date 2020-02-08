package com.revaturecharlesproject0.test;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.charlesproject0.models.Account;
import com.charlesproject0.views.CreateAccountView;
import com.charlesproject0.views.LoginView;
import com.charlesproject0.views.MainMenu;
import com.charlesproject0.views.View;


public class MainMenuTests {
  
	//we declare one reference here to be reused in every test
    private View view;
    private View loginView;
    private Account cloudAcc;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		view = new MainMenu();
		view.showMenu();
		

		

		
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	
	
	//scanner input tests are subject to implied input based on the text displayed within the assertion methods
//	@Test
//	public void testMenuOptionInput0() {
//			assertEquals("Should return null, thus exiting program with scanner input 3)", null, view.selectOption());
//		
//	}
//	@Test
//	public void testMenuOptionInputDefault() {
//		assertEquals("Should return null, thus exiting program if user doesn't supply a valid option(scanner input 0)", null, view.selectOption());
//	
//	}
//	@Test
//	public void testMenuOptionCreateAccount() {
//		assertEquals("Should return an instance of create account menu,(scanner input 1)", new CreateAccountView().getClass(), view.selectOption().getClass());
//	
//	}
	
//	@Test
//	public void testMenuOptionLoginPage() {
//		assertEquals("Should return an instance of login page ,(scanner input 2)", new LoginView().getClass(), view.selectOption().getClass());
//	
//	}
	



}
