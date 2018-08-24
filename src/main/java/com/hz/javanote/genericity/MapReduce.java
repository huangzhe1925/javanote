package com.hz.javanote.genericity;

import java.util.List;

public interface MapReduce<K,V> {
	V process(List<K> list);
}
