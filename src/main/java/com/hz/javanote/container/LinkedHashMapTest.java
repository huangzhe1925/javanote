package com.hz.javanote.container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class LinkedHashMapTest {
	public static void main(String args[]){
		List<String> list=Arrays.asList("1","2","3","4","5");
		List<String> list1=new ArrayList<String>(list);
		list1.add("6");
		Collections.addAll(list1,"7");
		Collections.addAll(list1,new String[]{"8","9"});
		System.out.println(list.toString());
		System.out.println(list1.toString());
	}
}
