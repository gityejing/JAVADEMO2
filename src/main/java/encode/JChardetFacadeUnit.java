package encode;

import info.monitorenter.cpdetector.io.*;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Created by yejing on 2015/4/3.
 * 工具类，用来获取文本文件使用的字符集
 */
public class JChardetFacadeUnit {

    public static String getFileEncode(String fileName){
        /*------------------------------------------------------------------------
        detector是探测器，它把探测任务交给具体的探测实现类的实例完成。
        cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法
        加进来，如ParsingDetector、 JChardetFacade、ASCIIDetector、UnicodeDetector。
        detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的
        字符集编码。
        --------------------------------------------------------------------------*/
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        /*-------------------------------------------------------------------------
        ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
        指示是否显示探测过程的详细信息，为false不显示。
        ---------------------------------------------------------------------------*/
        detector.add(new ParsingDetector(false));//如果不希望判断xml的encoding，而是要判断该xml文件的编码，则可以注释掉
        /*--------------------------------------------------------------------------
        JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码测定。
        所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以
        再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
        ---------------------------------------------------------------------------*/
        detector.add(JChardetFacade.getInstance());
        // ASCIIDetector用于ASCII编码测定
        detector.add(ASCIIDetector.getInstance());
        // UnicodeDetector用于Unicode家族编码的测定
        detector.add(UnicodeDetector.getInstance());
        Charset charset = null;
        File f = new File(fileName);
        try {
            charset = detector.detectCodepage(f.toURL());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (charset != null) {
            System.out.println(f.getName() + "编码是：" + charset.name());
            return charset.name();
        } else {
            System.out.println(f.getName() + "未知");
            return "未知";
        }
    }

    public static String getFileEncode(File file){
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        detector.add(new ParsingDetector(false));//如果不希望判断xml的encoding，而是要判断该xml文件的编码，则可以注释掉
        detector.add(JChardetFacade.getInstance());
        detector.add(ASCIIDetector.getInstance());
        detector.add(UnicodeDetector.getInstance());
        Charset charset = null;
        try {
            charset = detector.detectCodepage(file.toURL());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (charset != null) {
            System.out.println(file.getName() + "编码是：" + charset.name());
            return charset.name();
        } else {
            System.out.println(file.getName() + "未知");
            return "未知";
        }
    }

}
