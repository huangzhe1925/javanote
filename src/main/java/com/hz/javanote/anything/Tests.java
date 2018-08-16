package com.hz.javanote.anything;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public class  Tests {

	public static final Map<String, List<String>> NIC_MODEL_TO_NIC_DRIVERS_MAP = Maps.newHashMap();

	static {
		NIC_MODEL_TO_NIC_DRIVERS_MAP.put("i350", Lists.newArrayList("NIC_DRIVER_IGBN"));
		NIC_MODEL_TO_NIC_DRIVERS_MAP.put("x520", Lists.newArrayList("NIC_DRIVER_IXGBEN"));
		NIC_MODEL_TO_NIC_DRIVERS_MAP.put("x540", Lists.newArrayList("NIC_DRIVER_IXGBEN"));
		NIC_MODEL_TO_NIC_DRIVERS_MAP.put("x550", Lists.newArrayList("NIC_DRIVER_IXGBEN"));
		NIC_MODEL_TO_NIC_DRIVERS_MAP.put("x710", Lists.newArrayList("NIC_DRIVER_I40EN"));
		NIC_MODEL_TO_NIC_DRIVERS_MAP.put("bcm57414", Lists.newArrayList("NIC_DRIVER_BNXTNET", "NIC_DRIVER_BNXTROCE"));
	}

	public static void main(String args[]) throws Exception {
		String testCase = "20";
		Class<?> threadClazz = Tests.class;
		Method method = threadClazz.getMethod("test" + testCase);
		method.invoke(method);
	}

	public static void test20() {
		Set<String> models = Sets.newHashSet("fdf");
		List<String> nicDriverTypes = Lists.newArrayList();
		if (CollectionUtils.isEmpty(models)) {
			return;
		}
		nicDriverTypes.addAll(models.stream().filter(model -> NIC_MODEL_TO_NIC_DRIVERS_MAP.containsKey(model))
				.map(model -> NIC_MODEL_TO_NIC_DRIVERS_MAP.get(model)).flatMap(dirverType -> dirverType.stream())
				.collect(Collectors.toList()));
		
		nicDriverTypes.stream().forEach(driver -> System.out.println(driver));
	}

	public static void test19() {
		Multimap<String, String> map = ArrayListMultimap.create();
	}

	public static void test18() {
		// Long durationMills=System.currentTimeMillis();
		Long durationMills = 6L;
		//
		// if (this.getTimeStampSet().isEmpty()) {
		// return "";
		// }
		// long minTimeStamp = this.getTimeStampSet().first();
		// long maxTimeStamp = this.getTimeStampSet().last();
		// long durationMills= maxTimeStamp - minTimeStamp;
		long durationInDay = TimeUnit.MILLISECONDS.toDays(durationMills);
		long durationInHour = TimeUnit.MILLISECONDS.toHours(durationMills) - TimeUnit.DAYS.toHours(durationInDay);
		long durationInMins = TimeUnit.MILLISECONDS.toMinutes(durationMills) - TimeUnit.DAYS.toMinutes(durationInDay)
				- TimeUnit.HOURS.toMinutes(durationInHour);
		long durationInSecs = TimeUnit.MILLISECONDS.toSeconds(durationMills) - TimeUnit.DAYS.toSeconds(durationInDay)
				- TimeUnit.HOURS.toSeconds(durationInHour) - TimeUnit.MINUTES.toSeconds(durationInMins);
		long durationInMillSecond = durationMills - TimeUnit.DAYS.toMillis(durationInDay)
				- TimeUnit.HOURS.toMillis(durationInHour) - TimeUnit.MINUTES.toMillis(durationInMins)
				- TimeUnit.SECONDS.toMillis(durationInSecs);

		System.out.println(durationInDay + " " + durationInHour + " " + durationInMins + " " + durationInSecs + " "
				+ durationInMillSecond);
	}

	public static void test17() {
		TreeSet<Integer> set = new TreeSet<>();
		set.add(5);
		set.add(4);
		set.add(10);
		System.out.println(set.first());
		System.out.println(set.last());
	}

	public static void test16() {
		String[] arr = "1,2,3,,5,,".split(",", -1);
		System.out.println(arr.length);
		for (String arrStr : arr) {
			System.out.println(arrStr);
		}
	}

	public static void test15() {
		String[] arr = "1,2,3,,5,,".split(",");
		System.out.println(arr.length);
		for (String arrStr : arr) {
			System.out.println(arrStr);
		}
	}

	public static synchronized void test14() {
		Map<Key, String> map = new HashMap<>();
		Key key1 = new Key();
		Key key2 = new Key();
		map.put(key1, "1");
		map.put(key2, "2");

		System.out.println(map.get(key2));
		test14();
	}

	public static void test13() {
		Set<Key> set = new HashSet<>();
		Key key1 = new Key("1");
		Key key2 = new Key("2");
		set.add(key1);
		set.add(key2);

		System.out.println(set.size());
	}

	public static class Key {

		private String keyString;

		public Key() {
		}

		public Key(String keyString) {
			this.keyString = keyString;
		}

		@Override
		public int hashCode() {
			return keyString.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return true;
		}
	}

	public static void test12() {
		String str1 = "1";
		String str2 = "1";
		String str3 = "12";
		String str4 = "2";
		System.out.println(str1 == str2);
		System.out.println(str1 == str2);
		System.out.println(str3 == (str1.concat("")));

	}

	public static void test11() {
		final ResourceBundle EVENT_KB_MAPPING_RESOURCE = ResourceBundle
				.getBundle("com.hz.javanote.anything.event-kb-mapping");

		if (EVENT_KB_MAPPING_RESOURCE.containsKey("MYSTIC018008")) {
			System.out.println("Value:" + EVENT_KB_MAPPING_RESOURCE.getString("MYSTIC018008") + ";");
		}

		if (EVENT_KB_MAPPING_RESOURCE.containsKey("MYSTIC018016")) {
			System.out.println("Value:" + EVENT_KB_MAPPING_RESOURCE.getString("MYSTIC018008") + ";");
		}
	}

	public static void test10() {
		while (true) {
			try {
				Thread.sleep(5000);
				System.out.println("waiting");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void test9() {
		final ImmutableSet<String> set = ImmutableSet.<String>builder().add("test").build();
		// .add("Dell Express Flash PM1725a 800GB SFF")
		// .add("Dell Express Flash PM1725a 1.6TB SFF")
		// .build();
	}

	public static void test8() {
		Object obj = null;
		if (obj == null) {
			System.out.println("not null");
		}
	}

	public static void test7() {
		Integer num = 100;
		ValueDown1(num);
		System.out.println(num);
	}

	public static void ValueDown1(Integer num) {
		num = new Integer("101");
		System.out.println(num);

	}

	public static void test6() {
		String strs[] = new String[] { "111s", "1", "111.2", "111s", "111.s", "1s11" };

		for (String str : strs) {
			boolean isNum = str.matches("^[+-]?([0-9]+)\\.?(([0-9]+)?)");
			System.out.println(str + ":" + isNum);
		}
	}

	public static void test0() {
		// System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
		// System.out.println(Integer.MAX_VALUE);
		// Integer out=Integer.MAX_VALUE+1;
		// System.out.println(Integer.toBinaryString(out));
		// System.out.println(out);

		// List<Map<String,String>> list2=new ArrayList();
		// list2.add();
		// list2.add("1234");
		// list2.remove(0);
		// System.out.println(list2);
		//
		// List<String> list3=Collections.unmodifiableList(list2);
		// list3.remove(0);
		// System.out.println(list3);
		final Integer[] arr = new Integer[] { 1, 2, 3, 4 };
		arr[0]++;
		System.out.println(Arrays.asList(arr));
		// arr=new Integer[]{1,2,3,4,5};
		// System.out.println(Arrays.asList(arr));

		// List<String> list1=Arrays.asList();
		// list1.remove(0);
		// System.out.println(list1);
	}

	public static void test1() {
		System.out.println(Boolean.TRUE.toString());
		TestClause tc = new TestClause() {
			// this method is useless, thinking how to use it
			private void print() {
				System.out.println("print");
			}
		};
	}

	public static void test2() {
		HashMap map = new HashMap();
		Object obj = Long.valueOf("0");
		map.put("1", obj);
		obj = ((Long) obj) + 3;
		map.put("1", obj);
		System.out.println(map.get("1"));
	}

	public static void test3() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(1951, 0, 1, 0, 0, 0);
		System.out.println(new Date(cal.getTimeInMillis()));
	}

	public static void test4() {
		Object o1 = null;
		Long l1 = (Long) o1;
	}

	public static void test5() {
		int cards = 13;
		int len = 1;
		int[] cardsArr = new int[cards];
		cardsArr[0] = 1;

		int count = 0;
		int nowCard = 2;
		int stepCount = 0;
		while (nowCard <= cards) {
			while (0 != cardsArr[count % cards] || stepCount < len) {
				if (0 == cardsArr[count % cards]) {
					stepCount++;
				}
				count++;
			}
			cardsArr[count % cards] = nowCard;
			stepCount = 0;
			nowCard++;
		}
		System.out.println(Arrays.toString(cardsArr));
	}

}

class TestClause {
	public TestClause() {
		System.out.println("Initial");
	}

	private void privateMethod() {
		System.out.println("privateMethod");
	}
}