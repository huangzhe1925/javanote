package com.hz.javanote.container;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CollectionToArray {
	public static void main(String args[]) {
		Map<String,String> map=new HashMap<>();
		for(int i=0;i<5;i++){
			map.put(i+"", i+"");
		}
		System.out.println(Arrays.toString(map.values().toArray(new String[]{})));
	}
}
