package com.charlesproject0.views;

import java.util.ArrayList;

import com.charlesproject0.utils.InputUtil;
import com.charlesproject0.utils.JointOrSingleEnum;

import com.charlesproject0.models.BankAccount;

public class CreateBankAccountView implements View {
	private ArrayList<BankAccount> bankAccounts;
	@Override
	public void showMenu() {
		System.out.println("\nHere we'll create either a single or joint account:\n");


	}

	@Override
	public View selectOption() {

		System.out.println("\nFor single owned account enter 1; for a joint account enter 2:\n");
		JointOrSingleEnum usrChoice = InputUtil.getIntInRange(1, 2) == 1 ? JointOrSingleEnum.SINGLE : JointOrSingleEnum.JOINT;
		if (usrChoice.equals(JointOrSingleEnum.SINGLE)) {
			//TODO add sql logic for creation of single account
			System.out.println("To be added");
			
		}
//		else if(usrChoice.equals(JointOrSingleEnum.JOINT)) {
//				Strings[] usrAccs = InputUtil.
//				
//				
//		}
		else {
			System.out.println();
		}

		
		return null;
	}
	
	public CreateBankAccountView() {
		
	}

}
