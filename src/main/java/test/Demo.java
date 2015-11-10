package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {
	public static void main(String[] args) {
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 Date d = new Date();
		 System.out.println(df.format(d));
		 System.out.println(d.getDay());
		 System.out.println(d.getDate());
		 Calendar c = Calendar.getInstance();

		 c.get(Calendar.DATE);

		 System.out.println(c.get(Calendar.DATE));

		String str = "2014-17";
		System.out.println(str.substring(0, str.lastIndexOf("-")));

		Pattern p = Pattern.compile("cat");
		Matcher m = p.matcher("one cat two cats in the yard");
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "dog");
		}
		m.appendTail(sb);
		System.out.println(sb.toString());
		
		String s = "djdjdd,2efef,efqaerfw,efqefq111,";
		s = s.substring(0,s.lastIndexOf(","));
		System.out.println(s);
		
            
	}
}
