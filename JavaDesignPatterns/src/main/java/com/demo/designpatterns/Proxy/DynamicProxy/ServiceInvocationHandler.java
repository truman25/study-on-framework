package com.demo.designpatterns.Proxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ServiceInvocationHandler implements InvocationHandler {

	/**
	 * 被代理的对象
	 */
	private Object srcObject;

	public ServiceInvocationHandler(Object srcObject) {
		this.srcObject = srcObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("向观众问好");

		// 在执行方法前，执行前置通知。
		IAdvice beforeAdvice = new BeforeAdvice();
		beforeAdvice.exec();
		// 执行目标对象方法
		Object returnValue = method.invoke(srcObject, args);
		// 在执行方法后，执行后置通知。
		IAdvice afterAdvice = new AfterAdvice();
		afterAdvice.exec();
		// 前置通知，和后置通知，都是要看具体实际的业务需求来进行添加。
		
		System.out.println("谢谢大家");
		return returnValue;
	}

}
