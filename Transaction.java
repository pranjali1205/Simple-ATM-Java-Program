package ATM;
import java.util.Date;

public class Transaction {
 private double amount;//amont of this transaction
 private Date TimeStamp;//transaction's time
 private Account inAccount;//account in which transaction is performed
 private String memo;//just a memo of this account 
  
 public Transaction(double amount,Account inAccount) {
	this.amount = amount;
	this.inAccount = inAccount;
	this.TimeStamp = new Date();
	this.memo = "";
 }
 public Transaction(double amount,String memeo, Account inAccount) {
	 //calling the 2 argument constructor
	 this(amount ,inAccount);
	 this.memo = memo;
 }
public double getAmount() {
	
	return this.amount;
}
public String getSummaryLine() {
	if(this.amount>=0) {
		return String.format("%s,$%.02f :%s",this.TimeStamp.toString(),this.amount,this.memo);
	}
	else {
		return String.format("%s,$(%.02f) :%s",this.TimeStamp.toString(), this.amount,this.memo);
	}
}
}
