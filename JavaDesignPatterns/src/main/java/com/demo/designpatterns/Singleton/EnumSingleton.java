package com.demo.designpatterns.Singleton;

/**
 * ö��-����ģʽ�������ڶ���ģʽ�����ȶ�ģʽ�򵥵Ķ࣬��Ȼ�̰߳�ȫ��ȱ�����ģʽ��ͬ���ڷ������أ�
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
