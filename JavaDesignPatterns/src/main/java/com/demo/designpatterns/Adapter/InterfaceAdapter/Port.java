package com.demo.designpatterns.Adapter.InterfaceAdapter;

/**
 * 定义端口接口，提供通信服务
 * 
 * @author jaydelano
 *
 */
public interface Port {

	/**
	 * 远程SSH端口为22
	 */
	void SSH();

	/**
	 * 网络端口为80
	 */
	void NET();

	/**
	 * Tomcat容器端口为8080
	 */
	void Tomcat();

	/**
	 * MySQL数据库端口为3306
	 */
	void MySQL();

}
