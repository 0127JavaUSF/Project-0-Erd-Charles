package com.charlesproject0.views;

import java.util.ArrayList;

import com.charlesproject0.models.Account;
import com.charlesproject0.models.BankAccount;
import com.charlesproject0.utils.InputUtil;
import com.charlesproject0.utils.VerifyingAccountsTableUtil;

public class ViewBankAccounts implements View {
	private ArrayList<BankAccount> bankAccounts;
	Account usrAcc;
	
	public ViewBankAccounts(Account usrAcc) {
		this.usrAcc = usrAcc;
	}


	public Account getUsrAcc() {
		return usrAcc;
	}
	public void setUsrAcc(Account usrAcc) {
		this.usrAcc = usrAcc;
	}
	@Override
	public void showMenu() {
		System.out.println("Type in the name of an existing account listed below to begin: \n");


	}

	@Override
	public View selectOption() {
		try{
			this.setBankAccounts(VerifyingAccountsTableUtil.returnBankAccounts(this.getUsrAcc()));
			for (BankAccount bAcc: this.getBankAccounts()) {
				System.out.println(bAcc.getBankAccountName());
			}
			
		}
		catch(NullPointerException e) {
			e.printStackTrace();//either the user has no bank accounts, or they failed to load
		}
		String choice;
		boolean found = false;
		if (!(this.getBankAccounts().isEmpty())) {
			do {
				choice = InputUtil.getNextString();
				for(BankAccount bAcc: this.getBankAccounts()) {
					if (choice.equals(bAcc.getBankAccountName())){
						found = true;
						break;
					};
				}
			}
			while (!(found));//while user doesn't select one of the listed accounts
		}
		
		
		return null;//TODO return BankAccountOptions
	}
	public ArrayList<BankAccount> getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

}
