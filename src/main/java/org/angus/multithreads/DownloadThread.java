package org.angus.multithreads;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadThread extends Thread {
	
	private String url = null;/*** 待下载的文件*/
	private String fileName = null;/*** 本地文件名*/
	private long offset = 0;/*** 偏移量*/
	private long length = 0;/*** 分配给本线程的下载字节数*/

	public DownloadThread(String url, String file, long offset, long length) {
		this.url = url;
		this.fileName = file;
		this.offset = offset;
		this.length = length;
		System.out.println("偏移量=" + offset + ";字节数=" + length);
	}

	public void run() {
		try {
			HttpURLConnection httpConnection = (HttpURLConnection) new URL(this.url).openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("RANGE", "bytes=" + this.offset+ "-" + (this.offset + this.length - 1));
			System.out.println("RANGE bytes=" + this.offset + "-"+ (this.offset + this.length - 1));
			BufferedInputStream bis = new BufferedInputStream(httpConnection.getInputStream());
			byte[] buff = new byte[1024];
			int bytesRead;
			int sum = 0;
			File newFile = new File(fileName);
			RandomAccessFile raf = new RandomAccessFile(newFile,"rw");
			while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
				raf.seek(this.offset);// 将文件指针移动到指定的偏移量处
				raf.write(buff, 0, bytesRead);
				this.offset = this.offset + bytesRead;// 修改偏移量
				sum+=bytesRead;
				System.out.println("bytesRead:"+sum);
			}
			raf.close();
			bis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
