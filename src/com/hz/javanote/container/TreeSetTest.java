package com.hz.javanote.container;

import java.util.Random;
import java.util.TreeSet;

public class TreeSetTest {
	public static void main(String args[]){
		TreeSet<TestObject> ts=new TreeSet<TestObject>();
		Random rand=new Random();
		for(int index=0;index<1000;index++){
//			char c=(char)('a'+rand.nextInt(26));
			ts.add(new TestObject(rand.nextInt(26)));
		}
//		ts.contains(new TestObject(10));
//		ts.remove("c");
		System.out.println(ts);
	}
}

class TestObject implements Comparable<TestObject>{
	TestObject(int num){
		this.num=num;
	}
	private int num=0;

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
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
		TestObject other = (TestObject) obj;
		if (num != other.num)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return num+"";
	}

	@Override
	public int compareTo(TestObject o) {
		return this.num-o.num;
	}
	
}
