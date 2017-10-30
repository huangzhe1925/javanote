package com.hz.javanote.thread;

public class TicketOut {
	
	
	public static Integer PEOPLE_NUM=1000;
	public static void main(String args[]){
		
		Taker taker=new Taker();
		for(int i=0;i<PEOPLE_NUM;i++){
			new Thread(taker).start();
		}
		
	}
	
}


class Taker implements Runnable{
	private Integer ticketNum=200 ;
	private Object lock=new Object();
	
	@Override
	public void run() {
		
		synchronized (lock) {
			if(ticketNum<=0){
				return;
			}
			ticketNum= ticketNum-1;
			System.out.println("Thread : "+(Thread.currentThread().getId()+1)+" take one ticket, left ticket : "+ticketNum);
		}
		
	}


}
