package com.demo.designpatterns.Proxy.DynamicProxyByCglib;

import com.demo.designpatterns.Proxy.DynamicProxy.Singer;

/**
 * cglib��̬����ģʽ
 * 
 * ���ܶ�final���ε�����д���
 * 
 * @author jaydelano
 *
 */
public class CglibProxyPattern {

	public static void main(String[] args) {
		// Ŀ�����
		Singer target = new Singer();
		// �������
		Singer proxy = (Singer) new CglibProxyFactory(target).getProxyInstance();
		// ִ�д������ķ���
		proxy.sing();
	}
}
