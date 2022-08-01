package com.bjit.java.training;

import java.util.ArrayList;
import java.util.List;

public class GenericsHandler {
	
	public static void main(String[] args) {
		
		GenericsPractice<Integer> genericsIntegerObj = new GenericsPractice<>(23);
		System.out.println("Value of the Generics Obj is: "+genericsIntegerObj.getData());
		
		GenericsPractice<String> genericsObjString = new GenericsPractice<>("Hello");
		System.out.println("Value of the Generics Obj is: "+genericsObjString.getData());
		
		GenericsPractice<Float> genericsFloatObj = new GenericsPractice<>(45f);
		GenericsPractice<Byte> genericsByteObj = new GenericsPractice<>((byte)45);
		
		System.out.println("Value of the Generics Obj is: "+genericsFloatObj.getData());
		System.out.println("Value of the Generics Obj is: "+genericsByteObj.getData());
		
		
	}

}
