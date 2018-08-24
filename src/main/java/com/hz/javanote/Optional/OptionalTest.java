package com.hz.javanote.Optional;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		testOptional1();
	}
	
	public static void testOptional1() {
		Optional<Person> person=Optional.of(new Person());
		Optional<String> license=person.map(Person::getCar).map(Car::getLicense);
		System.out.println(license.get());
	}
	
	public static void testOptional2() {
		
		
	}
	
	
	static class Person{
		private Car car;
		public Person() {
			this.car=new Car();
		}
		
		public Car getCar() {
			return car;
		}

		public void setCar(Car car) {
			this.car = car;
		}
	}
	
	static class Car{
		public Car() {
			this.license="licenseA";
		}
		private String license;
		public String getLicense() {
			return license;
		}

		public void setLicense(String license) {
			this.license = license;
		}
	}
}
