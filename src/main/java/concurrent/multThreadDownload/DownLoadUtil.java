package concurrent.multThreadDownload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.bouncycastle.util.encoders.UrlBase64;

public class DownLoadUtil {
	
	private static final int TCOUNT = 10;// 线程数也是文件分段数
	private CountDownLatch latch = new CountDownLatch(TCOUNT);
	private long completeLength = 0;
	private long fileLength;

	/**
	 * 从网络上下载数据到指定的文件
	 * @param urlAdress 网络地址
	 * @param target 目标文件
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 */
	public void download(String urlAdress,File target) throws IOException, InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(TCOUNT);
		
		URL url = new URL(urlAdress);
		URLConnection cn = url.openConnection();
		
		fileLength = cn.getContentLength();
		long packageLength = fileLength / TCOUNT;// 每个段的大小
		long leftLength = fileLength % TCOUNT;// 分段之后剩余的字节数  
		
		RandomAccessFile file = new RandomAccessFile(target, "rw");
		
		// 初始偏移量
		long pos = 0;
		long endPos = pos + packageLength;
		
		for (int i = 0; i < TCOUNT; i++) {
			if (leftLength > 0) {
				endPos++;
				leftLength--;
			}
			System.out.println((i+1)+":"+pos+"----"+endPos);
			service.execute(new DownLoadThread(url, file, pos, endPos));
			pos = endPos+1;
			endPos = pos+packageLength-1;
		}
		latch.await();
		service.shutdown();
	}
	
	protected class DownLoadThread implements Runnable {
		
		private URL url;
		private RandomAccessFile file;
		private long from;
		private long end;

		DownLoadThread(URL url, RandomAccessFile file, long from, long end) {
			this.url = url;
			this.file = file;
			this.from = from;
			this.end = end;
		}

		public void run() {
			long pos = from;
			byte[] buf = new byte[512];
			try {
				HttpURLConnection cn = (HttpURLConnection) url.openConnection();
				cn.setRequestProperty("Range", "bytes=" + from + "-" + end);
				if (cn.getResponseCode() != 200 && cn.getResponseCode() != 206) {
					run();
					return;
				}
				BufferedInputStream bis = new BufferedInputStream(cn.getInputStream());
				int len;
				while ((len = bis.read(buf)) != -1) {
					synchronized (file) {
						file.seek(pos);
						file.write(buf, 0, len);
					}
					pos += len;
					completeLength += len;
					System.out.println(completeLength * 100 / fileLength + "%");
				}
				cn.disconnect();
				latch.countDown();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
