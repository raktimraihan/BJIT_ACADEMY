package com.bjit.java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ArrayListExample {
	public static void main(String[] args) {
        List<Integer> randomNumbers = new ArrayList<>();
        randomNumbers.add(100);
        randomNumbers.add(120);
        randomNumbers.add(1);
        randomNumbers.add(95);
        randomNumbers.add(99);

        System.out.println("Numbers : " + randomNumbers);

        // Sort an ArrayList using its sort() method. You must pass a Comparator to the ArrayList.sort() method.
        randomNumbers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer name1, Integer name2) {
                return name1.compareTo(name2);
            }
        });

        // The above `sort()` method call can also be written simply using lambda expression
        randomNumbers.sort((name1, name2) -> name1.compareTo(name2));

        // Following is an even more concise solution
        randomNumbers.sort(Comparator.reverseOrder());

        System.out.println("Sorted Numbers : " + randomNumbers);
    }
}
