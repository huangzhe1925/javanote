package com.hz.javanote.designpattern.behavior.templatemethod;

public class StrategyTestMulti {  
	  
	 public static void main(String[] args) {  
	        String exp = "8add1min5mul2div2";  
	        AbstractCalculator[] cals = new AbstractCalculator[]{new Plus(),new Minus(),new Multi(),new Divide()};
	        String[] opts=new String[]{"add","min","mul","div"};
	        
	        CalculatorMulti calMulti=new CalculatorMulti();
	        calMulti.setCalculators(cals);
	        calMulti.setOpts(opts);
	        int result=calMulti.calculate(exp);
	        
	        System.out.println(result);  
	    }  
}  