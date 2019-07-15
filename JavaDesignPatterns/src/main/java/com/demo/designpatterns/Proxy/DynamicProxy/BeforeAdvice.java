package com.demo.designpatterns.Proxy.DynamicProxy;

/**
 * 前置通知
 * 
 * @author jaydelano
 *
 */
public class BeforeAdvice implements IAdvice {
	// 在被代理的方法前来执行，从而达到扩展功能。
	public void exec() {
		System.out.println("前置通知被执行！");
	}
}
