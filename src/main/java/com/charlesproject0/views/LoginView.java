package com.charlesproject0.views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.charlesproject0.models.Account;
import com.charlesproject0.utils.ConnectionUtil;
import com.charlesproject0.utils.InputUtil;

public class LoginView implements View {

	@Override
	public void showMenu() {
		System.out.println("Please enter your username, followed by your password");
		System.out.println("                  /¯¯\\\r\n" + 
				"                  \\__/\r\n" + 
				"                   ||\r\n" + 
				"                   ||\r\n" + 
				"                  |  |\r\n" + 
				"                  |  |\r\n" + 
				"                  |  |\r\n" + 
				"                  |  |\r\n" + 
				"                  |  |\r\n" + 
				"                  |  |\r\n" + 
				"              .--.----.--.\r\n" + 
				"            .-----\\__/-----.\r\n" + 
				"    ___---¯¯////¯¯|\\/|¯¯\\\\\\\\¯¯---___\r\n" + 
				" /¯¯ __O_--////   |  |   \\\\\\\\--_O__ ¯¯\\\r\n" + 
				"| O?¯      ¯¯¯    |  |    ¯¯¯      ¯?O | \r\n" + 
				"|  '    _.-.      |  |      .-._    '  |\r\n" + 
				"|O|    ?..?      ./  \\.      ?..?    |O|\r\n" + 
				"| |     '?. .-.  | /\\ |  .-. .?'     | |\r\n" + 
				"| ---__  ¯?__?  /|\\¯¯/|\\  ?__?¯  __--- |\r\n" + 
				"|O     \\         ||\\/ |         /     O|\r\n" + 
				"|       \\  /¯?_  ||   |  _?¯\\  /       |\r\n" + 
				"|       / /    - ||   | -    \\ \\       |\r\n" + 
				"|O   __/  | __   ||   |   __ |  \\__   O|\r\n" + 
				"| ---     |/  -_/||   |\\_-  \\|     --- | \r\n" + 
				"|O|            \\ ||   | /            |O|  \r\n" + 
				"\\ '              ||   |        ^~DLF ' /\r\n" + 
				" \\O\\    _-¯?.    ||   |    .?¯-_    /O/\r\n" + 
				"  \\ \\  /  /¯¯¯?  ||   |  ?¯¯¯\\  \\  / /\r\n" + 
				"   \\O\\/   |      ||   |      |   \\/O/\r\n" + 
				"    \\     |      ||   |      |     /\r\n" + 
				"     '.O  |_     ||   |     _|  O.'\r\n" + 
				"        '._O'.__/||   |\\__.'O_.'\r\n" + 
				"           '._ O ||   | O _.'\r\n" + 
				"              '._||   |_.'\r\n" + 
				"                 ||   |\r\n" + 
				"                 ||   |\r\n" + 
				"                 | \\/ |\r\n" + 
				"                 |  | |\r\n" + 
				"                  \\ |/\r\n" + 
				"                   \\/");

	}

	

	@Override
	public View selectOption() {//if the method returns null, exit the app(lock them out); otherwise, return AccountView page
		String[] unamePwd = new String[2];
		System.out.println("\n Username:  ");
		unamePwd[0] = InputUtil.getNextString();
		System.out.println("\n Password:  ");
		unamePwd[1] = InputUtil.getNextString();
		
		Account usrAcc = loginAuth(unamePwd);
		
		
		try{
			if (!(usrAcc == null)) {
				return new UserAccountView(usrAcc);
			}
			
		}
		catch(NullPointerException e) {

				e.printStackTrace();
			
		}

	
		System.out.println("Incorrect login info, redirecting to main menu");
		return new MainMenu();
	}
	
	//switch to public during testing
	public static Account loginAuth(String[] uname_pwd) {//TODO switch back to private non-static after test
		
		Account usrAcc = null;
		ResultSet rs = null;
		
		try(Connection connection = ConnectionUtil.getConnection()){

			String sql = "select * from accounts where account_name = ? and password = ?";
			PreparedStatement ps = connection.prepareStatement(sql);

			
			ps.setString(1, uname_pwd[0]);
			ps.setString(2, uname_pwd[1]);

			
			rs = ps.executeQuery();
			
			//Determinant on result set being returned
			if (!(rs == null)) {//found it
				
				while(rs.next()) {//ensures first row only, which should happen regardless if constraints on db are enforced
					usrAcc = new Account(rs.getInt("id"), rs.getString("account_name"), rs.getString("password"));
				}
			
			}

//			connection.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();

		}
		return usrAcc;
		



	}
	
	
	

}
