package com.charlesproject0.launch;

import com.charlesproject0.views.MainMenu;
import com.charlesproject0.views.View;

public class Launcher {
	public static void main(String[] args) {
		View view = new MainMenu();
		
		while(view != null) {
			view.showMenu();
			view = view.selectOption();
		}
		
		System.out.println("Bye!");
	}
}
