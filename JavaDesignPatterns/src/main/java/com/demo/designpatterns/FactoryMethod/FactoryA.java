package com.demo.designpatterns.FactoryMethod;

public class FactoryA extends Factory {

	@Override
	public Product Manufacture() {
		return new ProductA();
	}

}
