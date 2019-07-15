package com.thread.demo;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 非阻塞队列ConcurrentLinkedQueue
 * 
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
 * 
 * @author jaydelano
 *
 */
public class ConcurrentLinkedQueueTest {
	private static ConcurrentLinkedQueue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<Integer>();

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
				System.out.println(name + " start producer " + i);
				concurrentLinkedQueue.add(i);
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
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
					if (!concurrentLinkedQueue.isEmpty()) {
						System.out.println(name + " Consumer " + concurrentLinkedQueue.poll());//poll():当没有获得数据时返回为null，如果有数据时则移除移除表头数据，并将表头数据进行返回
					} else {
						System.out.println(name + " not cost");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
