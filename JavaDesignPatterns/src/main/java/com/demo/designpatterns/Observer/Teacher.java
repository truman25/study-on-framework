package com.demo.designpatterns.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * ConcreteSubject(����Ŀ�꣬Teacher) ���й�״̬�����ConcreteObserve����
 * ������״̬�����ı�ʱ�������ĸ����۲��߷���֪ͨ��
 */
public class Teacher implements Subject {

	private String phone;
	private List<Observer> students;

	public Teacher() {
		phone = "";
		students = new ArrayList<Observer>();
	}

	@Override
	public void attach(Observer mObserver) {
		students.add(mObserver);
	}

	@Override
	public void detach(Observer mObserver) {
		students.remove(mObserver);
	}

	@Override
	public void notice() {
		for (int i = 0; i < students.size(); i++) {
			((Observer) students.get(i)).update();
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
		notice();
	}

}
