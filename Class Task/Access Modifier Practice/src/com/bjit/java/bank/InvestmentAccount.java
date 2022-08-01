package com.bjit.java.bank;

import com.bjit.java.bankaccount.AccountHolder;

public class InvestmentAccount extends AccountHolder{
	
	private Double amount;
	private int durationInYear;
	public InvestmentAccount(Double amount, int durationInYear,String Name, int age, String nid) {
		super();
		setAge(age);
		setNid(nid);
		setName(Name);
		this.amount = amount;
		this.durationInYear = durationInYear;
	}
	@Override
	public String toString() {
		return "InvestmentAccount [amount=" + amount + ", durationInYear=" + durationInYear + ", Name=" + getName()
				+ ",Age=" + getAge() + ",Nid =" + getNid()+ "+]";
	}
	
	
	
	

}
