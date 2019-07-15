package com.demo.designpatterns.Singleton;

/**
 * ˫�ؼ����-����ģʽ
 * @author jaydelano
 *
 */
public class DoubleCheckedLockingSingleton {

	/* ����˽�о�̬ʵ������ֹ�����ã��˴���ֵΪnull��Ŀ����ʵ���ӳټ��� */  
	/* volatile��java5�������Ա�֤���߳��µĿɼ���;
		��volatile��ÿ�����߳�ĳһ���Ҫ�õ�volatile����ʱ����������߳����¿���һ�ݣ������ͱ�֤���̵߳Ļ�����̵߳�һ�¡�
		дvolatile: ÿ�����߳�ĳһ���Ҫдvolatile����ʱ�������ڶ����ͬ�������߳�ȥ�������ͱ�֤���̵߳ı�����ʱ����*/
	private volatile static DoubleCheckedLockingSingleton instance = null;

	/* ˽�й��췽������ֹ��ʵ����������ʹ��new ����() */  
	private DoubleCheckedLockingSingleton() {
	}

	public static DoubleCheckedLockingSingleton getInstance() {
		if (instance == null) {
			synchronized (DoubleCheckedLockingSingleton.class) {
				if (instance == null) {
					instance = new DoubleCheckedLockingSingleton();
				}
			}
		}
		return instance;
	}

	/* ����ö����������л������Ա�֤���������л�ǰ�󱣳�һ�� */
	public Object readResolve() {
		return getInstance();
	}
	
}
