package concurrent.multThreadDownload;

import java.io.File;

public class FileDownLoadTest {
	public static void main(String[] args) throws Exception {
		long begin = System.currentTimeMillis();
		File target = new File("d://a.jpg");
		new DownLoadUtil().download("http://pic7.nipic.com/20100603/668573_211435005500_2.jpg",target);
		System.out.println(System.currentTimeMillis() - begin);
	}
}
