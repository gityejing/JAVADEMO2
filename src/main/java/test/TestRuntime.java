package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Arrays;

import jodd.props.Props;

public class TestRuntime {

	static Props props = new Props();
	static{
		try {
//			file:/E:/MyEclipse%208.6/javaTest/bin/test/   空格会被编译成%20，需要进行处理
//			file:/E:/MyEclipse%208.6/javaTest/bin/
			System.out.println(URLDecoder.decode(TestRuntime.class.getResource("").getPath(), "utf-8"));// 从当前文件所在路径开始查找
			System.out.println(URLDecoder.decode(TestRuntime.class.getResource("/").getPath(), "utf-8"));// 从类路径下开始查找
			String url = Thread.currentThread().getContextClassLoader().getResource("SystemGlobals.properties").getPath();
			FileInputStream fis = new FileInputStream(URLDecoder.decode(url,"utf-8"));  
			props.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test1(String[] args) throws Exception{
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec("cmd /c dir", null, new File("e:/"));
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"GBK");
		BufferedReader br = new BufferedReader(isr);
		String line;

		System.out.printf("Output of running %s is:", Arrays.toString(args));

		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	}
	
	
	public static void test2(String[] args) throws Exception{
		ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "dir"); 
		builder.directory(new File("e:/")); 
		Process process = builder.start(); 
		InputStream is = process.getInputStream(); 
		InputStreamReader isr = new InputStreamReader(is, "GBK"); 
		BufferedReader br = new BufferedReader(isr); 
		String line; 

		System.out.printf("Output of running %s is:", Arrays.toString(args)); 

		while ((line = br.readLine()) != null) { 
		System.out.println(line); 
		} 
	}
	
	public static void test3(String[] args) throws Exception{
		String url = "http://www.baidu.com";
		String fileName = "c:/baidu.pdf";
		try {
			ProcessBuilder pbuilder = new ProcessBuilder(props.getValue("wkhtmltopdf"),url, fileName);
			pbuilder.redirectErrorStream(true);
			Process p = pbuilder.start();
			BufferedReader reader = null;
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println("++++++++++++++++" + line);
			}
			int result = p.waitFor();
			System.out.println("完成：" + fileName + "-" + result);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
//		test1(args);
		test3(args);
	}
	
}
