package com.hz.javanote.designpattern.creation.abstractfactory;

public class FactoryTest {

	  public static void main(String[] args) {  
	        Provider provider = new SendMailFactory();  
	        Sender sender = provider.produce();  
	        sender.Send();  
	    }  
}
