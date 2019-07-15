package socket;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SocketServer {

//	public static void main(String[] args) throws Exception {
//		// ����ָ���Ķ˿�
//		int port = 55533;
//		ServerSocket server = new ServerSocket(port);
//
//		// server��һֱ�ȴ����ӵĵ���
//		System.out.println("server��һֱ�ȴ����ӵĵ���");
//		Socket socket = server.accept();
//		// ���������Ӻ󣬴�socket�л�ȡ�����������������������ж�ȡ
//		InputStream inputStream = socket.getInputStream();
//		byte[] bytes = new byte[1024];
//		int len;
//		StringBuilder sb = new StringBuilder();
//		// ֻ�е��ͻ��˹ر������������ʱ�򣬷���˲���ȡ�ý�β��-1
//		while ((len = inputStream.read(bytes)) != -1) {
//			// ע��ָ�������ʽ�����ͷ��ͽ��շ�һ��Ҫͳһ������ʹ��UTF-8
//			sb.append(new String(bytes, 0, len, "UTF-8"));
//		}
//		System.out.println("get message from client: " + sb);
//
//		OutputStream outputStream = socket.getOutputStream();
//		outputStream.write("Hello Client,I get the message.".getBytes("UTF-8"));
//
//		inputStream.close();
//		outputStream.close();
//		socket.close();
//		server.close();
//
//	}
	
	
	/**
	 * ѭ������socket��Ϣ��Ϊ��ֹ�߲�������ʹ���̳߳�
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// ����ָ���Ķ˿�
		int port = 55533;
		ServerSocket server = new ServerSocket(port);
		// server��һֱ�ȴ����ӵĵ���
		System.out.println("server��һֱ�ȴ����ӵĵ���");

		// ���ʹ�ö��̣߳��Ǿ���Ҫ�̳߳أ���ֹ��������ʱ���������̺߳ľ���Դ
		ExecutorService threadPool = Executors.newFixedThreadPool(100);

		while (true) {
			final Socket socket = server.accept();

			Runnable runnable = new Runnable() {
				public void run() {
					try {
						// ���������Ӻ󣬴�socket�л�ȡ�����������������������ж�ȡ
						InputStream inputStream = socket.getInputStream();
						byte[] bytes = new byte[1024];
						int len;
						StringBuilder sb = new StringBuilder();
						while ((len = inputStream.read(bytes)) != -1) {
							// ע��ָ�������ʽ�����ͷ��ͽ��շ�һ��Ҫͳһ������ʹ��UTF-8
							sb.append(new String(bytes, 0, len, "UTF-8"));
						}
						System.out.println("get message from client: " + sb);
						inputStream.close();
						socket.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			threadPool.submit(runnable);
			System.out.println("============new "+"====="+((ThreadPoolExecutor)threadPool).getActiveCount());
		}

	}
	
}
