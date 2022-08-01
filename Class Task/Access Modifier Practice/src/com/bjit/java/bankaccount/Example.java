package com.bjit.java.bankaccount;

import java.util.Comparator;

public class Example {
	private String value_01;
	private String value_02;
	
	public Example(String value_01, String value_02) {
		super();
		this.value_01 = value_01;
		this.value_02 = value_02;
	}
	
	public boolean status() {
		return value_01.equals(value_02); 
	}
	

}
