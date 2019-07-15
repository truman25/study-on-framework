package com.demo.designpatterns.FactoryMethod;

/**
 * 工厂方式另一种写法：可以通过反射的方式
 * @author jaydelano
 *
 */
public abstract class Factory2 {
	public abstract <T extends Product> T createProduct(Class<T> clz);
}
