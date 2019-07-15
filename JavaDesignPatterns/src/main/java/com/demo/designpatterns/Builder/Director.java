package com.demo.designpatterns.Builder;

/**
 * 装机人员装机(指挥者)
 * 
 * @author jaydelano
 *
 */
public class Director {
	public void Construct(Builder builder) {
		builder.buildCPU();
		builder.buildMainBoard();
		builder.buildHD();
	}
}
