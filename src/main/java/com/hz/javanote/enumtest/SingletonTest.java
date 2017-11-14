package com.hz.javanote.enumtest;

public class SingletonTest {

	public static void main(String args[]) {
		Singleton_1 sin = Singleton_1.getInstance();
		Singleton_2 sin2 = Singleton_2.getInstance();
	}
}

class Singleton_1 {
	private static Singleton_1 instance;

	public static Singleton_1 getInstance() {
		if (instance == null) {
			synchronized (Singleton_1.class) {
				if (instance == null) {
					instance = new Singleton_1();
				}
			}
		}
		return instance;
	}
}

class Singleton_2 {
	private static Singleton_2 instance;

	public static Singleton_2 getInstance() {
		if (instance == null) {
			getInstanceInternal();
		}
		return instance;

	}

	private static synchronized void getInstanceInternal() {
		if (instance == null) {
			instance = new Singleton_2();
		}
	}

}