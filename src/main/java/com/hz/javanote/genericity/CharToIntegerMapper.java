package com.hz.javanote.genericity;

public class CharToIntegerMapper<K, V> implements Mapper<Character, Integer> {

	@Override
	public Integer map(Character k) {
		return Integer.valueOf(k.charValue());
	}

}
