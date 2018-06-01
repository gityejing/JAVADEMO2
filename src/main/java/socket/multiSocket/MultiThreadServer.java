package socket.multiSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer{
	private int port = 8821;
	private ServerSocket serverSocket;
	private ExecutorService executorService;
	private final int POOL_SIZE = 10;
	
	public MultiThreadServer() throws IOException{
		serverSocket = new ServerSocket(port);
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
		System.out.println("服務器啟動成功！");
	}
	
	public void service(){
		while (true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				executorService.execute(new Handler(socket));
			} catch (Exception e) {
				
			}
		}
	}
	
	public static void main(String[] args) {
		MultiThreadServer multiThreadServer;
		try {
			multiThreadServer = new MultiThreadServer();
			multiThreadServer.service();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}