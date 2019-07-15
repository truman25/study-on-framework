package com.demo.designpatterns.Proxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ��̬����ģʽ
 * 
 * @author jaydelano
 *
 */
public class DynamicProxyPattern {

	/*
	 * ����java�ײ��װ��ʵ��ϸ�ڣ����Դ���ǳ��򵥣���ʽҲ�����Ϲ̶� 
	 * ����Proxy��ľ�̬����newProxyInstance���ɣ��÷����᷵�ش��������
	 * ���յ�������������Ϊ: 
	 * ClassLoader loader��ָ����ǰĿ�����ʹ�����������д���̶� Class<?>[]
	 * interfaces��Ŀ�����ʵ�ֵĽӿڵ����ͣ�д���̶� 
	 * InvocationHandler h���¼�����ӿڣ��贫��һ��ʵ���࣬һ��ֱ��ʹ�������ڲ���
	 */

	/**
	 * ��ʽһ
	 * 
	 * @param args
	 */
	public static void main1(String[] args) {
		Singer target = new Singer();
		ISinger proxy = (ISinger) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("������ʺ�");
						// ִ��Ŀ����󷽷�
						Object returnValue = method.invoke(target, args);
						System.out.println("лл���");
						return returnValue;
					}
				});
		proxy.sing();
	}

	/**
	 * ��ʽ��
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ISinger service = new Singer();
		Class<? extends ISinger> clazz = service.getClass();
		ISinger proxyService = (ISinger) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
				new ServiceInvocationHandler(service));
		proxyService.sing();
	}

}
