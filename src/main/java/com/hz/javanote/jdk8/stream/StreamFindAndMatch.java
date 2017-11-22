package com.hz.javanote.jdk8.stream;

import java.util.List;

public class StreamFindAndMatch {
	public static void main(String[] args) {
		List<Dish> menu = Dish.getMenu();
	}
	
	public static void anyVegetarian(List<Dish> menu) {
		if (menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("The menu is (somewhat) vegetarian friendly!!");
		}
	}
	
	public static void allLowCal(List<Dish> menu) {
		if (menu.stream().allMatch(d -> d.getCalories() < 1000)) {
			System.out.println("The menu is all low cal!!");
		}
	}
}
