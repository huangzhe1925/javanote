package com.hz.javanote.designpattern.behavior.templatemethod;

public class Plus extends AbstractCalculator {  
	  
    public int calculate(int[] nums) {
    	int result=0;
    	for(int num:nums){
    		result+=num;
    	}
        return result;  
    }  
}  