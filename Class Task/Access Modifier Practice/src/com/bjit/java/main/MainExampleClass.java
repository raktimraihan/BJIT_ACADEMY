package com.bjit.java.main;

import com.bjit.java.bank.Bank;
import com.bjit.java.bank.InvestmentAccount;
import com.bjit.java.bankaccount.Example;
import com.bjit.java.bankaccount.SavingsAccount;

public class MainExampleClass {
	
	public static void main(String[] args) {
		
		//User can't create AccountHolder obj only as constructor is Default and protected
		//Savings account has all the access to AccountHolder Class, 
		//SavingsAccount class extends AccountHolderClass
		SavingsAccount savingsAccount_01 = new SavingsAccount("Raktim",23, "12345", "45675", "Uttara", 500);
		System.out.println("Savings account Details: "+ savingsAccount_01.toString());
		SavingsAccount savingsAccount_02 = new SavingsAccount("Rabbi",23, "123945", "456975", "Dhanmondi", 5009);
		System.out.println("Savings account Details: "+ savingsAccount_02.toString());
		
		//User can create a InvestmentAccountObj extending AccountHolder
		//Parameterized constructor of AccountHolder will not be accessible from InvestmentAccount class
		//toStringPrint() will also not be available 
		//Create the obj of AccountHolder using no parameterized constructor and get-set methods
		//as they are declared in protected access modifier
		InvestmentAccount investmentAccount_01 = new InvestmentAccount(5400D,1,"Hasan",23,"45667");
		System.out.println("Investment account Details: "+ investmentAccount_01.toString());
		InvestmentAccount investmentAccount_02 = new InvestmentAccount(540340D,1,"Hamid",39,"4R5667");
		System.out.println("Investment account Details: "+ investmentAccount_02.toString());
		
		Bank<SavingsAccount> savingBranch = new Bank<SavingsAccount>();
		Bank<InvestmentAccount> investmentBranch = new Bank<InvestmentAccount>();
		
		savingBranch.addAccount(savingsAccount_01);
		investmentBranch.addAccount(investmentAccount_01);
		
		savingBranch.addAccount(savingsAccount_02);
		investmentBranch.addAccount(investmentAccount_02);
		
		System.out.println("Saving Branch: "+ savingBranch);
		System.out.println("Investment Branch: "+ investmentBranch);
		
		Example example = new Example("Hello", "Hello");
		System.out.println("After comparing Value: "+example.status());
		
		
	}
}
