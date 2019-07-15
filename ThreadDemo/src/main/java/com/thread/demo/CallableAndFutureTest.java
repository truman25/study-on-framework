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
	 * Future<V>和Callable<V>与线程池获取执行结果
	 * @param args
	 */
	public static void main1(String[] args) {
		ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(1);
		CallableThread callableThread = new CallableThread();
		Future<Integer> future = schedulePool.schedule(callableThread, 1000, TimeUnit.MILLISECONDS);
        //这样就是直接提交，跟定时的0延迟任务一样
        //Future<Integer> future = schedulePool.submit(callableThread);
		try {
			System.out.println(future.get()); // 获取其结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			schedulePool.shutdown();
		}
	}
	
	/**
	 * Callable<V>和FutureTask<V>，闭包操作
	 * 
	 * FutureTask是一种可以取消的异步的计算任务
	 * 一般FutureTask多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果
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
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get()); // 获取其结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Callable<V>和Future<V>，线程池提交闭包操作
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
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get()); // 获取其结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

	/**
	 * 批处理任务
	 * @param args
	 */
	public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
//        /**
//		 * 容量为10的堵塞队列
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
					System.out.println("taskID:" + taskID + " 歇息了 " + ran);
                    return taskID;
                }
            });
        }
        // 可能做一些事情
        for(int i = 1; i < 5; i++) {
            try {
            	//谁最先运行完毕，直接返回
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
			System.out.println(Thread.currentThread().getName() + "正在出售第" + tickets.get() + "张票");
		}
	}
}
