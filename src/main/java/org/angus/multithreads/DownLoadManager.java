package org.angus.multithreads;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownLoadManager {
	/*** 每个线程下载的字节数:1M*/
	static final long unitSize = 1000 * 1024; 

	/**
	 * 启动多个线程下载文件
	 * @param remoteFileUrl 远程文件url
	 * @param localDir 本地文件路径
	 * @throws java.io.IOException
	 */
	public void doDownload(String remoteFileUrl,String localDir) throws IOException {
		String fileName = new URL(remoteFileUrl).getFile();// 远程文件的文件名
		fileName = localDir+fileName.substring(fileName.lastIndexOf("/") + 1,fileName.length()).replace("%20", " ");// 最终下载到本地的文件目录和文件名
		long fileSize = this.getRemoteFileSize(remoteFileUrl);
		if (fileSize == 0) {
			return;
		}
		this.createFile(fileName, fileSize);
		long threadCount = fileSize / unitSize;// 需要启动的线程的数量
		long offset = 0;
		if (fileSize <= unitSize) {// 如果远程文件尺寸小于等于unitSize
			threadCount = 1;
		} else {// 如果远程文件尺寸大于unitSize
			if (fileSize % unitSize != 0) threadCount+=1;
		}
		System.out.println("共启动 :"+ threadCount + " 个线程");
		ExecutorService e = Executors.newFixedThreadPool((int)threadCount);
		for (int i = 1; i <= threadCount; i++) {
			DownloadThread downloadThread = new DownloadThread(remoteFileUrl, fileName, offset, unitSize);
			e.execute(downloadThread);
			offset += unitSize;
		}
		e.shutdown();
		System.out.println("======================>下载完成");
	}

	/**
	 * 获取远程文件尺寸
	 * @param remoteFileUrl 远程文件名
	 * @return
	 * @throws java.io.IOException
	 */
	private long getRemoteFileSize(String remoteFileUrl) throws IOException {
		long fileSize = 0;
		HttpURLConnection httpConnection = (HttpURLConnection) new URL(remoteFileUrl).openConnection();
		httpConnection.setRequestMethod("HEAD");
		int responseCode = httpConnection.getResponseCode();
		if (responseCode >= 400) {
			System.out.println("Web服务器响应错误!");
			return 0;
		}
		String sHeader;
		for (int i = 1;; i++) {
			sHeader = httpConnection.getHeaderFieldKey(i);
			if (sHeader != null && sHeader.equals("Content-Length")) {
				System.out.println("文件大小ContentLength:"+ httpConnection.getContentLength());
				fileSize = Long.parseLong(httpConnection.getHeaderField(sHeader));
				break;
			}
		}
		return fileSize;
	}

	/*** 创建指定大小的文件*/
	private void createFile(String fileName, long fileSize) throws IOException {
		File newFile = new File(fileName);
		RandomAccessFile raf = new RandomAccessFile(newFile, "rw");
		raf.setLength(fileSize);
		raf.close();
	}
}
