package com.hz.javanote.innerclass;

public interface ClassInInterface {
	void howdy();
	class Test implements ClassInInterface{
		public void howdy(){
			System.out.println("ClassInInterface");
		}
		public static void main(String args[]){
			new Test().howdy();
		}
	}
}
