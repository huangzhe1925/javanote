package com.hz.javanote.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockCache {
	static Map<String, Object> map = new HashMap<String, Object>();
	static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	static Lock rLock = rwl.readLock();
	static Lock wLock = rwl.writeLock();

	// 获取一个key对应的value
	public static final Object get(String key) {
		rLock.lock();
		try {
			return map.get(key);
		} finally {
			rLock.unlock();
		}
	}

	// 设置key对应的value并返回旧的value
	public static final Object put(String key, Object value) {
		wLock.lock();
		try {
			return map.put(key, value);
		} finally {
			wLock.unlock();
		}
	}

	// 清空缓存
	public static final void clear() {
		wLock.lock();
		try {
			map.clear();
		} finally {
			wLock.unlock();
		}
	}

	public static void main(String args[]) {
		ReentrantReadWriteLockCache.put("1", "1");
		
		for (int i = 0; i < 100; i++) {
			if(i==4) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ReentrantReadWriteLockCache.put("2", "2");
				System.out.println("ReentrantReadWriteLockCache put 2");
			}else {
				new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println(ReentrantReadWriteLockCache.get("1")+" "+System.currentTimeMillis());
					}
				}).start();
			}
		}
	}

}
