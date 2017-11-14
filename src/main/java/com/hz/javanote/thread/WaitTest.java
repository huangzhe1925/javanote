package com.hz.javanote.thread;

public class WaitTest implements Runnable{

	public WaitTest(){
	}
	
	private Integer flag=1;
	public static void main(String args[]) {
		WaitTest wt=new WaitTest();
		NotifierTest nt=new NotifierTest(wt.flag);
		new Thread(wt).start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(nt).start();
	}

	@Override
	public void run() {
		System.out.println("Waiter waits");
		try {
			synchronized (this.flag) {
				System.out.println("Waiter get key");
				this.flag.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Waiter start");
	}
}

class NotifierTest implements Runnable{

	Object toBeAwake;
	NotifierTest(Object obj){
		this.toBeAwake=obj;
	}
	
	@Override
	public void run() {
		System.out.println("Notifier start");
		synchronized (toBeAwake) {
			System.out.println("Notifier get key");
			toBeAwake.notify();
		}
	}
	
}
