package com.hz.javanote.designpattern.creation.abstractfactory;

public class SendMailFactory implements Provider {  
    
    @Override  
    public Sender produce(){  
        return new MailSender();  
    }  
}  