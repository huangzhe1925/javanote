package com.hz.javanote.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * �����û��ʵ�ֽӿ�
 * 
 * @author student
 * 
 */
interface BookFacadeCG {
	public void addBook();
}

/**
 * �����û��ʵ�ֽӿڵ�ʵ����
 * 
 * @author student
 * 
 */
class BookFacadeImpl1 {
	public void addBook() {
		System.out.println("����ͼ�����ͨ����...");
	}
}

class BookFacadeCglib implements MethodInterceptor {
	private Object target;

	/**
	 * �����������
	 * 
	 * @param target
	 * @return
	 */
	public Object getInstance(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		// �ص�����
		enhancer.setCallback(this);
		// �����������
		return enhancer.create();
	}

	@Override
	// �ص�����
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("���￪ʼ");
		proxy.invokeSuper(obj, args);
		System.out.println("�������");
		return null;
	}

}

public class TestDynamicProxyCgLib {

	public static void main(String[] args) {
		BookFacadeCglib cglib = new BookFacadeCglib();
		BookFacadeImpl1 bookCglib = (BookFacadeImpl1) cglib
				.getInstance(new BookFacadeImpl1());
		bookCglib.addBook();
	}

}
