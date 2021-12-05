package ATM;

import java.util.*;

public class ATM {
	public static void main(String []args) {
		Scanner sc = new Scanner(System.in);
		Bank theBank = new Bank("PB bank");
		System.out.println("Enter your first name :-");
		String a = sc.next();
		System.out.println("Enter your last name :-");
		String b = sc.next();
		System.out.println("Enter the PIN u want to keep :-");
		String c = sc.next();
		//add a user,also creates a savings account
		User aUser = theBank.addUser(a, b, c);
		//also add a checking account for our user
//		System.out.println("What type of account you want to open \n"+"-Savings\n -Current");
//		String d = sc.next();
//		Account newAccount  = new Account(d,aUser ,theBank);
//		aUser.addAccount(newAccount);
//		theBank.addAccount(newAccount);
		
		User curUser;
		while(true) {
			//stay in login prompt until successful login
			curUser = ATM.mainMenuPrompt(theBank , sc);
			//stay in main menu until user quits
			ATM.printUserMenu(curUser , sc);
		}
	}

	private static void printUserMenu(User theUser, Scanner sc) {
		//print a summary of the user's account
		theUser.printAccountSummary();
		int choice;
		System.out.printf("Welcome" +" " +" %s "+" ! What would you like to do? \n",theUser.getFiretName());
		System.out.printf("1) Show account transaction history\n");
		System.out.printf("2)withdraw\n");
		System.out.printf("3)deposit\n");
		System.out.printf("4)transfer\n");
	    System.out.printf("5)Quit\n");
	    System.out.println();
	    System.out.printf("Enter choice :\n");
	    choice = sc.nextInt();
	    if(choice<1 || choice>5) {
	    	System.out.println("Invalid choice .Please enter a number between 1 to 5\n ");
	    }while(choice<1 || choice>5);
	    //process the choice
	    switch(choice) {
	    case 1:
	    	ATM.showTransHistory(theUser , sc);
	    	break;
	    
	    case 2 :
	    	ATM.withdrawFunds(theUser , sc);
	    	
	    case 3 :
	    	ATM.depositfunds(theUser , sc);
	    	
	    case 4 :
	    	ATM.transferFunds(theUser , sc);
	   
	    case 5:
//	    	sc.nextLine();
	    	break;
	    }
	   //redisplay this menu until the user wants to quit
	    if(choice!=5) {
	    	ATM.printUserMenu(theUser , sc);
	    }
	     
	    
	    		
	}

	public static void depositfunds(User theUser, Scanner sc) {
       int toAcct;
		
		double amount;
		double acctBal ;
		String memo;
		//get the account to transfer from 
		do {
		System.out.printf("In which account (%d)) you want\n"+"to deposit in",theUser.numAccounts()-1);
		int p = sc.nextInt();
		toAcct = p - 1;
		if(toAcct<0||toAcct>=theUser.numAccounts()) {
			System.out.println("Invalid account ,Please try again ");	
		}
		}while(toAcct<0||toAcct>=theUser.numAccounts());
		acctBal = theUser.getAcctBalance(toAcct);	
		do {
			System.out.printf("Enter the amount to deposit:$", acctBal);
			amount = sc.nextDouble();
			if(amount<0) {
				System.out.println("Amount must be greater than zero");
			}
			
		}while(amount <0 );
		System.out.println("Enter a memory for this deposit :-");
		
		//get the memo
		memo = sc.next();
		//do the deposit
		theUser.addAcctTransaction(toAcct, amount, memo);
		
	}

	private static void withdrawFunds(User theUser, Scanner sc) {
		int fromAcct;
		
		double amount;
		double acctBal ;
		String memo;
		//get the account to transfer from 
		do {
		System.out.printf("Enter the number(1-%d) of the account \n"+"to withdraw from",theUser.numAccounts());
		int t = sc.nextInt();
		fromAcct = t - 1;
		if(fromAcct<0||fromAcct>=theUser.numAccounts()) {
			System.out.println("Invalid account ,Please try again ");	
		}
		}while(fromAcct<0||fromAcct>=theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);	
		do {
			System.out.printf("Enter the amount to withdraw(max $%.02f):$", acctBal);
			amount = sc.nextDouble();
			if(amount<0) {
				System.out.println("Amount must be greater than zero");
			}
			else if(amount >acctBal){
				System.out.printf("Amount must not be greater than "+"balance of $%.02f",acctBal);
			}
;		}while(amount < 0 ||amount>acctBal);
		
         System.out.println("Enter a memory for this deposit :-");
		
		//get the memo
		memo = sc.next();
		//do the withdrawal
		theUser.addAcctTransaction(fromAcct, -1*amount, memo);
		
	}

	public static void transferFunds(User theUser, Scanner sc) {
		//inits
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal ;
		//get the account to transfer from 
		do {
		System.out.printf("Enter the number(1- %d) of the account \n"+"to transfer from",theUser.numAccounts());
		int q = sc.nextInt();
		fromAcct = q-1;
		if(fromAcct<0||fromAcct>=theUser.numAccounts()) {
			System.out.println("Invalid account ,Please try again ");	
		}
		}while(fromAcct<0||fromAcct>=theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);
		//get the account to transfer to
		do {
			System.out.printf("Enter the number(1-%d) of the account \n"+"to transfer from",theUser.numAccounts());
			int s = sc.nextInt();
			toAcct = s-1;
			if(toAcct<0||toAcct>=theUser.numAccounts()) {
				System.out.println("Invalid account ,Please try again ");	
			}
			}while(toAcct<0||toAcct>=theUser.numAccounts());
		//get the amount to transfer
		do {
			System.out.printf("Enter the amount to tranfer(max $%.02f):$", acctBal);
			amount = sc.nextDouble();
			if(amount<0) {
				System.out.println("Amount must be greater than zero");
			}
			else if(amount >acctBal){
				System.out.printf("Amount must not be greater than "+"balance of $%.02f",acctBal);
			}
;		}while(amount <0 ||amount>acctBal);
		//do the transfer
		theUser.addAcctTransaction(fromAcct , -1*amount, String.format("Transfer to account %s",theUser.getAcctUUID(toAcct)));
		theUser.addAcctTransaction(toAcct , amount, String.format("Transfer to account %s",theUser.getAcctUUID(fromAcct)));
	}

	public static void showTransHistory(User theUser, Scanner sc) {
		int theAcct;
		do {
		System.out.printf("Enter the number(1-%d) of the account "+"whose transactions you want to see", theUser.numAccounts());
		int r = sc.nextInt() ;
		theAcct = r -1;
		if(theAcct<0||theAcct>theUser.numAccounts()) {
			System.out.println("Invalid account ,Please try again ");
		}
		}while(theAcct<0||theAcct>theUser.numAccounts());
		//print the transaction history
		theUser.printAcctTransHistory(theAcct);
		
	}

	private static User mainMenuPrompt(Bank theBank, Scanner sc) {
		String userId;
		String pin;
		User  authUser;
		//user need to fill the userId and pin until correct combination is entered
		do {
		System.out.printf("\n \nWelcome to %s \n"+"\n//User id has been provided to you above  ",theBank.getname());
		System.out.print("\n\nEnter user ID:");
		userId = sc.next();
		System.out.print("Enter PIN :");
		pin = sc.next();
		//try to get user object corresponding to ID and pin combo
		authUser = theBank.userLogin(userId , pin);
		if(authUser ==null) {
			System.out.println("Incorrect user id/pin" + "Please try again");
		}
		}while(authUser ==null);//continue looping until successful login
		return authUser;
	}
}
