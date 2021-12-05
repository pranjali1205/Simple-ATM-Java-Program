package ATM;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
//import java.util.*;

public class User {
 private String firstName;
 private String lastName;
 private String uuid;//user unique id
 private byte pinHash[];//to store the hash of user's PIN
 private ArrayList<Account> accounts ;

 
 public User(String firstname,String Lastname, String pin , Bank theBank ) {
	//setting user's name 
	 this.firstName = firstname;
	 this.lastName = Lastname;
	 //storing PIN's hash rather than PIN for security reasons
	 MessageDigest md;
	try {
		md = MessageDigest.getInstance("MD5");
		this.pinHash = md.digest(pin.getBytes());
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//generating a uuid
	 this.uuid = theBank.getNewUserUUID();
	 this.accounts = new ArrayList<Account>();
	 //printing message
	 System.out.printf("New user %s %s with id %s is created. \n" ,firstname, Lastname,this.uuid );
 }
 //enables user to add account
 public void addAccount(Account anAccount) {
	 this.accounts.add(anAccount); 
 }
 public String getUUID() {
	// TODO Auto-generated method stub
	return this.uuid;
 }
 public boolean validatePin(String pin) throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("MD5");
	 this.pinHash = md.digest(pin.getBytes());
	 return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash); 
 }
public String getFiretName() {
	// TODO Auto-generated method stub
	return this.firstName;
}
public void printAccountSummary() {
	System.out.printf("\n\n %s's accounts history\n",this.firstName);
	for(int a=0 ;a<this.accounts.size() -1;a++) {
	System.out.printf("(%d)%s \n" ,a+1, this.accounts.get(a).getSummaryLine());	
	}
	
}
public int numAccounts() {
	// TODO Auto-generated method stub
	return this.accounts.size();
}
public void printAcctTransHistory(int acctIdx) {
	this.accounts.get(acctIdx).printTransHistory();
	
}
public double getAcctBalance(int acctIdx) {
	return this.accounts.get(acctIdx).getBalance();
	
}
public String getAcctUUID(int acctIdx) {
	return this.accounts.get(acctIdx).getUUID();
} 

public void addAcctTransaction(int acctIdx , double amount , String memo) {
	this.accounts.get(acctIdx).addTransaction(amount , memo);
}
}
