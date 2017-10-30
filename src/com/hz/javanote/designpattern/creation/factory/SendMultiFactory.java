package com.hz.javanote.designpattern.creation.factory;

public class SendMultiFactory {
	public Sender produceMail(){  
        return new MailSender();  
    }  
      
    public Sender produceSms(){  
        return new SmsSender();  
    }  
}
