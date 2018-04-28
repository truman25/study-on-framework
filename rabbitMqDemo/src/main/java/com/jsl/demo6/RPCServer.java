package com.jsl.demo6;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
/**
 * 
 * @ClassName:     RPCServer.java
 * @Description:   RPC服务器端
 * 
 * @author         gu.xm
 * @version        V1.0  
 * @Date           2017年2月16日 下午10:15:29
 */
public class RPCServer {
	private static final String RPC_QUEUE_NAME = "rpc_queue";

	/**
	 * 返回斐波纳契数列，模拟服务端处理耗时服务
	 * @param n
	 * @return
	 */
	private static int fib(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return fib(n - 1) + fib(n - 2);
	}
	
	//Message properties（消息属性）
	//AMQP协议为消息预定义了一组14个属性。大部分的属性是很少使用的。除了一下几种：
	//deliveryMode：标记消息传递模式，2-消息持久化，其他值-瞬态。在第二篇文章中还提到过。
	//contentType：内容类型，用于描述编码的mime-type。例如经常为该属性设置JSON编码。
	//replyTo：应答，通用的回调队列名称
	//correlationId：关联ID，方便RPC响应与请求关联

	public static void main(String[] argv) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.0.35");
		factory.setUsername("admin");
		factory.setPassword("123456");

		Connection connection = null;
		try {
			connection = factory.newConnection();
			final Channel channel = connection.createChannel();
			//声明队列  //这里使用的是demo2中的工作队列模式
			channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
			//限制：每次最多给一个消费者发送1条消息  //限制发给同一个消费者不得超过1条消息 (当消息处理完毕后，有了反馈，才会进行第二次发送)
			channel.basicQos(1);

			System.out.println(" [x] Awaiting RPC requests");
			//为rpc_queue队列创建消费者，用于处理请求 
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					//获取请求中的correlationId属性值，并将其设置到结果消息的correlationId属性中  
					AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder().correlationId(
							properties.getCorrelationId()).build();

					String response = "";

					try {
						String message = new String(body, "UTF-8");
						int n = Integer.parseInt(message);

						System.out.println(" [.] fib(" + message + ")");
						response += fib(n);
					} catch (RuntimeException e) {
						System.out.println(" [.] " + e.toString());
					} finally {
						//先发送回调结果(给指定名称properties.getReplyTo()的回调对列发送结果)  
						channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
						//另外需要在每次处理完成一个消息后，手动发送一次应答。
						channel.basicAck(envelope.getDeliveryTag(), false);
					}
				}
			};
			//第二个参数 false 打开应答机制
			channel.basicConsume(RPC_QUEUE_NAME, false, consumer);

			// loop to prevent reaching finally block
			while (true) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException _ignore) {
				}
			}
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (IOException _ignore) {
				}
		}
	}
}
