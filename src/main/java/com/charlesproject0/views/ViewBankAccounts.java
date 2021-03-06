package com.charlesproject0.views;

import java.util.ArrayList;

import com.charlesproject0.models.Account;
import com.charlesproject0.models.BankAccount;
import com.charlesproject0.utils.InputUtil;
import com.charlesproject0.utils.ModelsUtil;

public class ViewBankAccounts implements View {
	private ArrayList<BankAccount> bankAccounts;
	private Account usrAcc;
	
	public ViewBankAccounts(Account usrAcc) {
		this.usrAcc = usrAcc;
		this.bankAccounts = ModelsUtil.returnBankAccounts(this.usrAcc);
	}


	public Account getUsrAcc() {
		return usrAcc;
	}
	public void setUsrAcc(Account usrAcc) {
		this.usrAcc = usrAcc;
	}
	@Override
	public void showMenu() {
	


	}

	@Override
	public View selectOption() {
		try{
			this.setBankAccounts(ModelsUtil.returnBankAccounts(this.getUsrAcc()));
//			ModelsUtil.printBankAccounts(this.bankAccounts);
			
		}
		catch(NullPointerException e) {
			e.printStackTrace();//either the user has no bank accounts, or they failed to load
		}
		BankAccount foundAcc = null;
		if (!(this.getBankAccounts().isEmpty())) {
			foundAcc = ModelsUtil.verifyBankInList(this.bankAccounts);
			return new BankAccountTransactionView(this.bankAccounts, foundAcc, this.usrAcc);//TODO return BankAccountOptions
		}
		else {
			System.out.println("\nThere were no accounts found, returning you to User account view");
			return new UserAccountView(usrAcc);
		}
		
		
		
	}
	public ArrayList<BankAccount> getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}


}
