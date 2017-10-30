package com.hz.javanote.genericity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LostInformation {

	public static void main(String args[]){
		List<Integer> list=new ArrayList<Integer>();
		Map<Integer,String> map=new HashMap<Integer,String>();
		System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
		System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
	}
}

