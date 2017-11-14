package com.hz.javanote.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TicketSouce implements Runnable {
	// 票的总数
	volatile private int ticket = 10;

	public void run() {
		for (int i = 1; i < 50; i++) {
			if (ticket > 0) {
				// 休眠1s秒中，为了使效果更明显，否则可能出不了效果
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "号窗口卖出"
						+ this.ticket-- + "号票");
			}
		}
	}
}

public class TestSycTicket {
	public static void main(String args[]) {
		TicketSouce mt = new TicketSouce();
		// 基于火车票创建三个窗口
		ExecutorService exe=Executors.newCachedThreadPool();
		exe.execute(new Thread(mt, "a"));
		exe.execute(new Thread(mt, "b"));
		exe.execute(new Thread(mt, "c"));
	}

}
