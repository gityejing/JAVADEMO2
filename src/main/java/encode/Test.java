package encode;

import IO.IOUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2015/4/3.
 */
public class Test {
    public static void main(String args[]){
        String path = "E:\\intellij\\audit\\Demo\\src\\encode\\EncodingConverter.java";
        String encode = JChardetFacadeUnit.getFileEncode(path);
        try {
            String content = IOUtils.read(path,encode);
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String content = "中文内容";
        path = "c:/test.txt";
        String encoding = "utf-8";
        try {
            IOUtils.write(path, content, encoding);
            System.out.println(IOUtils.read(path, encoding));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
