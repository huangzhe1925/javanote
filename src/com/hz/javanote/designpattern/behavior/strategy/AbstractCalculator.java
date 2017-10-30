package com.hz.javanote.designpattern.behavior.strategy;

public abstract class AbstractCalculator implements ICalculator{  
	
	
	public int[] split(String exp,String seprator){
		 
		String[] strsArr=exp.split(seprator);
		if(strsArr.length!=2){
			return null;
		}
		int[] intArr=new int[]{Integer.parseInt(strsArr[0]),Integer.parseInt(strsArr[1])};
		
		return intArr;
	}  
}  