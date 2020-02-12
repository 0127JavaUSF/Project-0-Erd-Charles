package com.charlesproject0.views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.charlesproject0.utils.ConnectionUtil;
import com.charlesproject0.utils.InputUtil;
import com.charlesproject0.utils.JointOrSingleEnum;
import com.charlesproject0.utils.ModelsUtil;
import com.charlesproject0.models.Account;
import com.charlesproject0.models.BankAccount;

public class CreateBankAccountView implements View {
	private ArrayList<BankAccount> bankAccounts;
	private ArrayList<Account> allUsrAccounts;
	private Account usrAcc;
	@Override
	public void showMenu() {
		System.out.println("\nHere we'll create either a bank account after a series of options have been selected for the bank account specifications:\n");


	}

	@Override
	public View selectOption() {
		
		ArrayList<String> accsReq = new ArrayList<String>;
		accsReq.add(this.usrAcc.getAccountName());//need to add at least this user regardless if join/single
		
		System.out.println("Please enter bank account name ");
		String bankAccName = InputUtil.getNextString();
		
		System.out.println("Enter 1 for checking, 2 for savings");
		
		int checkOrSavings = InputUtil.getIntInRange(1, 2);
		String checkOrSavingsParameter = null;
		
		if(checkOrSavings == 1) {
			checkOrSavingsParameter = "CHECKING";
			
		}
		else {
			checkOrSavingsParameter = "SAVINGS";
			
		}
		boolean isJointAcc = false;
		System.out.println("\nFor single owned account enter 1; for a joint account enter 2:\n");
		int usrChoiceJoint = InputUtil.getIntInRange(1, 2);
		
		if (usrChoiceJoint == 2) {
			System.out.println("Enter the name of another user to add them to the bank account");
			String potentialAdditionalUser = ModelsUtil.verifyAccInList(InputUtil.getNextString());
			if (potentialAdditionalUser == null) {
				System.out.println("There is no user account with that name, returning to User account view");
				return new UserAccountView(this.usrAcc);//escapes here if other account doesn't exist
			}
			accsReq.add(potentialAdditionalUser);
		}
		ModelsUtil.createBankAccount(bankAccName, checkOrSavingsParameter, isJointAcc, accsReq);//sends bank account name, checking or savings, isJoint account, and accounts required for now...

		System.out.println("Bank account created");
		return new UserAccountView(this.usrAcc);
	}
	
	public CreateBankAccountView() {
		
	}

	public CreateBankAccountView(Account usrAcc) {
		this.usrAcc = usrAcc;
	}

}
