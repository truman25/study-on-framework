package com.demo.designpatterns.Proxy.StaticProxy;

/**
 * ��̬����ģʽ
 * @author jaydelano
 *
 */
public class StaticProxyPattern {

	public static void main(String[] args) {
        IService service = new Service();
        //���뱻������Ķ���
        ProxyService proxyService = new ProxyService(service);
        proxyService.service();
    }
	
}
