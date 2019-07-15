package com.demo.designpatterns.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者(Observer)模式测试类
 */
public class ObserverPattern {

	public static void main(String[] args) {
		List<Observer> students = new ArrayList<Observer>();
		Teacher t = new Teacher();
		for (int i = 0; i < 10; i++) {
			Student st = new Student("Andy.Chen" + i, t);
			students.add(st);
			t.attach(st);
		}

		System.out.println(
				"Welcome to Andy.Chen Blog!" + "\n" + "Observer Patterns." + "\n" + "-------------------------------");

		t.setPhone("12345678");
		for (int i = 0; i < 3; i++)
			((Student) students.get(i)).show();

		t.setPhone("87654321");
		for (int i = 0; i < 3; i++)
			((Student) students.get(i)).show();
	}
}
