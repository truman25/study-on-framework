package com.thread.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolTest {
	//ForkJoinPool��ExecutorService��ʵ���࣬�����һ��������̳߳�
	//ForkJoinTask��һ�������࣬�����������������ࣺRecusiveAction��RecusiveTask��
	//����RecusiveTask�����з���ֵ�����񣬶�RecusiveAction����û�з���ֵ������
	
	//ForkJoinPool��Ҫ����ʹ�÷��η�(Divide-and-Conquer Algorithm)��������⡣���͵�Ӧ�ñ�����������㷨��
	//�����Ҫ�����ڣ�ForkJoinPool��Ҫʹ������ٵ��߳����������������

	public static void main1(String[] args) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		pool.submit(new PrintTask(1, 100));
		pool.awaitTermination(2, TimeUnit.SECONDS);// ������ǰ�߳�ֱ�� ForkJoinPool
													 //�����е�����ִ�н���
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
		System.out.println("��ǰ������Ϊ�� " + result);
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
			System.out.println("��ǰ������Ϊ�� " + num);
		} else {
			int mid = (end + start) / 2;
			PrintTask left = new PrintTask(start, mid);
			PrintTask right = new PrintTask(mid + 1, end);
			//����ִ��������С����
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
			System.out.println("��ǰ������Ϊ�� " + num);
			return num;
		} else {
			int mid = (end + start) / 2;
			PrintTask1 left = new PrintTask1(start, mid);
			PrintTask1 right = new PrintTask1(mid + 1, end);
			//����ִ������ С����
			left.fork();
			right.fork();
			//������С�����ۼӵĽ���ϲ�����
			return left.join() + right.join();
		}
	}
}
