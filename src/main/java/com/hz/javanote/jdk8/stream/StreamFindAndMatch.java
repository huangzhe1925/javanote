package com.hz.javanote.jdk8.stream;

import java.util.List;
import java.util.Optional;

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
	
	public static void noneMatch(List<Dish> menu) {
		if (menu.stream().noneMatch(d -> d.getCalories() < 1000)) {
			System.out.println("The menu is all low cal!!");
		}
	}
	
	public static Optional<Dish> findAnyDish(List<Dish> menu){
		return menu.stream().filter(Dish::isVegetarian).findAny();
	}
	
	public static Optional<Dish> findFirstDish(List<Dish> menu){
		return menu.stream().filter(Dish::isVegetarian).findFirst();
	}
}
