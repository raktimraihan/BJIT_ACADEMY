package com.bjit.training.java;

public class StringComparisionInThreeWays 
{
	public static void main(String[] args) 
	{	
		//Initialize two string variables
		String firstString = "Hello";
		String secondString = "Hello";
		
		//compare using == and print result in boolean
		System.out.println("Comparition Result using == : "+ (firstString==secondString));
		
		//compare using equals() and print result in boolean
		System.out.println("Comparition Result using == : "+ (firstString.equals(secondString)));
		
		//compare using compareTo() and print result in boolean
		System.out.println("Comparition Result using == : "+ (firstString.compareTo(secondString)));
	}

}
