package com.demo.designpatterns.Adapter;

/**
 * 类适配器模式
 * @author jaydelano
 *
 */
public class ClassAdapter extends Adaptee implements Target {

	/**
	 * 由于源类Adaptee没有方法sampleOperation2() 因此适配器补充上这个方法
	 */
	@Override
	public void sampleOperation2() {
		// 写相关的代码
		System.out.println("===========ClassAdapter sampleOperation2 ");
	}

}
