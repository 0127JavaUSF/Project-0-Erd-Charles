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
		System.out.println("1: Withdrawal/Deposit");
		System.out.println("2: Transfer");
		System.out.println("3: Delete this bank account(DANGEROUS)");
		System.out.println("4: View Balance");
		System.out.println("5: Return to User account view");
	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(1, 5);
//		 User selects something - should be reusable
//		 Do something with their selection, custom to this class
		switch(selection) {

			case 1: initWithDrawalOrDeposit();
			return this;
			case 2: transfer();
			return this;
			case 3: delete();
			return new UserAccountView(this.usrAcc);//bank account is gone, so we need to return them to viewing bank accounts
			case 4: viewBalance();
			return this;
			default: return new UserAccountView(this.usrAcc);
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
			
			
			System.out.println("Successfully completed transfer");
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
	
	private void initWithDrawalOrDeposit() {
		
		double gilAmount = InputUtil.getNextDouble();//obtain input for gil amount to withdraw/deposit
		System.out.println("Choose from an option below:");
		System.out.println("1:Withdrawal");
		System.out.println("2:Deposit");
		int choice = InputUtil.getIntInRange(1, 2);
		boolean withdrawal = false;
		if(choice == 1) {
			withdrawal = true;
		}

		ModelsUtil.usrWithdrawOrDeposit(withdrawal, gilAmount, this.currBankAcc);
	}
	
	
	private void delete() {
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "UPDATE public.bank_accounts " + 
					" SET isactive = false " + 
					" WHERE id= ? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, this.currBankAcc.getBankAccountId());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully 'deleted' account" + currBankAcc.getBankAccountName());
		System.out.println("Returning to bank account options view...");		
		
		
	}
	
	private void viewBalance() {
		//refresh if changes since last viewing
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "select * from bank_accounts where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, this.currBankAcc.getBankAccountId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				this.currBankAcc.setGilBalance(rs.getDouble("gil_balance"));
			}
			System.out.println("Account Balance for account: " + this.currBankAcc.getGilBalance() + "gil\n");
			
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Balance for account: " + this.currBankAcc.getBankAccountName() + "is " + this.currBankAcc.getGilBalance() + "gil.");
	}


}
