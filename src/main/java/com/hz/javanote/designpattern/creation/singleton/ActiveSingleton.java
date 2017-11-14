package com.hz.javanote.designpattern.creation.singleton;

public class ActiveSingleton {
    /* 持有私有静态实例，防止被引用*/  
    private static ActiveSingleton instance = null;
    
    static{
    	 if (instance == null) {  
             instance = new ActiveSingleton();  
         }  
    }
  
    /* 私有构造方法，防止被实例化 */  
    private ActiveSingleton() {  
    }  
  
    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */  
    public Object readResolve() {  
        return instance;  
    }  
}
