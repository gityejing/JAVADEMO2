package concurrent;

public class FileDownLoadTest {
	public static void main(String[] args) throws Exception {
		long begin = System.currentTimeMillis();
		new DownLoadUtil().download("http://test1.com");
		System.out.println(System.currentTimeMillis() - begin);
	}
}
