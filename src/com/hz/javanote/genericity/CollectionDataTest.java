package com.hz.javanote.genericity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;



// adaptor mode 
public class CollectionDataTest{
	public static void main(String args[]){
		Set<String> set=new LinkedHashSet<String>(new CollectionData<String>(new Government(),3));
		set.addAll(CollectionData.list(new Government() , 3));
		System.out.println(set);
	}
}


class CollectionData<T> extends ArrayList<T>{
	public CollectionData(Generator<T> gen,int quantity){
		for(int i=0;i<quantity;i++){
			add(gen.next());
		}
	}
	public static <T> CollectionData<T> list(Generator<T> gen,int quantity){
		return new CollectionData<T>(gen,quantity);
	}
	
}

class Government implements Generator<String> {
	String[] foundation =("this is the test").split(" ");
	private int index;
	@Override
	public String next() {
		return foundation[index++];
	}

}


interface Generator<T> {
	T next();
}