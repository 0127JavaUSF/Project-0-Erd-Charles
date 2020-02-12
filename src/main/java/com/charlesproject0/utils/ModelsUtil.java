package com.charlesproject0.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.charlesproject0.models.Account;
import com.charlesproject0.models.BankAccount;
import com.charlesproject0.utils.ConnectionUtil;
import com.charlesproject0.views.BankAccountTransactionView;
import com.charlesproject0.views.MainMenu;

public class ModelsUtil {//All in one dao with some other utilities, not best practice I know guys...
	public static ArrayList<String> returnVisibleAccounts(String ... strings ) {//verifies that the account exists in db
		ArrayList<String> results = new ArrayList<String>();
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "Select account_name from accounts";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<String> dbrsList = new ArrayList<String>();
			while (rs.next()) {
				dbrsList.add(rs.getString("account_name"));
				
				
			}
			for (String entry:strings){
				if (dbrsList.contains(entry)) {
					results.add(entry);
				}
				
				
			}
			System.out.println("placeholder");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	public static ArrayList<BankAccount> returnBankAccounts(Account usrAcc){//returns the bank accounts of a user
		
		ArrayList<BankAccount> dbrsList = new ArrayList<BankAccount>();
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_accounts \r\n" + 
					"					JOIN accounts_join ON accounts_join.bank_accounts_id = bank_accounts.id \r\n" + 
					"					JOIN accounts ON accounts_join.accounts_id = accounts.id \r\n" + 
					"					 WHERE account_name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, usrAcc.getAccountName());
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {//grabs all bank-accounts associated to the user account
				String[] tempArr = new String[2];
				double tempDoub;
				int tempInt;
				tempInt = rs.getInt("id");
				tempArr[0] = rs.getString("bank_account_name");
				tempArr[1] = rs.getString("account_type");
				tempDoub = rs.getDouble("gil_balance");
				dbrsList.add(new BankAccount(tempInt, tempArr[0], tempArr[1], tempDoub));
				
			}


			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return dbrsList;
		
	}
	public static BankAccount verifyBankInList(ArrayList<BankAccount> bankAccounts) {//returns the account input by the user
		String choice;
		boolean found = false;//not sure i even need this
		BankAccount foundAcc = null;
		System.out.println("Select a bank account from the list:\n");
		ModelsUtil.printBankAccounts(bankAccounts);
			do {
				choice = InputUtil.getNextString();
				for(BankAccount bAcc: bankAccounts) {
					if (choice.equals(bAcc.getBankAccountName())){
						found = true;
						foundAcc = new BankAccount(bAcc.getBankAccountId(), bAcc.getBankAccountName(), bAcc.getAccountType(), bAcc.getGilBalance());//return account from the user's bank accounts
						return foundAcc;
					};
				}
			}
			while (!(found));//while user doesn't select one of the listed accounts
		return foundAcc;

	}

	public static void printBankAccounts(ArrayList<BankAccount> bankAccounts) {
		for (BankAccount bAcc: bankAccounts) {
			System.out.println(bAcc.getBankAccountName());
		}
		
	}
	
	public static BankAccount usrWithdrawOrDeposit(boolean withdrawal, double gilAmount, BankAccount bankAcc) {//allows a logged in user to make deposit/withdrawal
			BankAccount updatedAcc = null;
			try(Connection connection = ConnectionUtil.getConnection()){

				//Account1 update
				double currBankAccBalance = bankAcc.getGilBalance();
				String sql2 = "Update bank_accounts SET gil_balance = ?  where id = ? and gil_balance = ? RETURNING *";
				PreparedStatement ps2 = connection.prepareStatement(sql2);
				double newCurrAccBalance = 0;
				if (withdrawal) {
				newCurrAccBalance = currBankAccBalance - gilAmount;//subtract from this account
				}
				else {
				newCurrAccBalance = currBankAccBalance + gilAmount;
				}
				ps2.setDouble(1, newCurrAccBalance);
				ps2.setInt(2, bankAcc.getBankAccountId());
				ps2.setDouble(3, currBankAccBalance);
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()) {
					updatedAcc = new BankAccount(rs2.getInt("id"), rs2.getString("bank_account_name"), rs2.getString("account_type"), rs2.getDouble("gil_balance"));
				}
				
			
			}
			catch(SQLException e) {
				System.out.println("Something went wrong with the withdrawal/deposit");
				e.printStackTrace();
			}
			String autoStatement = withdrawal ? "withdrawal" : "deposit";
			System.out.println("Amount was successfully issued for " + autoStatement);
			return updatedAcc;
	}
	
	public static void createBankAccount(String bAccName, String checkOrSave, boolean jointAcc, ArrayList<String> usrAccs) {
		try(Connection connection = ConnectionUtil.getConnection()){
			connection.setAutoCommit(false);
			String sql = "INSERT INTO bank_accounts" + 
					" (bank_account_name, account_type, gil_balance) " + 
					" VALUES(?, ?, 0.0) RETURNING * ";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, bAccName);
			ps.setString(2, checkOrSave);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BankAccount tempBAcc = new BankAccount(rs.getInt("id"), rs.getString("bank_account_name"), rs.getString("account_type"), rs.getDouble("gil_balance"));
			}
			
			
	
			
			
			String sql2 = "INSERT INTO accounts_join " + 
					" (accounts_id, bank_accounts_id) " + 
					" VALUES(?, ?); ";
			PreparedStatement ps2 = connection.prepareStatement(sql);
			
			ps2.setInt(1, bAcc);
			ps2.setInt(2, checkOrSave);
			
			ResultSet rs2 = ps.executeQuery();
			
			
			
			
			
			
			if (jointAcc) {
				
			}
			else {
				connection.commit();
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("There may be an issue with your database connection, please try again or contact admin");
		}
	}
	
	public static String verifyAccInList(String usrInputString) {

		String foundName = null;
		ArrayList<String> usrAccsAsStrings= ModelsUtil.returnAllAccountStrings();

				for(String astring: usrAccsAsStrings) {
					if (usrInputString.equals(astring)){

						foundName = usrInputString;
						return foundName;
					};
				}
			System.out.println("\n\nUser Account not found...\n\n");
			return foundName;
	}

	private static ArrayList<String> returnAllAccountStrings() {
		ArrayList<String> usrAccsStrings = new ArrayList<String>();
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT account_name FROM accounts";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {//grabs all bank-accounts associated to the user account
				usrAccsStrings.add(rs.getString("account_name"));
				
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return usrAccsStrings;
	}
}
