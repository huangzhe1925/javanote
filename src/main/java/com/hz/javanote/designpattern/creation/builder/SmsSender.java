package com.hz.javanote.designpattern.creation.builder;

public class SmsSender implements Sender {
    @Override  
    public void Send() {  
        System.out.println("this is smsSender!");  
    }  
}
