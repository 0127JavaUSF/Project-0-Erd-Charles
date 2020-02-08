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

    private View loginView;
    private Account cloudAcc;
    private View accView;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		loginView = new LoginView();
		cloudAcc = new Account(1, "cloud", "cloud123");
		accView = new UserAccountView();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUserAccountView() {
		assertEquals("Should return accountView if provided cloud, cloud123 un/pwd combo(placeholder for auth); otherwise, fail", accView.getClass() , loginView.selectOption().getClass());

	}
	
//	@Test
//	public void testLoginUserAuth() {
//		assertEquals("Should return the id of 1 associated to the value of the user returned from db", cloudAcc.getId() , loginView.selectOption());
//
//	}

}
