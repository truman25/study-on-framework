package com.demo.designpatterns.Builder;

import java.util.ArrayList;
import java.util.List;

public class Computer {
	/**
	 * �����������
	 */
	private List<String> parts = new ArrayList<String>();

	public void Add(String part) {
		parts.add(part);
	}

	public void print() {
		for (int i = 0; i < parts.size(); i++) {
			System.out.println("���:" + parts.get(i) + "װ����...");
		}
		System.out.println("������װ���...");
	}

}
