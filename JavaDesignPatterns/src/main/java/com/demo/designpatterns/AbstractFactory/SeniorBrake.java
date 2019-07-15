package com.demo.designpatterns.AbstractFactory;

public class SeniorBrake implements IBrake {

	@Override
	public void brake() {
		System.out.println("高级制动");

	}

}
