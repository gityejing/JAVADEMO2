package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class ProcessBuilderTest {
	public static void restart() throws IOException {
		// Runtime 例子
		Process p;
		// test.bat中的命令是ipconfig/all
		String cmd = "c:\\test\\test.bat";

		try {
			// 执行命令
			p = Runtime.getRuntime().exec(cmd);
			// 取得命令结果的输出流
			InputStream fis = p.getInputStream();
			// 用一个读输出流类去读
			InputStreamReader isr = new InputStreamReader(fis);
			// 用缓冲器读行
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			// 直到读完为止
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ProcessBuilder 例子 Java程序自重启
		// 用一条指定的命令去构造一个进程生成器
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", "Test3.jar");
		// 让这个进程的工作区空间改为F:\dist
		// 这样的话,它就会去F:\dist目录下找Test.jar这个文件
		pb.directory(new File("F:\\dist"));
		// 得到进程生成器的环境 变量,这个变量我们可以改,
		// 改了以后也会反应到新起的进程里面去
		Map<String, String> map = pb.environment();
		Process p1 = pb.start();
		// 然后就可以对p做自己想做的事情了
		// 自己这个时候就可以退出了
		System.exit(0);
	}
}
