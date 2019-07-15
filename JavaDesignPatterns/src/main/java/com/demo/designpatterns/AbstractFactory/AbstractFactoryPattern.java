package com.demo.designpatterns.AbstractFactory;

/**
 * 抽象工厂模式
 * 
 * 工厂类、产品类，可以用抽象类也可以用接口
 * 
 * @author jaydelano
 *
 */
public class AbstractFactoryPattern {

	public static void main(String[] args) {
		// A车厂
		CarFactory factoryQ3 = new Q3Factory();
		factoryQ3.createTire().tire();
		factoryQ3.createEngine().engine();
		factoryQ3.createBrake().brake();
	}
}
