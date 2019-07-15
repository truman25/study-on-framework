package com.demo.designpatterns.Observer;

/**
 * Subject(目标，Subject)： 目标知道它的观察者。可以有任意多个观察者观察同一个目标。 提供注册和删除观察者对象的接口。
 */
public interface Subject {
	
	public void attach(Observer mObserver);

	public void detach(Observer mObserver);

	public void notice();
	
}
