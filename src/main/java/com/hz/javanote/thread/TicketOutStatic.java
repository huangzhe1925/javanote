package com.hz.javanote.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketOutStatic {
	
	public static Integer TICKET_NUM=500;
	public static Integer PEOPLE_NUM=2000;
	public static AtomicInteger TICKET_NUM_ATOM=new AtomicInteger(500);
	
	public static Object LOCK=new Object();
	public static void main(String args[]){
		
		for(int i=0;i<PEOPLE_NUM;i++){
			new Thread(new TakerStatic((i+1)+"")).start();
		}
	}
}


class TakerStatic implements Runnable{
	private String id;
	
	TakerStatic(String id){
		this.id=id;
	}

	@Override
	public void run() {
	
		//如果synchronize的是TicketOutStatic.TICKET_NUM对象，在块中进行修改会改变TicketOutStatic.TICKET_NUM地址导致，多线程锁的不是一个东西
		synchronized (TicketOutStatic.LOCK) {
			if(TicketOutStatic.TICKET_NUM<=0){
				return;
			}
			TicketOutStatic.TICKET_NUM= TicketOutStatic.TICKET_NUM-1;
			System.out.println("Thread : "+this.id+" , left ticket : "+TicketOutStatic.TICKET_NUM);
		}
		
	}


}

