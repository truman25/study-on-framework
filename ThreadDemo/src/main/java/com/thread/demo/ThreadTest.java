package com.thread.demo;

public class ThreadTest {
	public static void main(String[] args) {  
		//方式一 继承Thread
    	ThreadFun1 thread = new ThreadFun1();  
    	thread.start();

	
    	//方式二 实现Runnable接口
    	ThreadFun2 thread2 = new ThreadFun2();  
        Thread t = new Thread(thread2);
        t.start();  
        
        //方式三 Thread闭包创建线程
        for (int i = 1; i < 5; i ++) {
			final int taskId = i;
			new Thread() {
				public void run() {
					for (int i = 1; i < 5; i ++) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("Thread Task : " + taskId + "; run time :" + i);
					}
				}
			}.start();
		}
        
        //方式四 Runnable闭包创建线程
        for (int i = 1; i < 5; i ++) {
			final int taskId = i;
			new Thread(new Runnable() {
				public void run() {
					for (int i = 1; i < 5; i ++) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("Runnable Task : " + taskId + "; run time :" + i);
					}
				}
			}).start();
		}

    } 

}

/**
 * 继承Thread
 * @author jaydelano
 *
 */
class ThreadFun1 extends Thread {  
	 
	@Override
    public void run() {  
        while(true) {  
            try {  
                Thread.sleep(1000);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            System.out.println("Thread Hello!");  
        }  
    }  
}

/**
 * 实现Runnable接口
 * @author jaydelano
 *
 */
class ThreadFun2 implements Runnable {  
	 
	@Override
    public void run() {  
        while(true) {  
            try {  
                Thread.sleep(1000);  
            } catch (InterruptedException e) {   
                e.printStackTrace();  
            }  
            System.out.println("Runnable Hello!");  
        }  
    }  
}
