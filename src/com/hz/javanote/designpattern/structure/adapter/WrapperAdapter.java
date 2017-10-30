package com.hz.javanote.designpattern.structure.adapter;

public class WrapperAdapter implements Targetable {

	
	private Source source;  
	
	public WrapperAdapter(Source source){  
        super();  
        this.source = source;  
    }  
	
	@Override
	public void method1() {
		 source.method1();  
	}

	@Override
	public void method2() {
		System.out.println("this is the targetable method!");  
	}

}
