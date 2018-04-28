package com.jsl.demo4;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ReceiveLogsDirect {
	private static final String EXCHANGE_NAME = "direct_logs";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.0.35");
		factory.setUsername("admin");
		factory.setPassword("123456");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		// 声明一个随机队列  
		String queueName = channel.queueDeclare().getQueue();

		//所有日志严重性级别  
        String[] severities={"error","info","warning"};  
//        String[] severities={"error"};//只关注error级别的日志，然后记录到文件中去。  
        for (String severity : severities) {  
            //关注所有级别的日志（多重绑定）  
        	//绑定键关键取决于转换器的类型。对于fanout类型，忽略此参数
            channel.queueBind(queueName, EXCHANGE_NAME, severity);  
        }  
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}
