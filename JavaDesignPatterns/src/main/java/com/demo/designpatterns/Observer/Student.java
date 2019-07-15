package com.demo.designpatterns.Observer;

/**
 * ConcreteObserver(����۲���, Student)�� ά��һ��ָ��ConcreteSubject��������á�
 * �洢�й�״̬����Щ״̬Ӧ��Ŀ���״̬����һ�¡� ʵ��Observer�ĸ��½ӿ���ʹ����״̬��Ŀ���״̬����һ�¡�
 */
public class Student implements Observer {

	private String name;
	private String phone;
	private Teacher mTeacher;

	public Student(String name, Teacher t) {
		this.name = name;
		mTeacher = t;
	}

	public void show() {
		System.out.println("Name:" + name + "\nTeacher'sphone:" + phone);
	}

	@Override
	public void update() {
		phone = mTeacher.getPhone();
	}

}
