package com.hz.javanote.loop;

public class LoopBreak {
	public static void main(String args[]) {
		Integer[] nums=new Integer[]{0,1,2,3,4,5,6,7,8,9};
		
		loop1:for(int num1:nums){
			loop2:for(int num2:nums){
				loop3:for(int num3:nums){
					if(num1==num2&&num2==num3&&num3==3){
						System.out.println(num1+" "+num2+" "+num3);
						break loop2;
					}else{
						System.out.println(num1+" "+num2+" "+num3);
					}
				}
			}
		}
	}
}
