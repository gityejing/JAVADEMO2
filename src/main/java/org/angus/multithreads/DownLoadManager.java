package org.angus.multithreads;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownLoadManager {
	/*** ÿ���߳����ص��ֽ���:1M*/
	static final long unitSize = 1000 * 1024; 

	/**
	 * ��������߳������ļ�
	 * @param remoteFileUrl Զ���ļ�url
	 * @param localDir �����ļ�·��
	 * @throws java.io.IOException
	 */
	public void doDownload(String remoteFileUrl,String localDir) throws IOException {
		String fileName = new URL(remoteFileUrl).getFile();// Զ���ļ����ļ���
		fileName = localDir+fileName.substring(fileName.lastIndexOf("/") + 1,fileName.length()).replace("%20", " ");// �������ص����ص��ļ�Ŀ¼���ļ���
		long fileSize = this.getRemoteFileSize(remoteFileUrl);
		if (fileSize == 0) {
			return;
		}
		this.createFile(fileName, fileSize);
		long threadCount = fileSize / unitSize;// ��Ҫ�������̵߳�����
		long offset = 0;
		if (fileSize <= unitSize) {// ���Զ���ļ��ߴ�С�ڵ���unitSize
			threadCount = 1;
		} else {// ���Զ���ļ��ߴ����unitSize
			if (fileSize % unitSize != 0) threadCount+=1;
		}
		System.out.println("������ :"+ threadCount + " ���߳�");
		ExecutorService e = Executors.newFixedThreadPool((int)threadCount);
		for (int i = 1; i <= threadCount; i++) {
			DownloadThread downloadThread = new DownloadThread(remoteFileUrl, fileName, offset, unitSize);
			e.execute(downloadThread);
			offset += unitSize;
		}
		e.shutdown();
		System.out.println("======================>�������");
	}

	/**
	 * ��ȡԶ���ļ��ߴ�
	 * @param remoteFileUrl Զ���ļ���
	 * @return
	 * @throws java.io.IOException
	 */
	private long getRemoteFileSize(String remoteFileUrl) throws IOException {
		long fileSize = 0;
		HttpURLConnection httpConnection = (HttpURLConnection) new URL(remoteFileUrl).openConnection();
		httpConnection.setRequestMethod("HEAD");
		int responseCode = httpConnection.getResponseCode();
		if (responseCode >= 400) {
			System.out.println("Web��������Ӧ����!");
			return 0;
		}
		String sHeader;
		for (int i = 1;; i++) {
			sHeader = httpConnection.getHeaderFieldKey(i);
			if (sHeader != null && sHeader.equals("Content-Length")) {
				System.out.println("�ļ���СContentLength:"+ httpConnection.getContentLength());
				fileSize = Long.parseLong(httpConnection.getHeaderField(sHeader));
				break;
			}
		}
		return fileSize;
	}

	/*** ����ָ����С���ļ�*/
	private void createFile(String fileName, long fileSize) throws IOException {
		File newFile = new File(fileName);
		RandomAccessFile raf = new RandomAccessFile(newFile, "rw");
		raf.setLength(fileSize);
		raf.close();
	}
}
