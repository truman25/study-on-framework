package com.demo.designpatterns.Builder;

/**
 * �����װ����Ա�����彨���ߣ�
 * 
 * @author jaydelano
 *
 */
public class ConcreteBuilder extends Builder {

	Computer computer = new Computer();

	@Override
	public void buildCPU() {
		computer.Add("װCPU");
	}

	@Override
	public void buildMainBoard() {
		computer.Add("װ����");
	}

	@Override
	public void buildHD() {
		computer.Add("װӲ��");
	}

	@Override
	public Computer getComputer() {
		return computer;
	}

}
