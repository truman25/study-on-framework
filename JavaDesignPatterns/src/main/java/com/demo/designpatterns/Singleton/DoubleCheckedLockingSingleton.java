package com.demo.designpatterns.Singleton;

/**
 * 双重检查锁-单例模式
 * @author jaydelano
 *
 */
public class DoubleCheckedLockingSingleton {

	/* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */  
	/* volatile（java5）：可以保证多线程下的可见性;
		读volatile：每当子线程某一语句要用到volatile变量时，都会从主线程重新拷贝一份，这样就保证子线程的会跟主线程的一致。
		写volatile: 每当子线程某一语句要写volatile变量时，都会在读完后同步到主线程去，这样就保证主线程的变量及时更新*/
	private volatile static DoubleCheckedLockingSingleton instance = null;

	/* 私有构造方法，防止被实例化，不能使用new 类名() */  
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

	/* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
	public Object readResolve() {
		return getInstance();
	}
	
}
