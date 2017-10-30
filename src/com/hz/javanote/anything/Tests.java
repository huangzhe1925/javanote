package com.hz.javanote.anything;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Tests {
	public static void main(String args[]) throws Exception {
		String testCase="7";
		Class<?> threadClazz = Tests.class;
        Method method = threadClazz.getMethod("test"+testCase);  
        method.invoke(method);
	}
	
	
	public static void test7(){
		Integer num=100;
		ValueDown1(num);
		System.out.println(num);
	}
	
	
	public static void ValueDown1(Integer num){
		num=new Integer("101");
		System.out.println(num);
		
	}
	
	public static void test6(){
		String strs[]=new String[]{"111s","1","111.2","111s","111.s","1s11"};
		
		for(String str:strs){
			boolean isNum=str.matches("^[+-]?([0-9]+)\\.?(([0-9]+)?)");
			System.out.println(str+":"+isNum);
		}
	}
	
	
	
	
	public static void test0(){
//		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
//		System.out.println(Integer.MAX_VALUE);
//		Integer out=Integer.MAX_VALUE+1;
//		System.out.println(Integer.toBinaryString(out));
//		System.out.println(out);
		
//		List<Map<String,String>> list2=new ArrayList();
//		list2.add();
//		list2.add("1234");
//		list2.remove(0);
//		System.out.println(list2);
//		
//		List<String> list3=Collections.unmodifiableList(list2);
//		list3.remove(0);
//		System.out.println(list3);
		final Integer[] arr=new Integer[]{1,2,3,4};
		arr[0]++;
		System.out.println(Arrays.asList(arr));
//		arr=new Integer[]{1,2,3,4,5};
//		System.out.println(Arrays.asList(arr));
		
//		List<String> list1=Arrays.asList();
//		list1.remove(0);
//		System.out.println(list1);
	}
	 
	
	public static void test1(){
		System.out.println(Boolean.TRUE.toString());
		TestClause tc=new TestClause(){ 
		// this method is useless, thinking how to use it 
		private void print() {
			System.out.println("print");
		}};
	}
	public static void test2(){
		HashMap map=new HashMap();
		Object obj=Long.valueOf("0");
		map.put("1", obj);
		obj=((Long)obj)+3;
		map.put("1", obj);
		System.out.println(map.get("1"));
	}
	
	public static void test3(){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(1951, 0, 1, 0, 0, 0);
		System.out.println(new Date(cal.getTimeInMillis()));
	}
	
	public static void test4(){
		Object o1=null;
		Long l1=(Long)o1;
	}
	
	public static void test5(){
		int cards=13;
		int len=1;
		int[] cardsArr= new int[cards];
		cardsArr[0]=1;
		
		int count=0;
		int nowCard=2;
		int stepCount=0;
		while(nowCard<=cards){
			while(0!=cardsArr[count%cards]||stepCount<len){
				if(0==cardsArr[count%cards]){
					stepCount++;
				}
				count++;
			}
			cardsArr[count%cards]=nowCard;
			stepCount=0;
			nowCard++;
		}
		System.out.println(Arrays.toString(cardsArr));
	}
	
}

class TestClause{
	public TestClause(){
		System.out.println("Initial");
	}
	private void privateMethod(){
		System.out.println("privateMethod");
	}
}