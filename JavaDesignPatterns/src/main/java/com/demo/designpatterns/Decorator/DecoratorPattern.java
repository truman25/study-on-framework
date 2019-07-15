package com.demo.designpatterns.Decorator;

/**
 * װ����ģʽ
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
