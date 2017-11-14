package com.hz.javanote.designpattern.behavior.templatemethod;

public abstract class AbstractCalculator {  
    
    /*主方法，实现对本类其它方法的调用*/  
    public final int calculate(String exp,String opt){
        int array[] = split(exp,opt);  
        if(array.length==1){
        	return array[0];
        }
        return calculate(array);  
    }  
      
    /*被子类重写的方法*/  
    abstract public int calculate(int[] nums);  
      
    public int[] split(String exp,String opt){  
        String array[] = exp.split(opt);
        int arrayInt[] = new int[array.length];
        for(int index=0;index<array.length;index++){
        	arrayInt[index]=Integer.parseInt(array[index]);
        }
        return arrayInt;  
    }  
}  