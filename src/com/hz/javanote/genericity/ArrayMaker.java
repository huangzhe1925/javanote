package com.hz.javanote.genericity;

import java.lang.reflect.Array;// attention here, do need to import pkg in lang.
import java.util.Arrays;

public class ArrayMaker<T> {
	private Class<T> kind;
	public ArrayMaker(Class<T> kind){ this.kind=kind;}
	@SuppressWarnings("unchecked")
	T[] create(int size){
		Object obj=Array.newInstance(kind, size);
		return (T[])obj;
	}
	
	public static void main(String args[]){
		ArrayMaker<String> stringMaker=new ArrayMaker<>(String.class);
		String[] stringArray=stringMaker.create(9);
		System.out.println(Arrays.toString(stringArray));
		
		
		
	}
	
}
