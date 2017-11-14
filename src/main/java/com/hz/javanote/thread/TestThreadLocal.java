package com.hz.javanote.thread;

public class TestThreadLocal {
	
	public static ThreadLocal<String> var=new ThreadLocal<String>();
	
	public static void main(String args[]){
		var.set("thread id: " +Thread.currentThread().getId()+" "+Thread.currentThread().getName());
		var.set("still this thread id: " +Thread.currentThread().getId()+" "+Thread.currentThread().getName());
		new Thread(new Runnable(){
			@Override
			public void run() {
//				var=new ThreadLocal<String>();
				var.set("Other thread : "+Thread.currentThread().getId()+" "+Thread.currentThread().getName());
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(var.get());
			}
			
		}).start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
	}
		
		
		System.out.println(var.get());
	}
}
