package com.demo.designpatterns.Proxy.DynamicProxy;

/**
 * 后置通知
 * 
 * @author jaydelano
 *
 */
public class AfterAdvice implements IAdvice {

	// 在被代理的方法后来执行，从而达到扩展功能。
	public void exec() {
		System.out.println("后置通知被执行！");
	}
}
