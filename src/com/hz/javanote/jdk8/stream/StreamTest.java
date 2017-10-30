package com.hz.javanote.jdk8.stream;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

public class StreamTest {

	/**
	 * 
	 * 
	 * 有多种方式生成 Stream Source： 从 Collection 和数组 Collection.stream()
	 * Collection.parallelStream() Arrays.stream(T array) or Stream.of() 从
	 * BufferedReader java.io.BufferedReader.lines() 静态工厂
	 * java.util.stream.IntStream.range() java.nio.file.Files.walk() 自己构建
	 * java.util.Spliterator 其它 Random.ints() BitSet.stream()
	 * Pattern.splitAsStream(java.lang.CharSequence) JarFile.stream()
	 * 
	 * @param <T>
	 * 
	 */
	public static void main(String[] args) {
		List<SteamDTO> stringList = Lists.newArrayList(new SteamDTO("1"), new SteamDTO("2"), new SteamDTO("3"), null,
				new SteamDTO("5"));
		testListFilter(stringList);

	}

	public static void testListFilter(List<SteamDTO> list) {
		Stream<SteamDTO> stream = list.parallelStream();
		long startTime=System.currentTimeMillis();
		long numCount=stream.filter(new Predicate<SteamDTO>() {
			public boolean test(SteamDTO t) {
				if (null == t) {
					return false;
				}
				return true;
			};
		}).count();
		long endTime=System.currentTimeMillis();
		System.out.println("parallelStream way "+(endTime-startTime)+"  numCount "+numCount);
		
		long oldWayCount=0;
		startTime=System.currentTimeMillis();
		for(SteamDTO dto:list){
			if(null!=dto){
				oldWayCount++;
			}
		}
		endTime=System.currentTimeMillis();
		System.out.println("oldWayCount way "+(endTime-startTime)+"  oldWayCount "+oldWayCount);
	}
}

class SteamDTO {

	public SteamDTO(String str, Integer num) {
		super();
		this.str = str;
		this.num = num;
	}

	public SteamDTO(Integer num) {
		super();
		this.num = num;
	}

	public SteamDTO(String str) {
		super();
		this.str = str;
	}

	public SteamDTO() {
		super();
	}

	private String str;
	private Integer num;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
