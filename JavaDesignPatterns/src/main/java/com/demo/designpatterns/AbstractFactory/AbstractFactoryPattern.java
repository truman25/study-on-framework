package com.demo.designpatterns.AbstractFactory;

/**
 * ���󹤳�ģʽ
 * 
 * �����ࡢ��Ʒ�࣬�����ó�����Ҳ�����ýӿ�
 * 
 * @author jaydelano
 *
 */
public class AbstractFactoryPattern {

	public static void main(String[] args) {
		// A����
		CarFactory factoryQ3 = new Q3Factory();
		factoryQ3.createTire().tire();
		factoryQ3.createEngine().engine();
		factoryQ3.createBrake().brake();
	}
}
