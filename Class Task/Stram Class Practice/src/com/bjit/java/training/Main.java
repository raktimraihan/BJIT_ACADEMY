package com.bjit.java.training;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	public static void integerEvenPrint(int limit) {
		List<Integer> listInteger =  Stream.generate(() -> new Random()
				.nextInt(0,100))
				.limit(limit)
				.filter(i -> i%2 == 0)
				.collect(Collectors.toList());
		
		MyInterface<List<Integer>> printInterface = list -> list.stream().forEach(element -> System.out.print(element+" "));
		printInterface.print(listInteger);
	}
	
	public static void doubleRandomPrint(int limit) {
		List<Double> listDouble =  Stream.generate(() -> new Random()
				.nextDouble(0,1000))
				.limit(limit)
				.sorted()
				.collect(Collectors.toList());
		
		MyInterface<List<Double>> printInterface03 = list -> list.stream().forEach(
				element -> System.out.println(element));
		printInterface03.print(listDouble);
		System.out.println();
		
		roundDouble<List<Double>> printInterface = list -> list.stream().forEach(
				element -> list[element]*= list[element]);
		MyInterface<List<Double>> printInterface01 = list -> list.stream().forEach(
				element -> System.out.println(element));
		printInterface01.print(listDouble);
	}
	
	public static void main(String[] args) {
		System.out.println("Even Integers: ");
		integerEvenPrint(20);
		System.out.println();
		System.out.println("Doubles: ");
		doubleRandomPrint(10);
	}
}
