package concurrent.multThreadDownload;

import java.io.File;

public class FileDownLoadTest {
	public static void main(String[] args) throws Exception {
		long begin = System.currentTimeMillis();
		File target = new File("d://第25集_bd.mp4");
		new DownLoadUtil().download("http://dl94.80s.im:920/1701/[心LZ2]第25集/[心LZ2]第25集_bd.mp4",target);
		System.out.println(System.currentTimeMillis() - begin);
	}
}
