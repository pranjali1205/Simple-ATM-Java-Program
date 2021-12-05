package ATM;
import java.util.ArrayList;

public class Account {
 private String name;//account name
  
 private String uuid; //account id
 private User holder;//user who holds this account
 private ArrayList<Transaction> transactions ;//list of transactions of this account

 public Account(String name , User holder, Bank theBank){
	this.name = name;
	this.holder = holder;
	this.uuid = theBank.getNewAccountUID();
	this.transactions = new ArrayList<Transaction>();
	holder.addAccount(this);
	theBank.addAccount(this);
 }

public String getUUID() {
	// TODO Auto-generated method stub
	return this.uuid;
}

public String getSummaryLine() {
	//get the account's balance
	double balance = this.getBalance();
	if(balance>=0) {
		return String.format("%s :$%.02f:%s",this.uuid,balance ,this.name);
	}
	else {
		return String.format("%s :$(%.02f) :%s",this.uuid,balance ,this.name);
	}
}

public double getBalance() {
	double balance = 0;
	for(Transaction t:this.transactions) {
		balance +=t.getAmount();
	}
	return balance;
}

public void printTransHistory() {
	System.out.printf("\n transaction history for account %s\n", this.uuid);
	for(int t= this.transactions.size()-1;t>0;t--) {
		System.out.println(this.transactions.get(t).getSummaryLine());
	}
	
}

public void addTransaction(double amount, String memo) {
	// create new transaction object and add it to our list
	Transaction newTrans = new Transaction(amount , memo,this);
	this.transactions.add(newTrans);
	
}
}
