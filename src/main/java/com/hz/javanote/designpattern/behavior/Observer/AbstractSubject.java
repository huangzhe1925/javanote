package com.hz.javanote.designpattern.behavior.Observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractSubject implements Subject {  
	  
    private List<Observer> list = new ArrayList<Observer>();  
    @Override  
    public void add(Observer observer) {  
    	list.add(observer);  
    }  
  
    @Override  
    public void del(Observer observer) {  
    	list.remove(observer);  
    }  
  
    @Override  
    public void notifyObservers() {  
    	Iterator<Observer> enumo = list.iterator();  
        while(enumo.hasNext()){  
            enumo.next().update();  
        }  
    }  
}  