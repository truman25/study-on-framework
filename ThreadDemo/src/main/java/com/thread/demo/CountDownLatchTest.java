package com.thread.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch类位于java.util.concurrent包下，利用它可以实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，
 * 此时就可以利用CountDownLatch来实现这种功能了。
 * @author jaydelano
 *
 */
public class CountDownLatchTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(3);
		final CountDownLatch latch = new CountDownLatch(3);
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("子线程" + Thread.currentThread().getName() + "执行完成");
						latch.countDown(); // 当前线程调用此方法，则计数减一
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}

		try {
			System.out.println("主线程" + Thread.currentThread().getName() + "等待子线程执行完成...");
			latch.await(); // 阻塞当前线程，直到计时器的值为0
			System.out.println("主线程" + Thread.currentThread().getName() + "开始执行...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
