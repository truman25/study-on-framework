package com.demo.designpatterns.Adapter.InterfaceAdapter;

/**
 * ����˿ڽӿڣ��ṩͨ�ŷ���
 * 
 * @author jaydelano
 *
 */
public interface Port {

	/**
	 * Զ��SSH�˿�Ϊ22
	 */
	void SSH();

	/**
	 * ����˿�Ϊ80
	 */
	void NET();

	/**
	 * Tomcat�����˿�Ϊ8080
	 */
	void Tomcat();

	/**
	 * MySQL���ݿ�˿�Ϊ3306
	 */
	void MySQL();

}
