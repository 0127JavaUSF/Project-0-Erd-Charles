package com.revaturecharlesproject0.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import com.charlesproject0.models.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.charlesproject0.models.Account;
import com.charlesproject0.utils.ModelsUtil;
import com.charlesproject0.views.LoginView;

public class DepositAndWithdrawalTests {
	private String[] unamePwd = new String[2];
	private Account usrAcc;
	private ArrayList<BankAccount> bankAcc;
	private double originalBalance;
	private double requestedAmount;
	BankAccount theAccount;
	BankAccount updatedAcc;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		unamePwd[0] = "Cloud";
		unamePwd[1] = "Cloud123";
//		usrAcc = LoginView.loginAuth(unamePwd);//must change loginview's method to public and static to run test 2
		ArrayList<BankAccount> bankAccounts= ModelsUtil.returnBankAccounts(usrAcc);
		theAccount = ModelsUtil.verifyBankInList(bankAccounts);
		originalBalance = theAccount.getGilBalance();
		requestedAmount = 2250;
//		updatedAcc = ModelsUtil.usrWithdrawOrDeposit(true, requestedAmount, theAccount);//applicable to bank withdrawal test
		updatedAcc = ModelsUtil.usrWithdrawOrDeposit(false, requestedAmount, theAccount);//applicable to bank deposit test
		
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testBankWithdrawal() {//must set method to public static
//		
//		
//		assertEquals("New balance should be old balance minus requested ", originalBalance - requestedAmount,  updatedAcc.getGilBalance(), 0.00);
//	
//		
//	}
	@Test
	public void testBankDeposit() {//must set method to public static due to not following best practices
		
		
		assertEquals("New balance should be old balance minus requested ", originalBalance + requestedAmount,  updatedAcc.getGilBalance(), 0.00);
	
		
	}

}
