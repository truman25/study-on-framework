package com.jsl.demo;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class HelloWorldReceiver {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.0.35");
		factory.setUsername("admin");
		factory.setPassword("123456");
		// 打开连接和创建频道，与发送端一样
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//创建一个队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		// 创建队列消费者  
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		// 指定消费队列  
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
