package com.hz.javanote.jdk8.stream;

import java.util.concurrent.ConcurrentHashMap;

public class StreamPerformance {
	public static void main(String args[]) {

		ConcurrentHashMap<KeyObject,Boolean> map=new ConcurrentHashMap<>();
		for(int index=0;index<100;index++) {
			map.put(new KeyObject(index+""), Boolean.TRUE);
		}
		map.put(new KeyObject("500"), Boolean.TRUE);
		for(int index=100;index<200;index++) {
			map.put(new KeyObject(index+""), Boolean.TRUE);
		}
		
		
		Long now=System.nanoTime();
		for(KeyObject key:map.keySet()){
			if(key.equals(new KeyObject("500"))) {
				break;
			}
		}
		Long end=System.nanoTime();
		System.out.println(end-now);
		
		
		now=System.nanoTime();
		map.keySet().forEach((key)->{
			if(key.equals(new KeyObject("500"))) {
				
			}
		});
		end=System.nanoTime();
		System.out.println(end-now);
		
		now=System.nanoTime();
		KeyObject dKey=new KeyObject("500");
//		map.keySet().parallelStream().filter(key -> key.equals(dKey)).findFirst().get();
		map.keySet().parallelStream().anyMatch(key -> key.equals(dKey));
		end=System.nanoTime();
		System.out.println(end-now);
		
	}
}

class KeyObject{
	
	private String key;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyObject other = (KeyObject) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	public KeyObject(String key) {
		super();
		this.key = key;
	}
	
	public KeyObject() {
		super();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
