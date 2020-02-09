package com.charlesproject0.views;

import java.util.ArrayList;

import com.charlesproject0.models.Account;
import com.charlesproject0.models.BankAccount;

public class ViewBankAccounts implements View {
	private ArrayList<BankAccount> bankAccounts;
	public ViewBankAccounts(Account usrAcc) {
		// TODO Auto-generated constructor stub
	}
	public ViewBankAccounts() {
		
	}

	@Override
	public void showMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public View selectOption() {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<BankAccount> getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

}
