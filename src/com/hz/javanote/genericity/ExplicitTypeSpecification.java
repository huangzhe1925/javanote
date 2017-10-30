package com.hz.javanote.genericity;

import java.util.HashMap;
import java.util.Map;

public class ExplicitTypeSpecification {
	static void f(Map<String,String> map){}
	public static void main(String args[]){
		f(New.<String,String>map());
	}
}

class New{
	public static <K,V> Map<K,V> map(){
		return new HashMap<K,V>();
	}
}
