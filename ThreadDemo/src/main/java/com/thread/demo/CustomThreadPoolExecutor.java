package com.thread.demo;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * �Զ����̳߳�
 * @author jaydelano
 *
 */
public class CustomThreadPoolExecutor {

	private ThreadPoolExecutor pool = null;

	/**
	 * �̳߳س�ʼ������
	 * 
	 * corePoolSize �����̳߳ش�С----10 
	 * maximumPoolSize ����̳߳ش�С----30 
	 * keepAliveTime �̳߳��г���corePoolSize��Ŀ�Ŀ����߳������ʱ��----30+��λTimeUnit 
	 * TimeUnit keepAliveTimeʱ�䵥λ----TimeUnit.MINUTES 
	 * workQueue ��������----new ArrayBlockingQueue<Runnable>(10)====10�������������� 
	 * threadFactory �½��̹߳���----new CustomThreadFactory()====���Ƶ��̹߳��� 
	 * rejectedExecutionHandler ���ύ����������maxmumPoolSize+workQueue֮��ʱ,�����ύ��41������ʱ(ǰ���̶߳�û��ִ����,�˲��Է�������sleep(100)), ����ύ��RejectedExecutionHandler������
	 */
	public void init() {
		pool = new ThreadPoolExecutor(10, 30, 30, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10),
				new CustomThreadFactory(), new CustomRejectedExecutionHandler());
	}

	public void destory() {
		if (pool != null) {
			pool.shutdownNow();
		}
	}

	public ExecutorService getCustomThreadPoolExecutor() {
		return this.pool;
	}

	private class CustomThreadFactory implements ThreadFactory {

		private AtomicInteger count = new AtomicInteger(0);//�ṩԭ�Ӳ�����Integer�࣬ͨ���̰߳�ȫ�ķ�ʽ�����Ӽ�

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			String threadName = CustomThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);
			System.out.println(threadName);
			t.setName(threadName);
			return t;
		}
	}

	private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			// System.out.println("error.............");
			try {
				executor.getQueue().put(r);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
		exec.init();

		ExecutorService pool = exec.getCustomThreadPoolExecutor();
		for (int i = 1; i < 100; i++) {
			System.out.println("�ύ��" + i + "������!");
			pool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("running=====" + Thread.currentThread().getName());
				}
			});
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
