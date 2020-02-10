package com.charlesproject0.models;



public class BankAccount {
	private int bankAccountId;
	private String bankAccountName;
	private String accountType;
	private double gilBalance;

	
	
	public BankAccount(int bankAccountId, String bankAccountName, String accountType, double gilBalance) {
		super();
		
		this.bankAccountId = bankAccountId;
		this.bankAccountName = bankAccountName;
		this.gilBalance = gilBalance;
		this.accountType = accountType;
	}
	
	public int getBankAccountId() {
		return bankAccountId;
	}
	public void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
	}
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



	

}
