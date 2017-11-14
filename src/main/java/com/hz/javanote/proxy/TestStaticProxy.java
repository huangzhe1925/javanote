package com.hz.javanote.proxy;

/**
 * 定义一个账户接口
 * 
 * @author Administrator
 * 
 */
interface Count {
	// 查看账户方法
	public void queryCount();

	// 修改账户方法
	public void updateCount();

}

/**
 * 委托类(包含业务逻辑)
 * 
 * @author Administrator
 * 
 */
class CountImpl implements Count {

	@Override
	public void queryCount() {
		System.out.println("查看账户方法...");

	}

	@Override
	public void updateCount() {
		System.out.println("修改账户方法...");

	}

}

/**
 * 这是一个代理类（增强CountImpl实现类）
 * 
 * @author Administrator
 * 
 */
class CountProxy implements Count {
	private CountImpl countImpl;

	/**
	 * 覆盖默认构造器
	 * 
	 * @param countImpl
	 */
	public CountProxy(CountImpl countImpl) {
		this.countImpl = countImpl;
	}

	@Override
	public void queryCount() {
		System.out.println("事务处理之前");
		// 调用委托类的方法;
		countImpl.queryCount();
		System.out.println("事务处理之后");
	}

	@Override
	public void updateCount() {
		System.out.println("事务处理之前");
		// 调用委托类的方法;
		countImpl.updateCount();
		System.out.println("事务处理之后");

	}

}

public class TestStaticProxy {

	public static void main(String[] args) {
		CountImpl countImpl = new CountImpl();
		CountProxy countProxy = new CountProxy(countImpl);
		countProxy.updateCount();
		countProxy.queryCount();
	}

}
