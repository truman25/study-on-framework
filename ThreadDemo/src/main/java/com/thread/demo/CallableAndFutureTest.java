package com.thread.demo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CallableAndFutureTest {

	/**
	 * Future<V>��Callable<V>���̳߳ػ�ȡִ�н��
	 * @param args
	 */
	public static void main1(String[] args) {
		ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(1);
		CallableThread callableThread = new CallableThread();
		Future<Integer> future = schedulePool.schedule(callableThread, 1000, TimeUnit.MILLISECONDS);
        //��������ֱ���ύ������ʱ��0�ӳ�����һ��
        //Future<Integer> future = schedulePool.submit(callableThread);
		try {
			System.out.println(future.get()); // ��ȡ��������������������������һֱ�ȵ�����ִ����ϲŷ���
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			schedulePool.shutdown();
		}
	}
	
	/**
	 * Callable<V>��FutureTask<V>���հ�����
	 * 
	 * FutureTask��һ�ֿ���ȡ�����첽�ļ�������
	 * һ��FutureTask�����ں�ʱ�ļ��㣬���߳̿���������Լ����������ȥ��ȡ���
	 * 
	 * @param args
	 */
	public static void main2(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        try {
            Thread.sleep(5000);// ������һЩ����
            System.out.println(future.get()); // ��ȡ��������������������������һֱ�ȵ�����ִ����ϲŷ���
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Callable<V>��Future<V>���̳߳��ύ�հ�����
	 * @param args
	 */
	public static void main3(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });
        try {
            Thread.sleep(5000);// ������һЩ����
            System.out.println(future.get()); // ��ȡ��������������������������һֱ�ȵ�����ִ����ϲŷ���
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

	/**
	 * ����������
	 * @param args
	 */
	public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
//        /**
//		 * ����Ϊ10�Ķ�������
//		 */
//		final BlockingQueue<Future<Integer>> queue = new LinkedBlockingDeque<Future<Integer>>(10);
//        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool, queue);
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
        for(int i = 1; i < 5; i++) {
            final int taskID = i;
            cs.submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                	int ran = new Random().nextInt(1000);
					Thread.sleep(ran);
					System.out.println("taskID:" + taskID + " ЪϢ�� " + ran);
                    return taskID;
                }
            });
        }
        // ������һЩ����
        for(int i = 1; i < 5; i++) {
            try {
            	//˭����������ϣ�ֱ�ӷ���
				Future<Integer> f = cs.take();
				System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
    }

}


class CallableThread implements Callable<Integer> {
	 
	private AtomicInteger tickets = new AtomicInteger(100);
	
	@Override
	public Integer call() throws Exception {
		while (tickets.get() > 0) {
			try {
				doMethod();
			}finally {
			}
		}
		return tickets.get();
	}
	
	private synchronized void doMethod() {
		if (tickets.addAndGet(-1) > 0) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "���ڳ��۵�" + tickets.get() + "��Ʊ");
		}
	}
}
