package com.charlesproject0.views;

import java.util.ArrayList;

import com.charlesproject0.models.Account;
import com.charlesproject0.models.BankAccount;
import com.charlesproject0.utils.InputUtil;
import com.charlesproject0.utils.ModelsUtil;

public class UserAccountView implements View {
	Account usrAcc;
	@Override
	public void showMenu() {
		System.out.println("Welcome " + usrAcc.getAccountName() + "!"
				+ "\n Please select an account for transactions or create/delete a single/joint account with the options below.");
		
		if(usrAcc.getAccountName().contentEquals("Cloud")) {
		System.out.println(
"           HHHHH;HHHH;;;;H;H; \\     H                                 \r\n" + 
"            HHHHH;HHHH;H;H;H;  ;    HHHHe,,           \r\n" + 
"             HHHHHHHHHHHHHHHH ; H;  HHH;;H; ;H,,,\r\n" + 
"           ,,HHHHHHHHHHHHHHHH\\;; H;  HHH; H; ;HH H HHH\r\n" + 
"   ,dHHHHHHHHHHHHHHH;HHHHH;H;H;;;,H; HHH;; ;; ; ; H HH\\\r\n" + 
"H;;;;;;;;;;;;;;     ;;;;;H;H;HHHH;;HH HHH;; ;; ;,HHHHHH,\r\n" + 
"HHHHHHHHHHHHHHHHHHH;;;;;;HH*??  HH;;H HH;;;;*??         H\r\n" + 
"HHHHHHHHHHHHH;;;H;;H;;;;;;;      H;HHHHH  H;;;;          H\r\n" + 
"HHHHHHHHHHHH;;H?;;H;;;;;;;       H;HHHH;;  H;;;;       ;  H\r\n" + 
"   ?**-HHHHHHHHH;HH;;;;;;  ;      H;HH;H;  H;;;;;   ;   ; H\r\n" + 
"        ?*HHHH;;H;;;;;;;  ;     H H;H;HH;;  H;;;H;  ;    ; H\r\n" + 
"        ,;HHHHHHH;HHH;;  ;   ; HHH H;HHH;;   H;;;H;  ; ;  HH\r\n" + 
"       ,HHHHHHHHHHHHHH H;   ; HHHH ;HHHH;;   HH;;H;H; ; H  H\\\r\n" + 
"     ,HHHHHHHH;;HHHHH H;  HH; H        H;; ; HHHH;H;H; ;\\H  H\r\n" + 
"    ,HHHHHHHHH;HHHHH H;; H;;H         HH;; ; HH;;HHH;H;H;;H  H\r\n" + 
"   ,;HHHHHHH;HHHHHH H;; H;;;H         HH;; ; HHHHH;;H;H;H;;H H\r\n" + 
"  ,;HHHHHHHH;HHHHHHH;; HH;;H          HH;; ; H;;;,    HH;H;;H H\r\n" + 
" ,;HHHHHHHHHHHHHHHH;;H ;H;H           HH;; ; H;;;;;H;,    HHHHH\r\n" + 
"HHHHHHHHHHHHHHHHHHH;H;;H;H            HH;; ; HH;;;;;;;;H;;,,  HH,.\r\n" + 
"HHHHHHHHHHHHH;HHHH;H;;;H;H            HH;;;H H;; ??HHHHHHHHHHHb,  ?\r\n" + 
"HHHHH*?/HHHH;;HHHH;H  ;HH             HHH;;H;HHHHH,     ?HHHHH;;;;\r\n" + 
"HHH?  /HHHHH;HHHHHH   ;H  ,DHHHHHHH?? HH;;;H;HHHHHHHHHH,,      ?HHH\r\n" + 
"??    HHHHHHHHHHHHHH  H  ;;HHHHHHH-  HHH;;;;HHHHH,,;;;HHHHHHHHH,, \r\n" + 
"     HHHHHHHHHHHH H;H H  ;;;HH;;;;HHHHHHH;;;HHHHHHHHH;;HHHHHHHHHHH\r\n" + 
"     HHHHH HHH;HH H;H H   ;;;;;H;HH;HHHH;;;HHHHHHHH;;;H;HHHHHHHHHH\r\n" + 
"    HHHHH  HHHH;H???HH       HHH;;;;HHHH;;;HHHHHHHHH;;H;H;HHHHHHHH\r\n" + 
"    HHH;H  HHHH;H  H H            ??;HHH;;;H;HH;HHH;;;H;H;;HHHH   ?\r\n" + 
"   HHH;H   HHHHH  H  H             ;HH H;;;H;HH;HHH;;;H;HH;;HHHH\r\n" + 
"  HHH;H   HHHHHH H  ;              ;HH H;;;H;HH;HHH;;H; HHHH;;;;H\r\n" + 
" HHH;H   HH HHHHHH   ,             ;H  H;;;H;HHH HHHH? HHHH??H;;H\r\n" + 
" HHHH    HH HHHHH H;;;,           ; H  H;;H HHH H H  HHHHH    H;;H\r\n" + 
"HHHH     H   HHHHH;;;;,          ;  H  H;;H H;H H,,,HHHHH      H;;H\r\n" + 
"HHH     HH   HHHHH;;?              H   H;;H HH H;;;;HHHH         HH\r\n" + 
"HH?     H    HHHHH  HHH,           H   H;H  H H;;;;;HHHH\r\n" + 
"HH      H    HHHHHH H   ?              H;H H;H;;;;;;;HHHH\r\n" + 
"H      H     HHHHHHH HH                HH  HH;;;;   ;HHHH;H,\r\n" + 
"H      H     HHHHHHH                  H;H  H;;;;    HHHHH;HH\r\n" + 
"H      H     HHHHHH;H                ;H;HHH;;;;  HHHHHH; HHH\r\n" + 
"       H     H;HHHH;;H           ,,HHHHH;;;;;;HHHHH;;;H;; H\r\n" + 
"             H;HHHH;;H  ;    ,,HHHHHHHHH;;HHH?H;;;H;;;H;  H\r\n" + 
"             H;HHHH;;;HHHHHHHHHHHHHHHHHHH;H;;;;H;;;H;;H   HH\r\n" + 
"             H;HHHH;;;;HHHH;HHHHHH;H;;H    H ;;H;;;H;;H   HH\r\n" + 
"             H;HHH;;;;;;HH;H;HHH ;H;;;H    H ;;H  ;H  H   HH\r\n" + 
"             H; HHH;;;;;;HHH;;;H;;H;;;H    H ;;H  H   H   H H\r\n" + 
"             HH  HH;;;H;;HHH;;;H;;H;;;H    H;;;H  H   H   H HH,,\r\n" + 
"             H   H;;;;H;H  HH;;H;;H;;;H;;;;H;;;H  H   H   H;HHHHH\r\n" + 
"            H    H;;;;H;H   HHHH;;H;;;H;;;;H;;;H  H   H  ;HHHH??\r\n" + 
"            H    H;;;;;H    HHHH;;H;;;H;;;;H;;;H  H   H;HH??\r\n" + 
"                 H;;;;H     HH;HHHH;;;H;;;;H;;;H  H;HH??\r\n" + 
"                 H;;;;H     HH;H;;H;;;H;;; H;;;H;HH?\r\n" + 
"                  H;;;H     HH;H;;H;;;H;;; H;;HH?\r\n" + 
"                  H;;H      HHHH;;H;;;;H;  HH?\r\n" + 
"                  H;;H         HH;;H;;;H HH?\r\n" + 
"                  H;H           HH H   HH\r\n" + 
"                  H;H             H\r\n" + 
"                  H;H\r\n" + 
"                  HH\r\n" + 
"                  H\n\n\n");

		}
		System.out.println("1: Create Bank Account(single/joint)");
		System.out.println("2: View Bank Accounts");
		System.out.println("3: Logout");
	}

	@Override
	public View selectOption() {
		
			int selection = InputUtil.getIntInRange(1, 3);
			// User selects something - should be reusable
			// Do something with their selection, custom to this class
			switch(selection) {

				case 1: return new CreateBankAccountView(this.usrAcc);
				case 2: 
					ArrayList<BankAccount> bAccs = ModelsUtil.returnBankAccounts(this.usrAcc);
					if (bAccs.isEmpty()) {
						System.out.println("\n\nYou must create a bank account before being able to view one, "
								+ "\nselect 'create bank account' option to create one\n\n");
						return this;
					}
					else {
						return new ViewBankAccounts(this.getUsrAcc());	
					}

				case 3: return new MainMenu() ;
				default: return new MainMenu();
			}
	}
		
		
		

	public void addBankAccount(String ... jointUsers) {
			for (String user:jointUsers) {
				System.out.println(user);
			}
	}
	
	//Constructors
	public UserAccountView(Account usrAcc) {
		this.usrAcc = usrAcc;
	}
	
	public UserAccountView() {

	}
	

	//non-overriden methods
	public Account getUsrAcc() {
		return usrAcc;
	}

	public void setUsrAcc(Account usrAcc) {
		this.usrAcc = usrAcc;
	}
	

}
