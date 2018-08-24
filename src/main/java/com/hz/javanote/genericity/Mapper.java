package com.hz.javanote.genericity;

public interface Mapper<K, V> {
	V map(K k);
}
