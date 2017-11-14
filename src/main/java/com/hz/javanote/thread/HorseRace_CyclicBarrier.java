package com.hz.javanote.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Horse implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private int strides = 0;
	private static Random rand = new Random(47);
	private static CyclicBarrier barrier;

	public Horse(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	public synchronized int getStrides() {
		return strides;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					strides += rand.nextInt(3);
				}
				barrier.await();
			}
		} catch (InterruptedException e) {

		} catch (BrokenBarrierException e) {
			throw new RuntimeException(e);
		}
	}

	public String toString() {
		return "Horse " + id + " ";
	}

	public String tracks() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < getStrides(); i++) {
			s.append("*");
		}
		s.append(id);
		return s.toString();
	}
}

public class HorseRace_CyclicBarrier {
	static int times=0;
	static final int FINISH_LINE = 75;
	private List<Horse> horses = new ArrayList<Horse>();
	private ExecutorService exec = Executors.newCachedThreadPool();
	private CyclicBarrier barrier;

	public HorseRace_CyclicBarrier(int nHorses, final int pause) {
		barrier = new CyclicBarrier(nHorses+1, new Runnable() {
			public void run() {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < FINISH_LINE; i++) {
					sb.append("=");
				}
				sb.append((times++)+" round");
				System.out.println(sb);
				for (Horse horse : horses) {
					System.out.println(horse.tracks());
				}
				for (Horse horse : horses) {
					if (horse.getStrides() >= FINISH_LINE) {
						System.out.println(horse + " won!");
						exec.shutdownNow();
						return;
					}
				}
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e) {
					System.out.println("barrier-action sleep interrupted");
				}
			}
		});
		for (int i = 0; i < nHorses; i++) {
			Horse horse = new Horse(barrier);
			horses.add(horse);
			exec.execute(horse);
		}
	}

	public static void main(String args[]) {
		int nHorses = 7;
		int pause = 200;
		new HorseRace_CyclicBarrier(nHorses, pause);
	}
}
