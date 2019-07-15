package com.demo.designpatterns.Adapter;

/**
 * ������ģʽ
 * @author jaydelano
 *
 */
public class AdapterPattern {

	/**
	 * ��������ģʽ
	 * @param args
	 */
	public static void main1(String[] args) {
        Target targetable = new ClassAdapter();
        targetable.sampleOperation1();
        targetable.sampleOperation2();
    }
	
	/**
	 * ����������ģʽ(�Ƽ�)
	 * @param args
	 */
	public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target targetable = new ObjectAdapter(adaptee);
        targetable.sampleOperation1();
        targetable.sampleOperation2();
    }
	
}
