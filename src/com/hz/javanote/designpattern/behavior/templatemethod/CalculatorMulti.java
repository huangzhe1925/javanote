package com.hz.javanote.designpattern.behavior.templatemethod;

public class CalculatorMulti {
	
	private AbstractCalculator[] calculators;
	private String[] opts;
	   
    public int calculate(String exp){   	
    	String result=calculate(exp,0);
    	
    	return Integer.parseInt(result);
    }  
    
    public String calculate(String exp,int optIndex){
        if(optIndex==opts.length-1){
        	return calculators[optIndex].calculate(exp, opts[optIndex])+"";
        }
    	
    	String array[] = exp.split(opts[optIndex]);
    	String nowExp="";
    	for(String arrStr:array){
    		nowExp=nowExp+calculate(arrStr,optIndex+1)+opts[optIndex];
    	}
    	
    	if(nowExp.endsWith(opts[optIndex])){
    		nowExp=nowExp.substring(0, nowExp.lastIndexOf(opts[optIndex]));
    	}
        
        return calculators[optIndex].calculate(nowExp, opts[optIndex])+"";
    }  

    
	public AbstractCalculator[] getCalculators() {
		return calculators;
	}

	public void setCalculators(AbstractCalculator[] calculators) {
		this.calculators = calculators;
	}

	public String[] getOpts() {
		return opts;
	}

	public void setOpts(String[] opts) {
		this.opts = opts;
	}
}
