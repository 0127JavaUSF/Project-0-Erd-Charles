package com.charlesproject0.views;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.charlesproject0.utils.ConnectionUtil;
import com.charlesproject0.utils.InputUtil;

public class CreateAccountView implements View {//TODO finish account creation stuff


	
	@Override
	public void showMenu() {
		System.out.println("Here you may create a user account or return to the main menu");
		System.out.println("1: Create a new User account");
		System.out.println("2: Return to main menu");
	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(1, 2);
		switch (selection) {
		case 1: createAccount(); return new MainMenu();
		case 2: return new MainMenu();
		default: return new MainMenu();
		
		}
	}


	
	private void createAccount() {//creation of user account
		System.out.println("Please enter account name ");
		String accountName = InputUtil.getNextString();
		
		System.out.println("Please enter password ");
		String password = InputUtil.getNextString();
		
		
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO public.accounts " + 
					" (account_name, password) " + 
					" VALUES(?, ?) RETURNING *";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, accountName);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("There may be an issue with your database connection, please try again or contact admin");
		}


		System.out.println("Account created!");
		
	}
	
}
