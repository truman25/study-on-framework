package com.demo.designpatterns.Adapter.InterfaceAdapter;

/**
 * 网站服务器
 * 需要Tomcat容器，Mysql数据库，网络服务，远程服务
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
