package com.demo.designpatterns.Adapter.InterfaceAdapter;

/**
 * 提供聊天服务
 * 需要网络功能
 */
public class Chat extends Wrapper {

	@Override
    public void NET() {
        System.out.println("Hello World...");
    }
}
