package com.demo.designpatterns.Adapter;

/**
 * ��������ģʽ
 * @author jaydelano
 *
 */
public class ClassAdapter extends Adaptee implements Target {

	/**
	 * ����Դ��Adapteeû�з���sampleOperation2() ����������������������
	 */
	@Override
	public void sampleOperation2() {
		// д��صĴ���
		System.out.println("===========ClassAdapter sampleOperation2 ");
	}

}
