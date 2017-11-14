package com.hz.javanote.proxy;

/**
 * ����һ���˻��ӿ�
 * 
 * @author Administrator
 * 
 */
interface Count {
	// �鿴�˻�����
	public void queryCount();

	// �޸��˻�����
	public void updateCount();

}

/**
 * ί����(����ҵ���߼�)
 * 
 * @author Administrator
 * 
 */
class CountImpl implements Count {

	@Override
	public void queryCount() {
		System.out.println("�鿴�˻�����...");

	}

	@Override
	public void updateCount() {
		System.out.println("�޸��˻�����...");

	}

}

/**
 * ����һ�������ࣨ��ǿCountImplʵ���ࣩ
 * 
 * @author Administrator
 * 
 */
class CountProxy implements Count {
	private CountImpl countImpl;

	/**
	 * ����Ĭ�Ϲ�����
	 * 
	 * @param countImpl
	 */
	public CountProxy(CountImpl countImpl) {
		this.countImpl = countImpl;
	}

	@Override
	public void queryCount() {
		System.out.println("������֮ǰ");
		// ����ί����ķ���;
		countImpl.queryCount();
		System.out.println("������֮��");
	}

	@Override
	public void updateCount() {
		System.out.println("������֮ǰ");
		// ����ί����ķ���;
		countImpl.updateCount();
		System.out.println("������֮��");

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
