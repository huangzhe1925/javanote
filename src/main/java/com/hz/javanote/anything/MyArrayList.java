package com.hz.javanote.anything;

import java.util.ArrayList;
import java.util.List;

public class MyArrayList<N extends innerInterface> extends ArrayList<N> {

	@Override
	public boolean add(N e) {
		return super.add(e);
	}
	
	
	public static void main(String args[]){
		List<innerInterface> list=new MyArrayList<>();
		list.add(new innerInterface() {
			@Override
			public void showThing() {
				System.out.println("yes this is innerInterface");
			}
		});
		
		
		for(innerInterface item:list){
			item.showThing();
		}
	}
}

interface innerInterface{
	public void showThing();
}
