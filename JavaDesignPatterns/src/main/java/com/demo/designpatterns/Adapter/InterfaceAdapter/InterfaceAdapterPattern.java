package com.demo.designpatterns.Adapter.InterfaceAdapter;

public class InterfaceAdapterPattern {

	private static Port chatPort = new Chat();
	private static Port serverPort = new Server();

	public static void main(String[] args) {
		// 聊天服务
		chatPort.NET();

		// 服务器
		serverPort.SSH();
		serverPort.NET();
		serverPort.Tomcat();
		serverPort.MySQL();
	}

}
