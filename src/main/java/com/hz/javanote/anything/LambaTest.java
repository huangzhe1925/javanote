package com.hz.javanote.anything;

public class LambaTest {
	public static void main(String[] args) {

		Runnable ran=()->System.out.println("Test");
		new Thread(()->{
			System.out.println("Test");
		}).start();
		
		
	}
	
}



