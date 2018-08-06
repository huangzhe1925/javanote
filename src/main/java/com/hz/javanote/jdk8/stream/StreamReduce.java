package com.hz.javanote.jdk8.stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamReduce {
	public static void main(String[] args) {
		Stream<Integer> nums = Arrays.stream(new Integer[]{1,2,3,4,5});
	}
	
	public static void getSum(Stream<Integer> nums) {
//		Optional<Integer> sum = nums.reduce(Integer::sum);
		Integer sum = nums.reduce(0,Integer::sum);
	}
	
	public static void getMax(Stream<Integer> nums) {
//		Optional<Integer> sum = nums.reduce(Integer::sum);
		Optional<Integer> max = nums.reduce(Integer::max);
	}
	
	public static void getMin(Stream<Integer> nums) {
//		Optional<Integer> sum = nums.reduce(Integer::sum);
		Optional<Integer> min = nums.reduce(Integer::min);
	}
}
