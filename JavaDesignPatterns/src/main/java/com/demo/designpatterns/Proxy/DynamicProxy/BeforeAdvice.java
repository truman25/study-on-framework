package com.demo.designpatterns.Proxy.DynamicProxy;

/**
 * ǰ��֪ͨ
 * 
 * @author jaydelano
 *
 */
public class BeforeAdvice implements IAdvice {
	// �ڱ�����ķ���ǰ��ִ�У��Ӷ��ﵽ��չ���ܡ�
	public void exec() {
		System.out.println("ǰ��֪ͨ��ִ�У�");
	}
}
