package com.demo.designpatterns.Adapter.InterfaceAdapter;

/**
 * ��վ������
 * ��ҪTomcat������Mysql���ݿ⣬�������Զ�̷���
 */
public class Server extends Wrapper {

	@Override
    public void SSH() {
        System.out.println("Connect success...");
    }

    @Override
    public void NET() {
        System.out.println("WWW...");
    }

    @Override
    public void Tomcat() {
        System.out.println("Tomcat is running...");
    }

    @Override
    public void MySQL() {
        System.out.println("MySQL is running...");
    }
    
}
