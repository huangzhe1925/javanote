package com.hz.javanote.genericity;

public class IntegerReducer<V> implements Reducer<Integer> {

	@Override
	public Integer reduce(Integer v1, Integer v2) {
		return v1+v2;
	}

}
