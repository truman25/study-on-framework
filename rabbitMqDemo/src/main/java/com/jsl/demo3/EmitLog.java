package com.jsl.demo3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {
	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.0.35");
		factory.setUsername("admin");
		factory.setPassword("123456");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// 指定转发——广播 类型有：Direct、Topic、Headers和Fanout(分散、散开)
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

		for (int i = 10; i > 0; i--) {
			String dots = "";
			for (int j = 0; j <= i; j++) {
				dots += ".";
			}
			String message = "helloworld" + dots + dots.length();
			//之前例子第一个参数为“”，表示Nameless exchange（匿名转发）
			//广播时第二个参数队列名称不需要填，生产者不关心消息发送给谁
			channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + message + "'");
		}

		channel.close();
		connection.close();
	}

}
