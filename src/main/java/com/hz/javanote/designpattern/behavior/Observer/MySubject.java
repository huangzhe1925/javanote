package com.hz.javanote.designpattern.behavior.Observer;

public class MySubject extends AbstractSubject {  
	  
    @Override  
    public void operation() {  
        System.out.println("update self!");  
        notifyObservers();  
    }  
  
}  