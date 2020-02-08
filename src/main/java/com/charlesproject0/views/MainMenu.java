package com.charlesproject0.views;

import com.charlesproject0.utils.InputUtil;

public class MainMenu implements View {

	public void showMenu() {
		System.out.println("Welcome to Final Fantasy's Chocobank!" + "\n" + "Please create or Login from the options below to deposit/withdraw/transfer your gil!");
		System.out.println("                  .\r\n" + 
				"               ___/),.._\r\n" + 
				"             /'   ,.   .\"'._\r\n" + 
				"            (     \"'   '-.__\"-._             ,-\r\n" + 
				"             \\'='='),  3\\ -._-\"-.          -\"/\r\n" + 
				"                   / \"\"/\"\\,_\\,__\"\"       _\" /,-\r\n" + 
				"                  /   /                -\" _/\"/\r\n" + 
				"                 /   |    ._\\\\ |\\  |_.\".-\"  /\r\n" + 
				"                /    |   __\\)|)|),/|_.\" _,.\"\r\n" + 
				"               7     \\_.\"   \" \") | ).-\"\"---''--\r\n" + 
				"              (                  \"/.\"\"7__-\"\"''\r\n" + 
				"              |                   3 .\"._--._\r\n" + 
				"              \\       \\ (_    __   \"\"   \".,_\r\n" + 
				"               \\.,.    \\  \"\"   -\"\".-\"\r\n" + 
				"                \".,_,  (\",_-,,,-\".-\r\n" + 
				"                    \"'-,\\_   __,-\"\r\n" + 
				"                          \",)\" \")\r\n" + 
				"                           /\"\\-\"\r\n" + 
				"                         ,\"\\/\r\n" + 
				"                   _,.__/\"\\/_\r\n" + 
				"             ctr  / \\) \"./,  \".\r\n" + 
				"               --/---\"---\" \"-) )----");
		System.out.println("1: Create Account");
		System.out.println("2: Login");
		System.out.println("3: Quit ");
	}

	public View selectOption() {
		int selection = InputUtil.getIntInRange(1, 3);
		// User selects something - should be reusable
		// Do something with their selection, custom to this class
		switch(selection) {

			case 1: return new CreateAccountView();
			case 2: return new LoginView();
			case 3: return null;
			default: return null;
		}
	}

}
