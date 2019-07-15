package com.thread.demo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

	/**
	 * 瞬时线程
	 * @param args
	 */
	public static void main1(String[] args) {
		
		//方法1 创建可以容纳3个线程的线程池（创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待）
		//ExecutorService threadPool = Executors.newFixedThreadPool(1);
		//方法2  线程池的大小会根据执行的任务数动态分配 （第一次任务还未执行完时，无需等待动态开辟一个新线程给任务二；线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程）
		//ExecutorService threadPool = Executors.newCachedThreadPool();
		//方法3  创建单个线程的线程池，如果当前线程在执行任务时突然中断，则会创建一个新的线程替代它继续执行任务(开辟固定数量1个线程(方法1)和开辟单个线程(方法3)方法是一样效果)
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		for (int i = 1; i < 5; i++) {
			final int taskID = i;
			threadPool.execute(new Runnable() {
				public void run() {
					for (int i = 1; i < 5; i++) {
						try {
							Thread.sleep(20);// 为了测试出效果，让每次任务执行都需要一定时间
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("第" + taskID + "次任务的第" + i + "次执行");
					}
				}
			});
		}
		threadPool.shutdown();// 任务执行完毕，关闭线程池
	}
	
	/**
	 * 定时线程和周期线程
	 * @param args
	 */
	public static void main2(String[] args) {
		ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(1);
		// 5秒后执行任务
		schedulePool.schedule(new Runnable() {
			public void run() {
				System.out.println("爆炸");
			}
		}, 5, TimeUnit.SECONDS);
		// 5秒后执行任务，以后每2秒执行一次
		schedulePool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("爆炸");
			}
		}, 5, 2, TimeUnit.SECONDS);
		// 任务执行完成后再延迟固定时间后再执行下一次
		// 设定执行线程计划,初始3s延迟,每次任务完成后延迟2s再执行一次任务
		schedulePool.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				System.out.println("爆炸");
			}
		}, 3, 2, TimeUnit.SECONDS);
	}
	
	/**
	 * 	isShutDown当调用shutdown()或shutdownNow()方法后返回为true。 
	 * 	isTerminated当调用shutdown()方法后，并且所有提交的任务完成后返回为true;
	 * 	isTerminated当调用shutdownNow()方法后，成功停止后返回为true;
	 * 	如果线程池任务正常完成，都为true
	 * @param args
	 */
    public static void main3(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.submit(new Runnable() {
			public void run() {
				System.out.println("ShortTask");
			}
		});
        service.submit(new Runnable() {
			public void run() {
				System.out.println("ShortTask");
			}
		});
        service.submit(new Runnable() {
			public void run() {
				System.out.println("LongTask");
			}
		});
        service.submit(new Runnable() {
			public void run() {
				System.out.println("ShortTask");
			}
		});
        System.out.println("isShutdown:" + service.isShutdown());
        System.out.println("isTerminated:" + service.isTerminated());
        service.shutdown();
//        List<Runnable> list = service.shutdownNow();
//        System.out.println(list.size());
        System.out.println("isShutdown:" + service.isShutdown());
        System.out.println("isTerminated:" + service.isTerminated());
        try {
            while (!service.awaitTermination(1, TimeUnit.SECONDS)) { //检测线程池有没有关闭
                System.out.println("线程池没有关闭");
                System.out.println("isTerminated:" + service.isTerminated());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
        System.out.println("线程池已经关闭");
        System.out.println("isTerminated:" + service.isTerminated());
    }

    /**
     * 自定义线程（线程池统一命名）
     * @param args
     */
    public static void main4(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2, new CustomThreadFactory());
		
		for (int i = 0; i < 10; i++) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		executorService.shutdown();
	}
	
    /**
     * ThreadFactory是一个线程工厂。用来创建线程。这里为什么要使用线程工厂呢？其实就是为了统一在创建线程时设置一些参数，如是否守护线程。
     * 线程一些特性等，如优先级。通过这个TreadFactory创建出来的线程能保证有相同的特性。它首先是一个接口类，而且方法只有一个。就是创建一个线程。
     * @author jaydelano
     *
     */
	public static class CustomThreadFactory implements ThreadFactory {
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			String threadName = "自定义线程名称";
			t.setName(threadName);
			return t;
		}
	}
	
	/**
	 * ThreadLocal为变量在每个线程中都创建了一个副本，那么每个线程可以访问自己内部的副本变量
	 * 最常见的ThreadLocal使用场景为 用来解决 数据库连接、Session管理等
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadLocalThread str = new ThreadLocalThread();
		Thread t1 = new Thread(str,"窗口1");
		Thread t2 = new Thread(str,"窗口2");
		Thread t3 = new Thread(str,"窗口3");
		t1.start();
		t2.start();
		t3.start();
	}
	
}

class ThreadLocalThread implements Runnable {
	 
	//通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值  
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {  
        public Integer initialValue() {  
            return 100;  
        }  
    };
	
	@Override
	public void run() {
		while (seqNum.get() > 0) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "正在出售第" + (seqNum.get()) + "张票");
			seqNum.set(seqNum.get()-1);
		}
	}
}
