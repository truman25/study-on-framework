package com.demo.designpatterns.Proxy.DynamicProxy;

/**
 * ����֪ͨ
 * 
 * @author jaydelano
 *
 */
public class AfterAdvice implements IAdvice {

	// �ڱ�����ķ�������ִ�У��Ӷ��ﵽ��չ���ܡ�
	public void exec() {
		System.out.println("����֪ͨ��ִ�У�");
	}
}
