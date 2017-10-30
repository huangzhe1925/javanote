package com.hz.javanote.genericity;

import java.util.ArrayList;
import java.util.List;


class A{
	
}

class B extends A{
	
}

public class TestInheritage {

	
	public static void main(String args[]){
		ArrayList<Integer> alistInt=new ArrayList<Integer>();
		Class<ArrayList> alistClass=ArrayList.class;
		Class<List> listClass=List.class;
		
		boolean result=alistClass.isAssignableFrom(listClass);
		System.out.println(result);
		
		
		result=listClass.isAssignableFrom(alistClass);
		System.out.println(result);
		
		
		System.out.println(A.class.isAssignableFrom(B.class));
	}
}
