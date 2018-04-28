package com.jsl.demo5;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLogTopic {
	private static final String EXCHANGE_NAME = "topic_logs";

	public static void main(String[] argv) {
		Connection connection = null;
		Channel channel = null;
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("192.168.0.35");
			factory.setUsername("admin");
			factory.setPassword("123456");

			connection = factory.newConnection();
			channel = connection.createChannel();
			// 指定转发——广播 Topic exchange（主题转发器）
			channel.exchangeDeclare(EXCHANGE_NAME, "topic");

			// 所有设备和日志级别
			String[] facilities = { "auth", "cron", "kern", "auth.A" };
			String[] severities = { "error", "info", "warning" };

			String dots = "";
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					// 每一个设备，每种日志级别发送一条日志消息
					String routingKey = facilities[i] + "." + severities[j % 3];

					dots += ".";
					// 发送的消息
					String message = " Hello World!" + dots + dots.length();
					// 参数1：exchange name
					// 参数2：routing key
					channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
					System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception ignore) {
				}
			}
		}
	}

}
