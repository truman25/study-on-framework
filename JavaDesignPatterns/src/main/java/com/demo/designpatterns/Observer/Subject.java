package com.demo.designpatterns.Observer;

/**
 * Subject(Ŀ�꣬Subject)�� Ŀ��֪�����Ĺ۲��ߡ��������������۲��߹۲�ͬһ��Ŀ�ꡣ �ṩע���ɾ���۲��߶���Ľӿڡ�
 */
public interface Subject {
	
	public void attach(Observer mObserver);

	public void detach(Observer mObserver);

	public void notice();
	
}
