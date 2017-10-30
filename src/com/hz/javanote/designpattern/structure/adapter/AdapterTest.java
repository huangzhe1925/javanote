package com.hz.javanote.designpattern.structure.adapter;

public class AdapterTest {  
	  
    public static void main(String[] args) {  
    	
    	//类的适配器模式
        Targetable target = new ClassAdapter();  
        target.method1();  
        target.method2();  
        
        //对象的适配器模式，也是 装饰模式、代理模式、外观模式、桥接模式、组合模式、享元模式的起源
        Source source = new Source();  
        Targetable target1 = new WrapperAdapter(source);  
        target1.method1();  
        target1.method2();  
        
        //接口的适配器模式
        Sourceable source1 = new SourceSub1();  
        Sourceable source2 = new SourceSub2();  
          
        source1.method1();  
        source1.method2();  
        source2.method1();  
        source2.method2();
    }
    
}  