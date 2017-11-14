package com.hz.javanote.designpattern.creation.abstractfactory;

public class MailSender implements Sender {  
    @Override  
    public void Send() {  
        System.out.println("this is mailsender!");  
    }  
}  