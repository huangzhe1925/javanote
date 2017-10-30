package com.hz.javanote.collection.maps;

import java.util.HashMap;
import java.util.Map;

public class HashMapWithSameHashNotEqualTest {

	public static void main(String[] args) {
		NotEqualObject obj1=new NotEqualObject("id1");
		NotEqualObject obj2=new NotEqualObject("id1");
		
		Map<NotEqualObject,String> map=new HashMap<NotEqualObject,String>();
		map.put(obj1, "id1");
		map.put(obj2, "id1");
		System.out.println(map.size());
		
		/*
		 * [hashcode 1] --> [link] -- > [link] 
		 * [hashcode 2]--> [link] -- > [link]
		 * [hashcode 3]--> [link] -- > [link]
		 * [hashcode 4]--> [link] -- > [link]
		 * 
		 * */
		
	}
	
	public static class NotEqualObject{
		private String id;
		public NotEqualObject(String id){
			this.id=id;
		}
		@Override
		public int hashCode() {
			return 101;
		}
		@Override
		public boolean equals(Object obj) {
			return false;
		}
	}
}
