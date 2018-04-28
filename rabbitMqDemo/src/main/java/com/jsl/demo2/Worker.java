package com.jsl.demo2;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Worker {
	private static final String TASK_QUEUE_NAME = "task_queue";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.0.35");
		factory.setUsername("admin");
		factory.setPassword("123456");
		final Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();

		boolean durable = true;//声明队列持久化（所以已经存在的队列，我们无法修改其属性）
		channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		int prefetchCount = 1;
		//限制发给同一个消费者不得超过1条消息 (当消息处理完毕后，有了反馈，才会进行第二次发送)
		channel.basicQos(prefetchCount);

		final Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");

				System.out.println(" [x] Received '" + message + "'");
				try {
					doWork(message);
				} finally {
					System.out.println(" [x] Done");
					//另外需要在每次处理完成一个消息后，手动发送一次应答。
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			}
		};
		boolean ack = false ; //打开应答机制
		channel.basicConsume(TASK_QUEUE_NAME, ack, consumer);
	}

	private static void doWork(String task) {
		for (char ch : task.toCharArray()) {
			if (ch == '.') {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException _ignored) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}
