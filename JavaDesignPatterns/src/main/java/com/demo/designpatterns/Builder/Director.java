package com.demo.designpatterns.Builder;

/**
 * װ����Աװ��(ָ����)
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
