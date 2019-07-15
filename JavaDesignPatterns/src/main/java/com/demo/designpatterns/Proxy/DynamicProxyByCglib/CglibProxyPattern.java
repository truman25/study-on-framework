package com.demo.designpatterns.Proxy.DynamicProxyByCglib;

import com.demo.designpatterns.Proxy.DynamicProxy.Singer;

/**
 * cglib动态代理模式
 * 
 * 不能对final修饰的类进行代理
 * 
 * @author jaydelano
 *
 */
public class CglibProxyPattern {

	public static void main(String[] args) {
		// 目标对象
		Singer target = new Singer();
		// 代理对象
		Singer proxy = (Singer) new CglibProxyFactory(target).getProxyInstance();
		// 执行代理对象的方法
		proxy.sing();
	}
}
