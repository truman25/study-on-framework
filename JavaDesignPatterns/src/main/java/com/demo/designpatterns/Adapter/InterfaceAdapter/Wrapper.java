package com.demo.designpatterns.Adapter.InterfaceAdapter;

/**
 * 定义抽象类实现端口接口，但是什么事情都不做
 * @author jaydelano
 *
 */
public abstract class Wrapper implements Port {

	@Override
	public void SSH() {

	}

	@Override
	public void NET() {

	}

	@Override
	public void Tomcat() {

	}

	@Override
	public void MySQL() {

	}

}
