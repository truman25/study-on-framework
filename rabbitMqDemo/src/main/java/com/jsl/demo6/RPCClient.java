package com.jsl.demo6;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 
 * @ClassName:     RPCClient.java
 * @Description:   RPC客户端
 * 
 * @author         gu.xm
 * @version        V1.0  
 * @Date           2017年2月16日 下午10:21:35
 */
public class RPCClient {
	private Connection connection;
	private Channel channel;
	private String requestQueueName = "rpc_queue";
	private String replyQueueName;

	/**
	 * 无参构造函数初始化链接、声明随机队列
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public RPCClient() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.0.35");
		factory.setUsername("admin");
		factory.setPassword("123456");

		connection = factory.newConnection();
		channel = connection.createChannel();
		//声明一个随机队列  
		replyQueueName = channel.queueDeclare().getQueue();
	}

	
//Message properties（消息属性）
//AMQP协议为消息预定义了一组14个属性。大部分的属性是很少使用的。除了一下几种：
//deliveryMode：标记消息传递模式，2-消息持久化，其他值-瞬态。在第二篇文章中还提到过。
//contentType：内容类型，用于描述编码的mime-type。例如经常为该属性设置JSON编码。
//replyTo：应答，通用的回调队列名称
//correlationId：关联ID，方便RPC响应与请求关联
	
	public String call(String message) throws IOException, InterruptedException {
		final String corrId = UUID.randomUUID().toString();
		 //设置replyTo和correlationId属性值  
		AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName)
				.build();
		//发送消息到rpc_queue队列     requestQueueName：随机队列名  props：存储corrId和响应队列名replyQueueName
		channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));
		//阻塞队列
		final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);

		channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				//收到信息时，比对CorrelationId，如果跟请求时的CorrelationId值一致，说明是需要的服务器端响应
				if (properties.getCorrelationId().equals(corrId)) {
					//将指定元素插入此队列中
					response.offer(new String(body, "UTF-8"));
				}
			}
		});
		//获取并移除此队列的头部，在元素变得可用之前一直等待 。queue的长度 == 0 的时候，一直阻塞
		return response.take();
	}

	/**
	 * 关闭链接
	 * @throws IOException
	 */
	public void close() throws IOException {
		connection.close();
	}

	public static void main(String[] argv) {
		RPCClient fibonacciRpc = null;
		String response = null;
		try {
			fibonacciRpc = new RPCClient();

			System.out.println(" [x] Requesting fib(30)");
			response = fibonacciRpc.call("8");
			System.out.println(" [.] Got '" + response + "'");
		} catch (IOException | TimeoutException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (fibonacciRpc != null) {
				try {
					fibonacciRpc.close();
				} catch (IOException _ignore) {
				}
			}
		}
	}
}
