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
import com.charlesproject0.utils.VerifyingAccountsTableUtil;

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
		boolean transferToThis;
		System.out.println("Select from the following options:");
		System.out.println("1.Transfer to this bank account from another bank account");
		System.out.println("2.Transfer from this bank account to another bank account");
		
		
		if (InputUtil.getIntInRange(1, 2) == 1) {
			transferToThis = true;
			
		}
		else {
			transferToThis = false;
		}
		

		double transferAmount = InputUtil.getNextDouble();//obtain input for transfer amount

		BankAccount bankAccSelectedDest = VerifyingAccountsTableUtil.verifyBankInList(bankAccounts);//allows user to select account from list

		try(Connection connection = ConnectionUtil.getConnection()){

			connection.setAutoCommit(false);//starts transaction
			//Account1 update
			String sql = "select gil_balance from bank_accounts where bank_account_name = (?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, this.currBankAcc.getBankAccountName());		//refresh the bank instance in case changes were made(real world applicable)
			double oldFromAccBalance = 0;
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				oldFromAccBalance = rs.getDouble("gil_balance");
			}
			String sql2 = "Update bank_accounts set(gil_balance=(?) where bank_account.id = (?))";
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			double newFromAccBalance;
			if (!(transferToThis)) {
			newFromAccBalance = oldFromAccBalance - transferAmount;//subtract from this account
			}
			else {
			newFromAccBalance = oldFromAccBalance
			}
			ps2.setDouble(1, newFromAccBalance);
			ps2.setInt(2, this.currBankAcc.getBankAccountId());
			ps2.executeUpdate(sql2);
			
			
			
			//Account2 update
			String sql3 = "select gil_balance from bank_accounts where bank_account_name";
			PreparedStatement ps3 = connection.prepareStatement(sql3);
			ps3.setString(1, bankAccSelectedDest.getBankAccountName());		//refresh the bank instance in case changes were made(real world applicable)
			double oldToAccBalance = 0;
			ResultSet rs2 = ps3.executeQuery(sql3);
			while(rs2.next()) {
				oldToAccBalance = rs.getDouble("gil_balance");
			}
			
			double newToAccBalance = oldToAccBalance + transferAmount;//subtract from this account
			
			String sql4 = "Update bank_accounts set(gil_balance=(?) where bank_account.id = (?))";
			PreparedStatement ps4 = connection.prepareStatement(sql4);
			ps4.setDouble(1, newToAccBalance);
			ps4.setInt(2, bankAccSelectedDest.getBankAccountId());
			ps4.executeUpdate(sql4);
			
			

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
	private void transferFrom() {
		System.out.println("Type the name of the account you wish to transfer funds from from the list below:\n");
		//refresh the bank instance in case changes were made to balance since accessed through UserAccount view selection
		System.out.println("Successfully altered balance");
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
	
	
	
	private double alterBalance(BankAccount bankAccSelected, Double BalanceObtained){
		double newBalance;
		newBalance = bankAccSelected.getGilBalance(); 
		
		
		System.out.println("Successfully altered balance");
		return newBalance;
		

	}
	

	
//	private String viewBalance(BankAccount bAcc) {
//		return bAcc.getGilBalance();
//		
//		
//	}

}
