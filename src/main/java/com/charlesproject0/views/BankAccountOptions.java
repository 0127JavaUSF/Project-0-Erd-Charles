package com.charlesproject0.views;

import com.charlesproject0.models.Account;
import com.charlesproject0.utils.InputUtil;

public class BankAccountOptions implements View {
	Account usrAcc;
	@Override
	public void showMenu() {
		System.out.println("Select 1 for single account, 2 for joint account, or 3 for deletion: \n");
		System.out.println("1: Create Single-owned Account");
		System.out.println("2: Create Joint-Account ");
		System.out.println("3: Transfer gil ");
		System.out.println("3: Deposit gil ");
		System.out.println("3: Withdraw gil ");
	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(1, 3);
		// User selects something - should be reusable
		// Do something with their selection, custom to this class
		switch(selection) {

			case 1: return new CreateAccountView();
			case 2: return new LoginView();
			case 3: return null;
			default: return null;
		}
	}
	
	
	
	public BankAccountOptions(Account usrAcc){
		this.usrAcc = usrAcc;
	}
	
	public void addBankAccount(String ... jointUsers) {
		for (String user:jointUsers) {
			System.out.println(user);
		}
		
	}
//TODO
//	private boolean checkIfUserExists(String userName) {
//		
//		
//	}
//	
	

}
