package threadDown;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownLoadUtil {
	private static final int TCOUNT = 10;
	private CountDownLatch latch = new CountDownLatch(TCOUNT);// 倒数同步器
	private long completeLength = 0;
	private long fileLength;// 文件的字节长度

	public void download(String urlAdress) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(TCOUNT);
		URL url = new URL(urlAdress);
		URLConnection cn = url.openConnection();
		cn.setRequestProperty("Referer", "http:// www.2cto.com ");
		fileLength = cn.getContentLength();// 文件长度
		long packageLength = fileLength / TCOUNT;// 计算每一个线程下载的文件段的大小，取整数，如果无法整除的话，最后还要多加一个线程
		long leftLength = fileLength % TCOUNT;
		RandomAccessFile file = new RandomAccessFile("test.rar", "rw");
		Range<Long> r = new Range<Long>(0L,packageLength);
		// 计算每个线程请求文件的开始和结束位置
		long pos = 0;// 每个下载线程，下载的开始位置
		long endPos = pos + packageLength;// 每个下载线程，下载的结束位置
		for (int i = 0; i < TCOUNT; i++) {
			if (leftLength > 0) {
				endPos++;
				leftLength--;
			}
			service.execute(new DownLoadThread(url, file, pos, endPos));
			pos = endPos;
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
	
	protected class Range<T> {
		T index;
		T length;
		
		public Range() {
			super();
		}
		
		public Range(T index, T length) {
			super();
			this.index = index;
			this.length = length;
		}
		public T getIndex() {
			return index;
		}
		public void setIndex(T index) {
			this.index = index;
		}
		public T getLength() {
			return length;
		}
		public void setLength(T length) {
			this.length = length;
		}
	}
}
