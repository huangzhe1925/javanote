package com.hz.javanote.designpattern.structure.bridge;

public class MyBridge extends Bridge {  
    public void method(){  
        getSource().method();  
    }  
}  