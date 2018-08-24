package com.hz.javanote.genericity;

import java.util.List;
import java.util.stream.Collectors;

public class MapReducer<K, V> implements MapReduce<K, V> {
	private Mapper<K, V> mapper;
	private Reducer<V> reducer;

	MapReducer(Mapper<K, V> mapper, Reducer<V> reducer) {
		this.mapper = mapper;
		this.reducer = reducer;
	}
	
	@Override
	public V process(List<K> list) {
		List<V> vList = list.stream().map(item -> this.mapper.map(item)).collect(Collectors.toList());
		V reducedV = vList.stream().reduce((result, item) -> this.reducer.reduce(result, item)).get();
		return reducedV;
	}
	
}
