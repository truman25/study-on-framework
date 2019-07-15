package com.demo.designpatterns.FactoryMethod;

public class FactoryB extends Factory {

	@Override
	public Product Manufacture() {
		return new ProductB();
	}

}
