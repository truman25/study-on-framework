package com.demo.designpatterns.Proxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理模式
 * 
 * @author jaydelano
 *
 */
public class DynamicProxyPattern {

	/*
	 * 由于java底层封装了实现细节，所以代码非常简单，格式也基本上固定 
	 * 调用Proxy类的静态方法newProxyInstance即可，该方法会返回代理类对象
	 * 接收的三个参数依次为: 
	 * ClassLoader loader：指定当前目标对象使用类加载器，写法固定 Class<?>[]
	 * interfaces：目标对象实现的接口的类型，写法固定 
	 * InvocationHandler h：事件处理接口，需传入一个实现类，一般直接使用匿名内部类
	 */

	/**
	 * 方式一
	 * 
	 * @param args
	 */
	public static void main1(String[] args) {
		Singer target = new Singer();
		ISinger proxy = (ISinger) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("向观众问好");
						// 执行目标对象方法
						Object returnValue = method.invoke(target, args);
						System.out.println("谢谢大家");
						return returnValue;
					}
				});
		proxy.sing();
	}

	/**
	 * 方式二
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
