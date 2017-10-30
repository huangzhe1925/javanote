package com.hz.javanote.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();

	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("trylock(): " + captured);
		} finally {
			if (captured) {
				lock.unlock();
			}
		}
	}
	
	public void timed(){
		boolean captured = lock.tryLock();
		try{
			captured =lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try{
			System.out.println("lock.tryLock(2, TimeUnit.SECONDS): "+captured);
		} finally{
			if (captured) {
				lock.unlock();
			}
		}
	}
	
	public static void main(String args[]){
		final AttemptLocking al=new AttemptLocking();
		al.untimed();
		al.timed();
		new Thread(){
			{setDaemon(true);}
			public void run(){
				al.lock.lock();
				while(true){}
			}
		}.start();
		al.untimed();
		al.timed();
	}

}
