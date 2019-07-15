package com.demo.designpatterns.Proxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ServiceInvocationHandler implements InvocationHandler {

	/**
	 * ������Ķ���
	 */
	private Object srcObject;

	public ServiceInvocationHandler(Object srcObject) {
		this.srcObject = srcObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("������ʺ�");

		// ��ִ�з���ǰ��ִ��ǰ��֪ͨ��
		IAdvice beforeAdvice = new BeforeAdvice();
		beforeAdvice.exec();
		// ִ��Ŀ����󷽷�
		Object returnValue = method.invoke(srcObject, args);
		// ��ִ�з�����ִ�к���֪ͨ��
		IAdvice afterAdvice = new AfterAdvice();
		afterAdvice.exec();
		// ǰ��֪ͨ���ͺ���֪ͨ������Ҫ������ʵ�ʵ�ҵ��������������ӡ�
		
		System.out.println("лл���");
		return returnValue;
	}

}
