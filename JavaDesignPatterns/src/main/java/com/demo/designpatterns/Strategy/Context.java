package com.demo.designpatterns.Strategy;

public class Context {
	Strategy strategy;

	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	public void contextInterface() {
		strategy.AlgorithmInterface();
	}

}
