package jodd.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;

import jodd.props.Props;
import jodd.props.PropsEntries;
import jodd.props.PropsEntry;

public class Prop {

    public static void main(String[] args) {
        Props props = new Props();
        try {
            FileInputStream fis = null;
			// ����һ��
//			String url = Thread.currentThread().getContextClassLoader().getResource("SystemGlobals.properties").getPath();
//			URI uri = new URI(url);   
//			fis = new FileInputStream(uri.getPath());  
//			System.out.println(uri.getPath());
            // ��������
            String url2 = Prop.class.getClassLoader().getResource("SystemGlobals.properties").getPath();
            url2 = URLDecoder.decode(url2, "utf-8");
            System.out.println(url2);
            fis = new FileInputStream(url2);

            props.load(fis);
            PropsEntries ens = props.entries();
            Iterator<PropsEntry> iterator = ens.iterator();
            while (iterator.hasNext()) {
                PropsEntry propsEntry = iterator.next();
                System.out.println(propsEntry.getKey());
                System.out.println(propsEntry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
