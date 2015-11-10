package jxls;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xml.sax.SAXException;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ReadExcel {

    private final static String xmlConfig = "student.xml"; // xml 模板文件
    private final static String file1_path = "stus.xls";   // 被读取的文件1
    
    public List read(){
        XLSReader mainReader;
        InputStream inputXML = new BufferedInputStream(ReadExcel.class.getResourceAsStream(xmlConfig));
        InputStream file1 = new BufferedInputStream(ReadExcel.class.getResourceAsStream(file1_path));
        try {
            mainReader = ReaderBuilder.buildFromXML(inputXML);
            Student stu = new Student();
            List students = new ArrayList();
            Map beans = new HashMap();
            beans.put("stu", stu);
            beans.put("students", students);
            mainReader.read(file1, beans);
            return students;
        } catch (Exception e) {
            Logger.getLogger(ReadExcel.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static void main(String[] args) {
        ReadExcel re = new ReadExcel();
        List<Student> list = re.read();
        for (Student stu : list) {
            System.out.println(stu);
        }
    }
}
