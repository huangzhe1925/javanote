package com.hz.javanote.genericity;

import java.util.Arrays;
import java.util.List;

public class CharToIntegerMapReducer<K,V> extends MapReducer<Character,Integer>{

	CharToIntegerMapReducer(Mapper<Character, Integer> mapper, Reducer<Integer> reducer) {
		super(mapper, reducer);
	}
	
	
	public static void main(String[] args) {
		CharToIntegerMapReducer<Character,Integer> mapreduce=new CharToIntegerMapReducer<>(new CharToIntegerMapper<Character,Integer>(),new IntegerReducer<Integer>());
		List<Character> list=Arrays.asList('1','1','1','1','1','1','1','1');
		System.out.println(mapreduce.process(list));
	}

	
}
