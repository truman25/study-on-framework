package com.demo.designpatterns.Decorator;

/**
 * 装饰者模式
 * @author jaydelano
 *
 */
public class DecoratorPattern {

	public static void main(String[] args) {
		
        Person mPerson = new Person("Andy");
        Jeans mJeans = new Jeans();
        mJeans.decoratorObj(mPerson);
        mJeans.show(); 
    }
}
