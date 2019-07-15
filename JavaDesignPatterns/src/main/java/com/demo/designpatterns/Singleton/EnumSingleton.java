package com.demo.designpatterns.Singleton;

/**
 * 枚举-单例模式（类似于饿汉模式，但比恶汉模式简单的多，天然线程安全，缺点跟恶汉模式相同属于非懒加载）
 * @author jaydelano
 *
 */
public enum EnumSingleton {
	INSTANCE;
}

class SingletonTest {
	public static void main(String[] args) {
		EnumSingleton s = EnumSingleton.INSTANCE;
		EnumSingleton s2 = EnumSingleton.INSTANCE;
		System.out.println(s == s2);
	}
}
