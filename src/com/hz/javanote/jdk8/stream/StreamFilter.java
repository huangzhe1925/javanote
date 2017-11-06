package com.hz.javanote.jdk8.stream;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StreamFilter {
	public static void main(String[] args) {
		List<Dish> menu = Dish.getMenu();

		// printMap(getAllDishNameDishMap(menu));
		printList(getVegetarianDishList(menu));
		printList(getDistinctNumberList(Arrays.asList(1, 2, 1, 3, 3, 2, 4)));

	}

	public static void printList(List<? extends Object> list) {
		list.parallelStream().forEach(l -> {
			System.out.println(l.toString());
		});
	}

	public static void printMap(Map<? extends Object, ? extends Object> map) {
		map.entrySet().parallelStream().forEach(l -> {
			System.out.println("Key:[" + l.getKey() + "]  Value:[" + l.getValue() + "]");
		});
	}

	public static List<Dish> getVegetarianDishList(List<Dish> menu) {
		List<Dish> vegetarianMenu = menu.parallelStream().filter(Dish::isVegetarian).collect(toList());
		return vegetarianMenu;
	}

	public static List<Integer> getDistinctNumberList(List<Integer> numbers) {
		List<Integer> result = numbers.parallelStream().filter(i -> i % 2 == 0).distinct().collect(toList());
		return result;
	}

	public static List<Dish> getThreeHighCalDishLish(List<Dish> menu) {
		List<Dish> dishes = menu.parallelStream().filter(d -> d.getCalories() > 300).limit(3).collect(toList());
		return dishes;
	}
	
	public static List<Dish> getSkipTwoHighCalDishLish(List<Dish> menu) {
		List<Dish> dishes = menu.parallelStream().filter(d -> d.getCalories() > 300).skip(2).collect(toList());
		return dishes;
	}
	
	public static List<Integer> getDishNameCharacterNumberLish(List<Dish> menu) {
		List<Integer> dishes = menu.parallelStream().map(Dish::getName).map(String::length).collect(toList());
		return dishes;
	}
	
//	public static List<String> getUniqueCharacterList(String sentence){
//		List<String> uniqueCharacters =sentence.split(",| ").
//				words.stream()
//				.map(w -> w.split(""))
//				.flatMap(Arrays::stream)
//				.distinct()
//				.collect(Collectors.toList());
//	}

}
