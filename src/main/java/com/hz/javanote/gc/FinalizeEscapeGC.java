package com.hz.javanote.gc;

/**
 * �˴�����ʾ������
 * 1.��������ڱ�GCʱ��������
 * 2.�����ԾȵĻ���ֻ��һ�Σ���Ϊһ�������finalize()�������ֻ�ᱻϵͳ�Զ�����һ��
 * @author roger
 *
 */
public class FinalizeEscapeGC {
	
	public static FinalizeEscapeGC SAVE_HOOK=null;

	public void isAlive(){
		System.out.println("Yes I am still alive :)");
	} 
	
	@Override
	protected void finalize() throws Throwable{
		super.finalize();
		System.out.println("finalize method executed");
		FinalizeEscapeGC.SAVE_HOOK=this;
	}
	
	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK=new FinalizeEscapeGC();
		
		//�����һ�γɹ������Լ�
		SAVE_HOOK=null;
		System.gc();
		//��Ϊfinalize�������ȼ��ܵͣ�������ͣ0.5���Եȴ���
		Thread.sleep(500);
		if(SAVE_HOOK !=null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("No I am dead :(");
		}
		
		//��Ϊ�Ѿ�ִ����finalize()������Ծ�ʧ����
		SAVE_HOOK=null;
		System.gc();
		Thread.sleep(500);
		if(SAVE_HOOK !=null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("No I am dead :(");
		}
	}

}