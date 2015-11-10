package jodd.encode;


import jodd.util.Base32;
import jodd.util.Base64;

public class BaseEAD {
	public static void main(String[] args) {
		String str = "hello";
		str = Base32.encode(str.getBytes());
		System.out.println(str);
		str = Base64.encodeToString(str.getBytes());
		System.out.println(str);
		String dStr = "aGVsbG8=";
		byte[] b = Base64.decode(dStr.getBytes());
		System.out.println(new String(b));
		str = Base64.encodeToString(str);
		System.out.println(str);
		str = Base64.decodeToString("aGVsbG8=");
		System.out.println(str);
	}
}
