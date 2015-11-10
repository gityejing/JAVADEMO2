package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class ProcessBuilderTest {
	public static void restart() throws IOException {
		// Runtime ����
		Process p;
		// test.bat�е�������ipconfig/all
		String cmd = "c:\\test\\test.bat";

		try {
			// ִ������
			p = Runtime.getRuntime().exec(cmd);
			// ȡ���������������
			InputStream fis = p.getInputStream();
			// ��һ�����������ȥ��
			InputStreamReader isr = new InputStreamReader(fis);
			// �û���������
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			// ֱ������Ϊֹ
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ProcessBuilder ���� Java����������
		// ��һ��ָ��������ȥ����һ������������
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", "Test3.jar");
		// ��������̵Ĺ������ռ��ΪF:\dist
		// �����Ļ�,���ͻ�ȥF:\distĿ¼����Test.jar����ļ�
		pb.directory(new File("F:\\dist"));
		// �õ������������Ļ��� ����,����������ǿ��Ը�,
		// �����Ժ�Ҳ�ᷴӦ������Ľ�������ȥ
		Map<String, String> map = pb.environment();
		Process p1 = pb.start();
		// Ȼ��Ϳ��Զ�p���Լ�������������
		// �Լ����ʱ��Ϳ����˳���
		System.exit(0);
	}
}
