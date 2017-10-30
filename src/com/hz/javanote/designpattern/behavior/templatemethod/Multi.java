package com.hz.javanote.designpattern.behavior.templatemethod;


public class Multi extends AbstractCalculator {  
	  
    @Override  
    public int calculate(int[] nums) {
    	int result=nums[0];
    	
    	for(int index=1;index<nums.length;index++){
    		result*=nums[index];
    	}
        return result;  
    }    
}  