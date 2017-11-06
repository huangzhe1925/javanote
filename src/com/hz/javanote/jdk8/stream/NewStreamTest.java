package com.hz.javanote.jdk8.stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class NewStreamTest {

	public static void main(String[] args) {
		List<Dish> menu=Dish.getMenu();
		
		//printMap(getAllDishNameDishMap(menu));
		printMap(getAllDishNameDishListMap(menu));
	}

	
	public static void printList(List<? extends Object> list){
		list.parallelStream().forEach(l->{System.out.println(l.toString());});
	}
	
	public static void printMap(Map<? extends Object,? extends Object> map){
		map.entrySet().parallelStream().forEach(l->{System.out.println("Key:["+l.getKey()+"]  Value:["+l.getValue()+"]");});
	}
	
	public static List<String> getThreeHighCaloricDishNames(List<Dish> menu) {
		List<String> result=menu.parallelStream().filter(d -> d.getCalories() > 300).map(Dish::getName).limit(3).collect(toList());
		return result;
	}
	
	public static Map<String,Integer> getAllDishNameCalMap(List<Dish> menu){
		Map<String, Integer> result=menu.parallelStream().collect(toMap(Dish::getName,Dish::getCalories));
		return result;
	}
	
	public static Map<String,Dish> getAllDishNameDishMap(List<Dish> menu){
		Map<String, Dish> result=menu.parallelStream().collect(toMap(Dish::getName,Function.identity()));
		return result;
	}
	
	public static Map<String,List<Dish>> getAllDishNameDishListMap(List<Dish> menu){
		Map<String,List<Dish>> result=menu.parallelStream().collect(groupingBy(Dish::getName));
		return result;
	}

	
}
