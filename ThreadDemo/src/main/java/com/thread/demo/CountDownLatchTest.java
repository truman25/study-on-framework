package com.thread.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch��λ��java.util.concurrent���£�����������ʵ�����Ƽ������Ĺ��ܡ�������һ������A����Ҫ�ȴ�����4������ִ�����֮�����ִ�У�
 * ��ʱ�Ϳ�������CountDownLatch��ʵ�����ֹ����ˡ�
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
						System.out.println("���߳�" + Thread.currentThread().getName() + "��ʼִ��");
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("���߳�" + Thread.currentThread().getName() + "ִ�����");
						latch.countDown(); // ��ǰ�̵߳��ô˷������������һ
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}

		try {
			System.out.println("���߳�" + Thread.currentThread().getName() + "�ȴ����߳�ִ�����...");
			latch.await(); // ������ǰ�̣߳�ֱ����ʱ����ֵΪ0
			System.out.println("���߳�" + Thread.currentThread().getName() + "��ʼִ��...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
