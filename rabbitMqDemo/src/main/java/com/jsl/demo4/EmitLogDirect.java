package com.jsl.demo4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLogDirect {
	private static final String EXCHANGE_NAME = "direct_logs";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.0.35");
		factory.setUsername("admin");
		factory.setPassword("123456");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// 创建一个转换器-Direct exchange（直接转发）
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");

		// 所有日志严重性级别
		String[] severities = { "error", "info", "warning" };
		for (int i = 0; i < 3; i++) {
			String severity = severities[i % 3];// 每一次发送一条不同严重性的日志

			String dots = "";
			for (int j = 0; j <= i; j++) {
				dots += ".";
			}

			// 发送的消息
			String message = "helloworld" + dots + dots.length();
			// 参数1：exchange name
			// 参数2：routing key
			channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
		}
		channel.close();
		connection.close();
	}

}
