package com.demo.designpatterns.FactoryMethod;

/**
 * ������ʽ��һ��д��������ͨ������ķ�ʽ
 * @author jaydelano
 *
 */
public abstract class Factory2 {
	public abstract <T extends Product> T createProduct(Class<T> clz);
}
