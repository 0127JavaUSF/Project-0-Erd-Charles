package com.charlesproject0.views;

import com.charlesproject0.models.Account;
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
		
		if (!(usrAcc.equals(null))) {
			return new UserAccountView();
		}
	

		return null;
	}
	
	private Account loginAuth(String[] uname_pwd) {
		
		//TODO start db auth and retrieval of data here
		Account usrAcc = null;
		if (uname_pwd[0].equals("cloud") && uname_pwd[1].equals("cloud123") ) {
			usrAcc = new Account(1, uname_pwd[0], uname_pwd[1]);
			return usrAcc;
		}
		else {
			return usrAcc;
		}


	}
	
	
	

}
