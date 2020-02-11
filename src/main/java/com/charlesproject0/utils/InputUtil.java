package com.charlesproject0.utils;

import java.util.Scanner;

/**
 * Utility class used for getting user input.
 *
 */
public class InputUtil {
	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Prompts user to input number in range min to max inclusive of both.
	 */
	public static int getIntInRange(int min, int max) {
		int selection = 0;
		outer: do {
			System.out.println("Please enter a number between " + min +
				" and " + max);
			
			while(!scanner.hasNextInt()) {
				scanner.nextLine();
				continue outer;
			}
			selection = scanner.nextInt();
			scanner.nextLine();
		} while(selection < min || selection > max);
		
		return selection;
	}
	
	public static String getNextString() {
		return scanner.nextLine();
	}
	
	public static double getNextDouble() {
		double quantity = 0;

			System.out.println("Enter an amount to transfer");
			
			while(!scanner.hasNextDouble()) {
				scanner.nextLine();

			}
			quantity = scanner.nextInt();
			scanner.nextLine();

		
		return quantity;
		
	}
}
