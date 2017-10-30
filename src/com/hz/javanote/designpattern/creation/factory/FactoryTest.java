package com.hz.javanote.designpattern.creation.factory;

public class FactoryTest {

	 public static void main(String[] args) {  
		 
		 	//input string get sender
	        SendFactory factory = new SendFactory();  
	        Sender sender = factory.produce("sms");  
	        sender.Send();  
	        
	        
	        //using method get sender
	        SendMultiFactory multiFactory = new SendMultiFactory();  
	        sender = multiFactory.produceSms();  
	        sender.Send();  
	        
	        //use static factory
	        sender=SendStaticFactory.produceSms();
	        sender.Send();  
	    }  
}
