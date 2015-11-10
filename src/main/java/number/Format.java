package number;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.junit.Test;

public class Format {
	// ��ȡDecimalFormat�ķ���DecimalFormat.getInstance();
	@Test
	public void test1() {
		DecimalFormat df = new DecimalFormat();
		// Ĭ����ʾ3λС��
		double d = 1.5555555;
		System.out.println(df.format(d));// 1.556
		// ����С��������λ��Ϊ5
		df.setMaximumFractionDigits(5);
		df.setMinimumIntegerDigits(15);
		System.out.println(df.format(d));// 1.55556
		df.setMaximumFractionDigits(2);
		System.out.println(df.format(d));// 1.56
		// ����С�������Сλ����������ʱ��0
		df.setMinimumFractionDigits(10);
		System.out.println(df.format(d));// 1.5555555500
		// ��������������С����Ϊ3��������ʱ��0
		df.setMinimumIntegerDigits(3);
		System.out.println(df.format(d));
		// �����������ֵ����ֵΪ2����������ʱ���Ӹ�λ����ʼȡ��Ӧ��λ��
		df.setMaximumIntegerDigits(2);
		System.out.println(df.format(d));
	}

	@Test
	public void test2() {
		DecimalFormat df = new DecimalFormat();
		int number = 155566;
		// Ĭ��������������һ�飬
		System.out.println(number);// �����ʽ155,566
		// ����ÿ�ĸ�һ��
		df.setGroupingSize(4);
		System.out.println(df.format(number));// �����ʽΪ15,5566
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		// ����С����ָ���
		dfs.setDecimalSeparator(';');
		// ���÷���ָ���
		dfs.setGroupingSeparator('a');
		df.setDecimalFormatSymbols(dfs);
		System.out.println(df.format(number));// 15a5566
		System.out.println(df.format(11.22));// 11;22
		// ȡ������
		df.setGroupingUsed(false);
		System.out.println(df.format(number));
	}

	@Test
	public void test3() {
		DecimalFormat df = new DecimalFormat();
		double a = 1.220;
		double b = 11.22;
		double c = 0.22;
		// ռλ������ʹ��0��#���֣���ʹ��0��ʱ����ϸ�����ʽ������ƥ�䣬������ʱ��Ჹ0����ʹ��#ʱ�Ὣǰ���0���к���
		// ���ٷֱȽ������
		// df.applyPattern("00.00%");
		df.applyPattern("##.##%");
		System.out.println(df.format(a));// 122%
		System.out.println(df.format(b));// 1122%
		System.out.println(df.format(c));// 22%
		double d = 841292452021.22222222;
		// ���̶���ʽ�������
		df.applyPattern("00.000");
		System.out.println(df.format(d));// 01.222
		df.applyPattern("##,###,###,###.0000");
		System.out.println(df.format(d));// 1.222
	}
}
