package com.thread.demo;

public class ThreadTest {
	public static void main(String[] args) {  
		//��ʽһ �̳�Thread
    	ThreadFun1 thread = new ThreadFun1();  
    	thread.start();

	
    	//��ʽ�� ʵ��Runnable�ӿ�
    	ThreadFun2 thread2 = new ThreadFun2();  
        Thread t = new Thread(thread2);
        t.start();  
        
        //��ʽ�� Thread�հ������߳�
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
        
        //��ʽ�� Runnable�հ������߳�
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
 * �̳�Thread
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
 * ʵ��Runnable�ӿ�
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
