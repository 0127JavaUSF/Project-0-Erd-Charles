package com.charlesproject0.views;


import com.charlesproject0.daos.AccountDao;
import com.charlesproject0.models.Account;
import com.charlesproject0.utils.InputUtil;

public class CreateAccountView implements View {

	AccountDao accountDao = new AccountDao();
	
	@Override
	public void showMenu() {
		System.out.println("1. Load Account");
		System.out.println("2. Create Account");
		System.out.println("0. Back");
	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(0, 2);
		switch (selection) {
		case 1: loadAccount(); return this;
		case 2: createAccount(); return this;
		default:
		case 0: return new MainMenu();
		}
	}

	private void loadAccount() {
		System.out.println("Enter Account ID: ");
		int id = InputUtil.getIntInRange(1, Integer.MAX_VALUE);
		
		Account account = accountDao.getAccount(id);
				
		System.out.println(account);
	}
	
	private void createAccount() {
		System.out.println("Please enter account name ");
		String accountName = InputUtil.getNextString();
		
		System.out.println("Please enter password ");
		String password = InputUtil.getNextString();
		
		Account account = new Account(0, accountName, password);
		account = accountDao.createAccount(account);
		System.out.println(account);
		
	}
	
}
