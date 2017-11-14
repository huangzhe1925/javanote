package com.hz.javanote.genericity;

import java.util.ArrayList;
import java.util.List;

public class GenericsAndCovariance {
	public static void main(String args[]){
		List<? extends Fruit> flist =new ArrayList<Apple>();
		// extends 是 上边界
		// super 是下边界
//		flist.add(new Apple()); error
	}
}

class Fruit{};
class Apple extends Fruit{}
class Jonathan extends Apple{}
class Orange extends Fruit{}