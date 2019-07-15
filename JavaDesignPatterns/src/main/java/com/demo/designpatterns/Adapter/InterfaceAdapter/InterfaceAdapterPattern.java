package com.demo.designpatterns.Adapter.InterfaceAdapter;

public class InterfaceAdapterPattern {

	private static Port chatPort = new Chat();
	private static Port serverPort = new Server();

	public static void main(String[] args) {
		// �������
		chatPort.NET();

		// ������
		serverPort.SSH();
		serverPort.NET();
		serverPort.Tomcat();
		serverPort.MySQL();
	}

}
