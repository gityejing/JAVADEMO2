package socket.multiSocket;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadClient {
	public static void main(String[] args) {
		int numTasks = 10;
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < numTasks; i++) {
			exec.execute(createTask(i));
		}
	}

	private static Runnable createTask(final int taskID) {
		return new Runnable() {

			private Socket socket = null;
			private int port = 8821;

			@Override
			public void run() {
				System.out.println("Task " + taskID + ":start");
				try {
					socket = new Socket("localhost", port);
					OutputStream socketOut = socket.getOutputStream();
					socketOut.write("shutdown\r\n".getBytes());
					BufferedReader br = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
					String msg = null;
					while ((msg = br.readLine()) != null) {
						System.out.println(msg);
					}
				} catch (Exception e) {
				}
			}
		};

	}
}

