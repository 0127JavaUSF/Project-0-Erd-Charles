package com.revaturecharlesproject0.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.charlesproject0.models.Account;
import com.charlesproject0.views.LoginView;
import com.charlesproject0.views.MainMenu;
import com.charlesproject0.views.UserAccountView;
import com.charlesproject0.views.View;

public class LoginAndTransferTests {

    private LoginView loginView;
    private Account cloudAcc;

	private String[] loginCredench = new String[2];

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		loginCredench[0] = "Cloud";
		loginCredench[1] = "Cloud123";
		loginView = new LoginView();
		cloudAcc = new Account(1, "cloud", "cloud123");

	}

	@After
	public void tearDown() throws Exception {
	}
//
//	@Test
//	public void testUserAccountView() {
//		assertEquals("Should return accountView if provided cloud, cloud123 un/pwd combo(placeholder for auth); otherwise, fail", accView.getClass() , loginView.selectOption().getClass());
//
//	}
//	
//	@Test
//	public void testLoginUserAuth() {
//		assertEquals("Should return the id of 1 associated to the value of the user returned from db", cloudAcc.getId() , loginView.loginAuth(loginCredench).getId());
//
//	}

}
