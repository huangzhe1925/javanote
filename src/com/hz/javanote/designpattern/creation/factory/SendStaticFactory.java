package com.hz.javanote.designpattern.creation.factory;

public class SendStaticFactory {
	  public static Sender produceMail(){  
	        return new MailSender();  
	    }  
	      
	    public static Sender produceSms(){  
	        return new SmsSender();  
	    }  
}
