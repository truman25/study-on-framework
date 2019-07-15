package com.demo.designpatterns.FactoryMethod;

/**
 * ��������ģʽ
 * 
 * �����ࡢ��Ʒ�࣬�����ó�����Ҳ�����ýӿ�
 * 
 * @author jaydelano
 *
 */
public class FactoryMethodPattern {
	public static void main(String[] args){
		//��ʽһ
        //�ͻ�Ҫ��ƷA
        FactoryA mFactoryA = new FactoryA();
        mFactoryA.Manufacture().Show();
 
        //�ͻ�Ҫ��ƷB
        FactoryB mFactoryB = new FactoryB();
        mFactoryB.Manufacture().Show();
        
        //��ʽ��
        //������ʽ��һ��д��������ͨ������ķ�ʽ
        //�ŵ㣺������Ʒʱ���������½�һ������ʵ���ֻ࣬��Ҫһ��ͨ�õĹ���ʵ���༴��
        Factory2 factory2 = new ConcreteFactory();
        Product product = factory2.createProduct(ProductA.class);
        product.Show();
    }

}
