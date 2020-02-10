package com.charlesproject0.views;


import com.charlesproject0.models.Account;
import com.charlesproject0.utils.InputUtil;

public class CreateAccountView implements View {//TODO finish account creation stuff


	
	@Override
	public void showMenu() {
		System.out.println("Let's begin creating an account with a series of questions regarding the type of account");
		System.out.println(". Back");
	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(1, 3);
//		switch (selection) {
//		case 1: return this;
//		case 2: createAccount(); return this;
//		default:
//		case 3: return new MainMenu();
//		}
		return null;
	}


	
	private void createAccount() {
		System.out.println("Please enter account name ");
		String accountName = InputUtil.getNextString();
		
		System.out.println("Please enter password ");
		String password = InputUtil.getNextString();
		
		Account account = new Account(0, accountName, password);

		System.out.println(account);
		
	}
	
}
