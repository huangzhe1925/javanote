package com.hz.javanote.designpattern.structure.proxy;

public class ProxyTest {
	public static void main(String[] args) {  
        Sourceable source = new Proxy();
        source.method();
    }
}
