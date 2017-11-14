package com.hz.javanote.designpattern.structure.adapter;

import javax.transaction.NotSupportedException;

public abstract class WrapperAbstractClass implements Sourceable{

	@Override
	public void method1() {
		try{
			throw new NotSupportedException("NotSupported method1");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void method2() {
		try{
			throw new NotSupportedException("NotSupported method2");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
