package com.bjit.java.bank;

import java.util.ArrayList;

public class Bank<T> {
	
	private ArrayList<T> arrayOfDataObj = new ArrayList<>();
	
	public Bank(T data) {
		arrayOfDataObj.add(data);
	}
	
	public Bank() {}

	public void addAccount(T data) {
		arrayOfDataObj.add(data);
	}

	@Override
	public String toString() {
		return "Bank [arrayOfDataObj=" + arrayOfDataObj + "]";
	}
	
}
