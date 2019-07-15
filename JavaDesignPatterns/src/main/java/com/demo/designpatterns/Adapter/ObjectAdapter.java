package com.demo.designpatterns.Adapter;

/**
 * 对象适配器模式
 * @author jaydelano
 *
 */
public class ObjectAdapter implements Target{

	private Adaptee adaptee;

	public ObjectAdapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }

	/**
	 * 源类Adaptee有方法sampleOperation1 因此适配器类直接委派即可
	 */
	@Override
	public void sampleOperation1() {
		this.adaptee.sampleOperation1();
	}

	/**
	 * 源类Adaptee没有方法sampleOperation2 因此由适配器类需要补充此方法
	 */
	@Override
	public void sampleOperation2() {
		// 写相关的代码
		System.out.println("===========ObjectAdapter sampleOperation2 ");
	}
}
