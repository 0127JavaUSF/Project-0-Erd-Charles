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

public class ModelsUtil {
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
}
