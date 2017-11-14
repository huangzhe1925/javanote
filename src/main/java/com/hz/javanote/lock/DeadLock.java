package com.hz.javanote.lock;

public class DeadLock {

	public static Object LOCK_1=new Object();
	public static Object LOCK_2=new Object();
	
	public void testLock(){
		Runner runner=new Runner();
		Runner2 runner2=new Runner2();
		for(int i=0;i<1000;i++){
			if(i%2==0){
				new Thread(runner).start();
			}else{
				new Thread(runner2).start();
			}
			
		}
	}
	public static void main(String args[]){
		DeadLock deadlock=new DeadLock();
		deadlock.testLock();
	}
}


class Runner implements Runnable{

	@Override
	public void run() {
		
		synchronized(DeadLock.LOCK_1){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(DeadLock.LOCK_2){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Running "+Thread.currentThread().getName());
			}
		}
	}
	
}

class Runner2 implements Runnable{

	@Override
	public void run() {
		
		synchronized(DeadLock.LOCK_2){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(DeadLock.LOCK_1){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Running "+Thread.currentThread().getName());
			}
		}
	}
	
}