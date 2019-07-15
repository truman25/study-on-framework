package com.demo.designpatterns.Observer;

/**
 * ConcreteObserver(具体观察者, Student)： 维护一个指向ConcreteSubject对象的引用。
 * 存储有关状态，这些状态应与目标的状态保持一致。 实现Observer的更新接口以使自身状态与目标的状态保持一致。
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
