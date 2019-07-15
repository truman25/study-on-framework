package com.demo.designpatterns.Adapter;

/**
 * ����������ģʽ
 * @author jaydelano
 *
 */
public class ObjectAdapter implements Target{

	private Adaptee adaptee;

	public ObjectAdapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }

	/**
	 * Դ��Adaptee�з���sampleOperation1 �����������ֱ��ί�ɼ���
	 */
	@Override
	public void sampleOperation1() {
		this.adaptee.sampleOperation1();
	}

	/**
	 * Դ��Adapteeû�з���sampleOperation2 ���������������Ҫ����˷���
	 */
	@Override
	public void sampleOperation2() {
		// д��صĴ���
		System.out.println("===========ObjectAdapter sampleOperation2 ");
	}
}
