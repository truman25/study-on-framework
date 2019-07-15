package com.thread.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 阻塞队列ArrayBlockingQueue
 * 
 * 使用阻塞队列的好处：多线程操作共同的队列时不需要额外的同步，另外就是队列会自动平衡负载，即那边（生产与消费两边）处理快了就会被阻塞掉，从而减少两边的处理速度差距。
 * 当许多线程共享访问一个公共 collection 时，ConcurrentLinkedQueue 是一个恰当的选择。
 * LinkedBlockingQueue 多用于任务队列
 * ConcurrentLinkedQueue  多用于消息队列
 * 多个生产者，对于LBQ性能还算可以接受；但是多个消费者就不行了mainLoop需要一个timeout的机制，否则空转，cpu会飙升的。LBQ正好提供了timeout的接口，更方便使用
 * 如果CLQ，那么我需要收到处理sleep
 * 单生产者，单消费者  用 LinkedBlockingqueue
 * 多生产者，单消费者   用 LinkedBlockingqueue
 * 单生产者 ，多消费者   用 ConcurrentLinkedQueue
 * 多生产者 ，多消费者   用 ConcurrentLinkedQueue
 * 
 * @author jaydelano
 *
 */
public class ArrayBlockingQueueTest {

	// 阻塞队列，FIFO(先进先出)
	private static ArrayBlockingQueue<Integer> concurrentArrayQueue = new ArrayBlockingQueue<Integer>(10);

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		executorService.submit(new Producer("producer1"));
		executorService.submit(new Producer("producer2"));
		executorService.submit(new Producer("producer3"));
		executorService.submit(new Consumer("consumer1"));
		executorService.submit(new Consumer("consumer2"));
		executorService.submit(new Consumer("consumer3"));

	}

	static class Producer implements Runnable {
		private String name;

		public Producer(String name) {
			this.name = name;
		}

		public void run() {
			for (int i = 1; i < 10; ++i) {
				System.out.println(name + " 生产： " + i);
				// concurrentArrayQueue.add(i);
				try {
					concurrentArrayQueue.put(i);
					//add方法在添加元素的时候，若超出了度列的长度会直接抛出异常
					//put方法，若向队尾添加元素的时候发现队列已经满了会发生阻塞一直等待空间，以加入元素
					//offer方法在添加元素时，如果发现队列已满无法添加的话，会直接返回false
					Thread.sleep(200); // 模拟慢速的生产，产生阻塞的效果
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		}
	}

	static class Consumer implements Runnable {
		private String name;

		public Consumer(String name) {
			this.name = name;
		}

		public void run() {
			for (int i = 1; i < 10; ++i) {
				try {
					// 必须要使用take()方法在获取的时候阻塞
					System.out.println(name + " 消费： " + concurrentArrayQueue.take());
					// 使用poll()方法 将产生非阻塞效果
					// System.out.println(name+"消费： " + concurrentArrayQueue.poll());

					// 还有一个超时的用法，队列空时，指定阻塞时间后返回，不会一直阻塞
					// 但有一个疑问，既然可以不阻塞，为啥还叫阻塞队列？
					// System.out.println(name+" Consumer " + concurrentArrayQueue.poll(300, TimeUnit.MILLISECONDS));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}
}
