package com.hz.javanote.designpattern.behavior.templatemethod;

public class Minus extends AbstractCalculator {  
	  
    @Override  
    public int calculate(int[] nums) {
    	int result=0;
    	for(int num:nums){
    		result-=num;
    	}
        return result;  
    }  
}  