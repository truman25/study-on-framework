package com.thread.demo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

	/**
	 * ˲ʱ�߳�
	 * @param args
	 */
	public static void main1(String[] args) {
		
		//����1 ������������3���̵߳��̳߳أ�����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ���
		//ExecutorService threadPool = Executors.newFixedThreadPool(1);
		//����2  �̳߳صĴ�С�����ִ�е���������̬���� ����һ������δִ����ʱ������ȴ���̬����һ�����̸߳���������̳߳�Ϊ���޴󣬵�ִ�еڶ�������ʱ��һ�������Ѿ���ɣ��Ḵ��ִ�е�һ��������̣߳�������ÿ���½��̣߳�
		//ExecutorService threadPool = Executors.newCachedThreadPool();
		//����3  ���������̵߳��̳߳أ������ǰ�߳���ִ������ʱͻȻ�жϣ���ᴴ��һ���µ��߳����������ִ������(���ٹ̶�����1���߳�(����1)�Ϳ��ٵ����߳�(����3)������һ��Ч��)
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		for (int i = 1; i < 5; i++) {
			final int taskID = i;
			threadPool.execute(new Runnable() {
				public void run() {
					for (int i = 1; i < 5; i++) {
						try {
							Thread.sleep(20);// Ϊ�˲��Գ�Ч������ÿ������ִ�ж���Ҫһ��ʱ��
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("��" + taskID + "������ĵ�" + i + "��ִ��");
					}
				}
			});
		}
		threadPool.shutdown();// ����ִ����ϣ��ر��̳߳�
	}
	
	/**
	 * ��ʱ�̺߳������߳�
	 * @param args
	 */
	public static void main2(String[] args) {
		ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(1);
		// 5���ִ������
		schedulePool.schedule(new Runnable() {
			public void run() {
				System.out.println("��ը");
			}
		}, 5, TimeUnit.SECONDS);
		// 5���ִ�������Ժ�ÿ2��ִ��һ��
		schedulePool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("��ը");
			}
		}, 5, 2, TimeUnit.SECONDS);
		// ����ִ����ɺ����ӳٹ̶�ʱ�����ִ����һ��
		// �趨ִ���̼߳ƻ�,��ʼ3s�ӳ�,ÿ��������ɺ��ӳ�2s��ִ��һ������
		schedulePool.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				System.out.println("��ը");
			}
		}, 3, 2, TimeUnit.SECONDS);
	}
	
	/**
	 * 	isShutDown������shutdown()��shutdownNow()�����󷵻�Ϊtrue�� 
	 * 	isTerminated������shutdown()�����󣬲��������ύ��������ɺ󷵻�Ϊtrue;
	 * 	isTerminated������shutdownNow()�����󣬳ɹ�ֹͣ�󷵻�Ϊtrue;
	 * 	����̳߳�����������ɣ���Ϊtrue
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
            while (!service.awaitTermination(1, TimeUnit.SECONDS)) { //����̳߳���û�йر�
                System.out.println("�̳߳�û�йر�");
                System.out.println("isTerminated:" + service.isTerminated());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
        System.out.println("�̳߳��Ѿ��ر�");
        System.out.println("isTerminated:" + service.isTerminated());
    }

    /**
     * �Զ����̣߳��̳߳�ͳһ������
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
     * ThreadFactory��һ���̹߳��������������̡߳�����ΪʲôҪʹ���̹߳����أ���ʵ����Ϊ��ͳһ�ڴ����߳�ʱ����һЩ���������Ƿ��ػ��̡߳�
     * �߳�һЩ���Եȣ������ȼ���ͨ�����TreadFactory�����������߳��ܱ�֤����ͬ�����ԡ���������һ���ӿ��࣬���ҷ���ֻ��һ�������Ǵ���һ���̡߳�
     * @author jaydelano
     *
     */
	public static class CustomThreadFactory implements ThreadFactory {
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			String threadName = "�Զ����߳�����";
			t.setName(threadName);
			return t;
		}
	}
	
	/**
	 * ThreadLocalΪ������ÿ���߳��ж�������һ����������ôÿ���߳̿��Է����Լ��ڲ��ĸ�������
	 * �����ThreadLocalʹ�ó���Ϊ ������� ���ݿ����ӡ�Session�����
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadLocalThread str = new ThreadLocalThread();
		Thread t1 = new Thread(str,"����1");
		Thread t2 = new Thread(str,"����2");
		Thread t3 = new Thread(str,"����3");
		t1.start();
		t2.start();
		t3.start();
	}
	
}

class ThreadLocalThread implements Runnable {
	 
	//ͨ�������ڲ��า��ThreadLocal��initialValue()������ָ����ʼֵ  
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
			System.out.println(Thread.currentThread().getName() + "���ڳ��۵�" + (seqNum.get()) + "��Ʊ");
			seqNum.set(seqNum.get()-1);
		}
	}
}
