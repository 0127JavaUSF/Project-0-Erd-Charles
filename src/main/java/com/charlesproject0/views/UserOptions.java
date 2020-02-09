package com.charlesproject0.views;

import java.util.ArrayList;

import com.charlesproject0.models.Account;
import com.charlesproject0.models.BankAccount;
import com.charlesproject0.utils.InputUtil;
import com.charlesproject0.utils.JointOrSingleEnum;

public class UserOptions implements View {
	private Account usrAcc;
	



	@Override
	public void showMenu() {
		System.out.println("Select from the options below: \n");
		System.out.println("1: Create bank account");
		System.out.println("2: View bank account(s) ");
		System.out.println("3: Logout");

	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(1, 4);
		// User selects something - should be reusable
		// Do something with their selection, custom to this class
		switch(selection) {

			case 1: return new CreateBankAccountView();
			case 2: return new ViewBankAccounts(this.getUsrAcc());
			case 3: return null ;
			default: return null;
		}
	}
	
	
	
	public UserOptions(Account usrAcc){
		this.setUsrAcc(usrAcc);
	}
	
	public void addBankAccount(String ... jointUsers) {
		for (String user:jointUsers) {
			System.out.println(user);
		}
		
	}

	public Account getUsrAcc() {
		return usrAcc;
	}

	public void setUsrAcc(Account usrAcc) {
		this.usrAcc = usrAcc;
	}
	


//TODO
//	private boolean checkIfUserExists(String userName) {
//		
//		
//	}
//	
	

}
