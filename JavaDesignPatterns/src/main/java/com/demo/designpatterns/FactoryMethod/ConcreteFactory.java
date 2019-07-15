package com.demo.designpatterns.FactoryMethod;

public class ConcreteFactory extends Factory2 {

	/**
	 * ������ʽ��һ��д��������ͨ������ķ�ʽ
	 * �ŵ㣺������Ʒʱ���������½�һ������ʵ���ֻ࣬��Ҫһ��ͨ�õĹ���ʵ���༴��
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Product> T createProduct(Class<T> clz) {
		Product  product = null;
        try {
            product= (Product) Class.forName(clz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) product;
	}

}
