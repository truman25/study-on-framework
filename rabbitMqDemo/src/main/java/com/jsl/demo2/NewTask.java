package com.jsl.demo2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {
	private static final String TASK_QUEUE_NAME = "task_queue";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.0.35");
		factory.setUsername("admin");
		factory.setPassword("123456");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		boolean durable = true;//声明队列持久化（所以已经存在的队列，我们无法修改其属性）
		channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);

		// 发送10条消息，依次在消息后面附加1-10个点  
        for (int i = 10; i > 0; i--)  
        {  
            String dots = "";  
            for (int j = 0; j <= i; j++)  
            {  
                dots += ".";  
            }  
            String message = "helloworld" + dots + dots.length();  
    		//MessageProperties.PERSISTENT_TEXT_PLAIN 信息为持久化
    		channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
    		System.out.println(" [x] Sent '" + message + "'");
        } 

		channel.close();
		connection.close();
	}
}
