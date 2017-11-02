package com.hz.javanote.jdk8.stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.groupingBy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class NewStreamTest {

	public static void main(String[] args) {
		List<Dish> menu=getMenu();
		
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
	
	public static List<Dish> getMenu() {
		List<Dish> menu = Arrays.asList(
				new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH) );		
		return menu;
	}
	
	
	public static class Dish {
		private final String name;
		private final boolean vegetarian;
		private final int calories;
		private final Type type;
		
		public Dish(String name, boolean vegetarian, int calories, Type type) {
			this.name = name;
			this.vegetarian = vegetarian;
			this.calories = calories;
			this.type = type;
		}
		public String getName() {
			return name;
		}
		@Override
		public String toString() {
			return "Dish [name=" + name + ", vegetarian=" + vegetarian + ", calories=" + calories + ", type=" + type
					+ "]";
		}
		public boolean isVegetarian() {
			return vegetarian;
		}
		public int getCalories() {
			return calories;
		}
		public Type getType() {
			return type;
		}
		public enum Type { MEAT, FISH, OTHER }
	}
}
