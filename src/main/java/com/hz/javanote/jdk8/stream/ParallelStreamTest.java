package com.hz.javanote.jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParallelStreamTest {
	 public static void main(String[] args) throws InterruptedException
	  {
	    Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8};
	    List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));
	    List<Integer> parallelStorage = new ArrayList<>();//Collections.synchronizedList(new ArrayList<>());
	    listOfIntegers
	            .parallelStream()
	            // Don't do this! It uses a stateful lambda expression.
	            .map(e -> {
	                parallelStorage.add(e);
	                return e;
	            })
	            .forEachOrdered(e -> System.out.print(e + " "));
	    System.out.println();
	    parallelStorage.stream().forEachOrdered(e -> System.out.print(e + " "));
	    System.out.println();
	    System.out.println("Sleep 5 sec");
	    TimeUnit.SECONDS.sleep(5);
	    parallelStorage.stream().forEachOrdered(e -> System.out.print(e + " "));
	}
}
