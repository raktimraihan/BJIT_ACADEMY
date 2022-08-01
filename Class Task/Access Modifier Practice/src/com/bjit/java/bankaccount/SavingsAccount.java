package com.bjit.java.bankaccount;

public class SavingsAccount extends AccountHolder{
	
	private String accountNumber;
	private String Branch;
	private double amount;
	
	public SavingsAccount(String name, int age, String nid, String accountNumber, String branch, double amount) {
		super(name, age, nid);
		this.accountNumber = accountNumber;
		Branch = branch;
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return super.toStringPrint()+"SavingsAccount [accountNumber="+accountNumber+", Branch="+Branch+", amount="+amount+"]";
	}
	
}
