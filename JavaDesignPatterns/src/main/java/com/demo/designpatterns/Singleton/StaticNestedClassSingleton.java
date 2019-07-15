package com.demo.designpatterns.Singleton;

/**
 * 静态内部类-单例模式（推荐）
 * @author jaydelano
 *
 */
public class StaticNestedClassSingleton {

	/* 私有构造方法，防止被实例化 */  
	private StaticNestedClassSingleton() {
	}

	/* 此处使用一个内部类来维护单例 */  
	private static class SingletonFactory {
		private static StaticNestedClassSingleton instance = new StaticNestedClassSingleton();
	}

	public static StaticNestedClassSingleton getInstance() {
		return SingletonFactory.instance;
	}
	
	/* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
	public Object readResolve() {
		return getInstance();
	}

}
