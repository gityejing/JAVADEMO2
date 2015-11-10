package org.angus.multithreads;

public class FileDownloadTest {
	public static void main(String[] args) {
		try {
			String remoteFileUrl = "http://dl.maxthon.cn/cn/mx2/mx_2.5.1.4751cn.exe";
			DownLoadManager downLoadManager = new DownLoadManager();
			downLoadManager.doDownload(remoteFileUrl,"D:/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
