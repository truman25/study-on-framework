package com.thread.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolTest {
	//ForkJoinPool是ExecutorService的实现类，因此是一种特殊的线程池
	//ForkJoinTask是一个抽象类，它还有两个抽象子类：RecusiveAction和RecusiveTask。
	//其中RecusiveTask代表有返回值的任务，而RecusiveAction代表没有返回值的任务。
	
	//ForkJoinPool主要用来使用分治法(Divide-and-Conquer Algorithm)来解决问题。典型的应用比如快速排序算法。
	//这里的要点在于，ForkJoinPool需要使用相对少的线程来处理大量的任务

	public static void main1(String[] args) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		pool.submit(new PrintTask(1, 100));
		pool.awaitTermination(2, TimeUnit.SECONDS);// 阻塞当前线程直到 ForkJoinPool
													 //中所有的任务都执行结束
		pool.shutdown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Integer result = 0;
		ForkJoinPool pool = new ForkJoinPool();
		Future<Integer> future = pool.submit(new PrintTask1(1, 9999));
		try {
			result = future.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("当前任务结果为： " + result);
		pool.shutdownNow();
		//pool.shutdown();
	}

}

class PrintTask extends RecursiveAction {
	private static final long serialVersionUID = 8635119133774500468L;
	private int start;
	private int end;
	private int num;
	final int MAX = 50;
 
	public PrintTask(int start, int end) {
		this.start = start;
		this.end = end;
	}
 
	@Override
	protected void compute() {
		if (end - start < 50) {
			for (int i = start; i <= end; i++) {
				num += i;
			}
			System.out.println("当前任务结果为： " + num);
		} else {
			int mid = (end + start) / 2;
			PrintTask left = new PrintTask(start, mid);
			PrintTask right = new PrintTask(mid + 1, end);
			//并行执行两个“小任务”
			left.fork();
			right.fork();
		}
	}
}

class PrintTask1 extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 8635119133774500468L;
	private int start;
	private int end;
	private int num;
	final int MAX = 50;
 
	public PrintTask1(int start, int end) {
		this.start = start;
		this.end = end;
	}
 
	@Override
	protected Integer compute() {
		if (end - start < 20) {
			for (int i = start; i <= end; i++) {
				num += i;
			}
			System.out.println("当前任务结果为： " + num);
			return num;
		} else {
			int mid = (end + start) / 2;
			PrintTask1 left = new PrintTask1(start, mid);
			PrintTask1 right = new PrintTask1(mid + 1, end);
			//并行执行两个 小任务
			left.fork();
			right.fork();
			//把两个小任务累加的结果合并起来
			return left.join() + right.join();
		}
	}
}
