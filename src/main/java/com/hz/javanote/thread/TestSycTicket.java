package com.hz.javanote.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TicketSouce implements Runnable {
	// Ʊ������
	volatile private int ticket = 10;

	public void run() {
		for (int i = 1; i < 50; i++) {
			if (ticket > 0) {
				// ����1s���У�Ϊ��ʹЧ�������ԣ�������ܳ�����Ч��
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "�Ŵ�������"
						+ this.ticket-- + "��Ʊ");
			}
		}
	}
}

public class TestSycTicket {
	public static void main(String args[]) {
		TicketSouce mt = new TicketSouce();
		// ���ڻ�Ʊ������������
		ExecutorService exe=Executors.newCachedThreadPool();
		exe.execute(new Thread(mt, "a"));
		exe.execute(new Thread(mt, "b"));
		exe.execute(new Thread(mt, "c"));
	}

}
