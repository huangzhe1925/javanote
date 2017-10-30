package com.hz.javanote.excptions;

public class TestExceptionAndReturn {
	public static void main(String[] args) {

		System.out.println(testRunTimeException());
	}

	public static String testRunTimeException() {

		String retString = null;
		try {
			throw new RuntimeException("ex");
		} catch (Exception e) {
			retString = "Exception";
		}
		System.out.println(retString);
		
		retString = "content";

		return retString;

	}

}
