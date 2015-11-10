package org.angus.multithreads;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadThread extends Thread {
	
	private String url = null;/*** �����ص��ļ�*/
	private String fileName = null;/*** �����ļ���*/
	private long offset = 0;/*** ƫ����*/
	private long length = 0;/*** ��������̵߳������ֽ���*/

	public DownloadThread(String url, String file, long offset, long length) {
		this.url = url;
		this.fileName = file;
		this.offset = offset;
		this.length = length;
		System.out.println("ƫ����=" + offset + ";�ֽ���=" + length);
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
				raf.seek(this.offset);// ���ļ�ָ���ƶ���ָ����ƫ������
				raf.write(buff, 0, bytesRead);
				this.offset = this.offset + bytesRead;// �޸�ƫ����
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
