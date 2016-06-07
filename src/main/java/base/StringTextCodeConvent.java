package base;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class StringTextCodeConvent {

	public static void main(String[] args) {

	}

	@Test
	public void test1() {
		String u = "\u95ab\u72b1\u73af\u7035\u89c4\u762e";
//		u = GBK2UTF8(u);
//		u = convert(u);
		u = convert("\u95ab\u72b1\u73af\u7035\u89c4\u762e");
		System.out.println(u);
	}

	public static String GBK2UTF8(String gs) {
		try {
			return new String(gs.getBytes("gbk"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			return gs;
		}
	}

	public static String convert(String utfString) {
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;

		while ((i = utfString.indexOf("\\u", pos)) != -1) {
			sb.append(utfString.substring(pos, i));
			if (i + 5 < utfString.length()) {
				pos = i + 6;
				sb.append((char) Integer.parseInt(
						utfString.substring(i + 2, i + 6), 16));
			}
		}
		return sb.toString();
	}

	public static String toGBK(String unicodeStr) {
		try {
			String gbkStr = new String(unicodeStr.getBytes("gbk"), "utf-8");
			return gbkStr;
		} catch (UnsupportedEncodingException e) {
			return unicodeStr;
		}
	}
}
