package com.charlesproject0.views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.charlesproject0.models.Account;
import com.charlesproject0.models.BankAccount;
import com.charlesproject0.utils.ConnectionUtil;
import com.charlesproject0.utils.InputUtil;
import com.charlesproject0.utils.ModelsUtil;

public class BankAccountTransactionView implements View {//provides options for selecting bank account and performing bank transfer/deposit/withdrawals
	private ArrayList<BankAccount> bankAccounts;
	private BankAccount currBankAcc;
	private Account usrAcc;
	
	
	
	BankAccountTransactionView(ArrayList<BankAccount> bankAccounts, BankAccount currBankAcc, Account usrAcc){//transferred over all relevant data to performing bank transfer/dep/withdrawal
		this.bankAccounts = bankAccounts;
		this.currBankAcc = currBankAcc;
		this.usrAcc = usrAcc;
		
	}
	@Override
	public void showMenu() {
		System.out.println("Choose from the transaction options below for account: " + this.currBankAcc.getBankAccountName());
		System.out.println("                ______________\r\n" + 
				"    __,.,---'''''              '''''---..._\r\n" + 
				" ,-'             .....:::''::.:            '`-.\r\n" + 
				"'           ...:::.....       '\r\n" + 
				"            ''':::'''''       .               ,\r\n" + 
				"|'-.._           ''''':::..::':          __,,-\r\n" + 
				" '-.._''`---.....______________.....---''__,,-\r\n" + 
				"      ''`---.....______________.....---''");
		System.out.println("1: Withdrawal");
		System.out.println("2: Deposit");
		System.out.println("3: Transfer to/from this account");
		System.out.println("4: Return to viewing bank accounts");
	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(1, 5);
//		 User selects something - should be reusable
//		 Do something with their selection, custom to this class
		switch(selection) {

			case 1: withdrawal();
			return this;
			case 2: deposit();
			return this;
			case 3: transfer();
			return this;
			default: return new ViewBankAccounts(this.usrAcc);
		}

	}
	private void transfer() {
		System.out.println("Select from the following options:");
		System.out.println("1.Transfer to this bank account from another bank account");
		System.out.println("2.Transfer from this bank account to another bank account\n");
		
		boolean transferToThis;
		if (InputUtil.getIntInRange(1, 2) == 1) {
			transferToThis = true;
			
		}
		else {
			transferToThis = false;
		}
		

		double transferAmount = InputUtil.getNextDouble();//obtain input for transfer amount
		BankAccount otherBankAcc = ModelsUtil.verifyBankInList(bankAccounts);//allows user to select account from list
		
		try(Connection connection = ConnectionUtil.getConnection()){

			connection.setAutoCommit(false);//starts transaction
			//Account1 update
			double currBankAccBalance = this.currBankAcc.getGilBalance();
			String sql2 = "Update bank_accounts SET gil_balance = ?  where id = ? and gil_balance = ? RETURNING *";
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			double newCurrAccBalance = 0;
			if (!(transferToThis)) {
			newCurrAccBalance = currBankAccBalance - transferAmount;//subtract from this account
			}
			else {
			newCurrAccBalance = currBankAccBalance + transferAmount;
			}
			ps2.setDouble(1, newCurrAccBalance);
			ps2.setInt(2, this.currBankAcc.getBankAccountId());
			ps2.setDouble(3, currBankAccBalance);
			ResultSet rs2 = ps2.executeQuery();
			
			

			double oldOtherBankAccBalance = otherBankAcc.getGilBalance();
			String sql4 = "Update bank_accounts SET gil_balance = ?  where id = ? and gil_balance = ? RETURNING *";
			PreparedStatement ps4 = connection.prepareStatement(sql4);
			double newOtherBankAccBalance = 0;
			if(!(transferToThis)) {
				newOtherBankAccBalance = oldOtherBankAccBalance + transferAmount;
			}
			else {
				newOtherBankAccBalance = oldOtherBankAccBalance - transferAmount;
				
			}
			ps4.setDouble(1, newOtherBankAccBalance);
			ps4.setInt(2, otherBankAcc.getBankAccountId());
			ps4.setDouble(3, oldOtherBankAccBalance);
			ResultSet rs4 = ps4.executeQuery();
			
			
			System.out.println("Success bitch");
			connection.commit();//end transaction
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Transaction declined");
		}
		catch(Exception e) {//remove me once done debugging
			e.printStackTrace();
		}
		
	}
	
	private void deposit() {
		//refresh the bank instance in case changes were made to balance since accessed through UserAccount view selection
		System.out.println("Successfully altered balance");
	}
	private void withdrawal() {
		System.out.println("Choose a quantity to withdrawal, you may loan as much as you want, not exceeding -999999999.99 gil(pretty great huh)");
		//refresh the bank instance in case changes were made to balance since accessed through UserAccount view selection
		System.out.println("Successfully altered balance");
	}
	

	
//	private String viewBalance(BankAccount bAcc) {
//		return bAcc.getGilBalance();
//		
//		
//	}

}
