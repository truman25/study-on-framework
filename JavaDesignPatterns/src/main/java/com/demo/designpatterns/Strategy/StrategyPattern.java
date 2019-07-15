package com.demo.designpatterns.Strategy;

/**
 * ²ßÂÔÄ£Ê½
 * 
 * @author jaydelano
 *
 */
public class StrategyPattern {
	public static void main(String[] args) {

		Context context;

		context = new Context(new ConcreteStrategyA());
		context.contextInterface();

		context = new Context(new ConcreteStrategyB());
		context.contextInterface();

		context = new Context(new ConcreteStrategyC());
		context.contextInterface();
	}

}
