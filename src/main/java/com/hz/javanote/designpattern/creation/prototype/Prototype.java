package com.hz.javanote.designpattern.creation.prototype;

public class Prototype {
	 public Object clone() throws CloneNotSupportedException {  
	        Prototype proto = (Prototype) super.clone();  
	        return proto;  
	    }  
}
