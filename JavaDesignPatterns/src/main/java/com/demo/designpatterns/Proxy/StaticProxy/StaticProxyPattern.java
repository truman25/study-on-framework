package com.demo.designpatterns.Proxy.StaticProxy;

/**
 * 静态代理模式
 * @author jaydelano
 *
 */
public class StaticProxyPattern {

	public static void main(String[] args) {
        IService service = new Service();
        //传入被代理类的对象
        ProxyService proxyService = new ProxyService(service);
        proxyService.service();
    }
	
}
