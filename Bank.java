package ATM;
import java.util.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;


public class Bank {
    private String name;
    private ArrayList<User>users;
    private ArrayList<Account>accounts;
	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}

	Scanner sc = new Scanner(System.in);
	public String getNewUserUUID() {
		String uuid;
		Random rng = new Random();
		int len = 6;
		boolean nonUnique ;
		//continue looping util we get a unique uid
		do {
			//generate the number
			uuid = "";
			for(int c = 0;c<len;c++) {
				uuid+= ((Integer)rng.nextInt(10)).toString();
			}
			//making sure uuid is unique for all users
			nonUnique = false;
			for(User u:this.users) {
				if(uuid.compareTo(u.getUUID())==0){
					nonUnique = true;
					break;
				}
			}
		}while(nonUnique);
		return uuid;
		
		
	}

	public String getNewAccountUID() {
		String uuid;
		Random rng = new Random();
		int len = 10;
		boolean nonUnique ;
		//continue looping util we get a unique uid
		do {
			//generate the number
			uuid = "";
			for(int c = 0;c<len;c++) {
				uuid+= ((Integer)rng.nextInt(10)).toString();
			}
			//making sure uid is unique for all users
			nonUnique = false;
			for(Account a:this.accounts) {
				if(uuid.compareTo(a.getUUID())==0){
					nonUnique = true;
					break;
				}
			}
		}while(nonUnique);
		return uuid;
	}
//	                                                                                                 

	public User addUser(String FirstName , String LastName ,String pin ) {
		User newUser = new User(FirstName,LastName,pin,this);
		this.users.add(newUser);
		 
		//creating new account for user
		System.out.println("Enter the type of account you want to create \n"+"1)Savings \n2)Current");
//		int a = sc.nextInt();
//		do {
//			if(a==1) {
//			 b = "Savings";	
//			}
//			else if(a==2) {
//			 b = "Current";
//			}
//			else {
//				System.out.println("Please enter 1 or 2 /n");
//			}
//		}while(a!=1 ||a!=2);
		
		String b = sc.next();
		Account newAccount = new Account( b,newUser , this); 
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);
		return newUser;
	}
	public User userLogin(String userID,String pin) {
		//Searching through list of users
		for(User u:this.users) {
			try {
				if(u.getUUID().equals(userID) && u.validatePin(pin)) {
					return u;
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;//if user/pin doen't match
	}

void addAccount(Account newAccount) {
	this.accounts.add(newAccount);
	
}

 public String getname() {
  return this.name;
  }
}
