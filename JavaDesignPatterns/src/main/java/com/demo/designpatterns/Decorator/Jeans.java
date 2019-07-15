package com.demo.designpatterns.Decorator;

/**
 * 具体的装饰类
 * @author jaydelano
 *
 */
public class Jeans extends Decorator {

	@Override
    public void show(){
        System.out.println("穿牛仔裤");
        super.show();
    }
}
