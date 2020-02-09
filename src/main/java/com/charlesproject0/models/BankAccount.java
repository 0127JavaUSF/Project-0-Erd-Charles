package com.charlesproject0.models;

import com.charlesproject0.utils.CheckingSavingsEnum;

public class BankAccount {

	private String bankAccountName;
	private String accountType;
	private double gilBalance;

	
	
	


	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getGilBalance() {
		return gilBalance;
	}
	public void setGilBalance(double gilBalance) {
		this.gilBalance = gilBalance;
	}

	public BankAccount(String bankAccountName, CheckingSavingsEnum accountType, double gilBalance) {
		super();
		this.bankAccountName = bankAccountName;
		this.gilBalance = gilBalance;
		this.accountType = accountType;
	}
	
	

}
