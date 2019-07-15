package com.demo.designpatterns.Adapter;

/**
 * 适配器模式
 * @author jaydelano
 *
 */
public class AdapterPattern {

	/**
	 * 类适配器模式
	 * @param args
	 */
	public static void main1(String[] args) {
        Target targetable = new ClassAdapter();
        targetable.sampleOperation1();
        targetable.sampleOperation2();
    }
	
	/**
	 * 对象适配器模式(推荐)
	 * @param args
	 */
	public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target targetable = new ObjectAdapter(adaptee);
        targetable.sampleOperation1();
        targetable.sampleOperation2();
    }
	
}
