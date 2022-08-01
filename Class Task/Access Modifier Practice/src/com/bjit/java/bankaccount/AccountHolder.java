package com.bjit.java.bankaccount;

public class AccountHolder {
	
	private String Name;
	private int age;
	private String nid;
	
	protected String getName() {
		return Name;
	}
	protected void setName(String name) {
		Name = name;
	}
	protected int getAge() {
		return age;
	}
	protected void setAge(int age) {
		this.age = age;
	}
	protected String getNid() {
		return nid;
	}
	protected void setNid(String nid) {
		this.nid = nid;
	}
	
	AccountHolder(String name, int age, String nid) {
		Name = name;
		this.age = age;
		this.nid = nid;
	}
	
	protected AccountHolder(){};
	
	String toStringPrint() {
		return "AccountHolder [Name=" + Name + ", age=" + age + ", nid=" + nid + "]";
	}
	
	
	
	

}
