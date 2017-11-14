package com.hz.javanote.anything;

public class NewClass {
	public static void main(String[] args) {
		new NewClassExtend().todo();
	}
	
	public static class NewClassExtend extends OriginClass{
		public void todo() {
			super.todo();
			System.out.println("new todo");
		}
		
	}

	public static class OriginClass {
		private void todo() {
			System.out.println("todo");
		}
	}

}
