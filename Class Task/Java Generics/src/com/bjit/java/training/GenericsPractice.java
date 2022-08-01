package com.bjit.java.training;

import java.util.Comparator;

public class GenericsPractice<T> {
	private T data;
	
	public GenericsPractice(){};
	
	public GenericsPractice(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
