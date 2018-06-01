package excel.export;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
/**
 * 实用jxl进行excel的导入和到处
 * 
 * @author 30000133
 * 
 * <dependency>
			<groupId>net.sourceforge.jexcelapi</groupId>
			<artifactId>jxl</artifactId>
			<version>2.6.12</version>
		</dependency>
 *
 */
public class MainTest {
	 public static void main(String args[]) throws Exception {
	        try {
	            List<Student> students = new ArrayList<Student>();
	            Student s1 = new Student();
	            s1.setId(1);
	            s1.setName("Tom");
	            s1.setScore(78);
	            students.add(s1);

	            Student s2 = new Student();
	            s2.setId(2);
	            s2.setName("Hanks");
	            s2.setScore(56);
	            students.add(s2);

	            Student s3 = new Student();
	            s3.setId(3);
	            s3.setName("jerry");
	            s3.setScore(99);
	            students.add(s3);

	            LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
	            fieldMap.put("id", "编号");
	            fieldMap.put("name", "姓名");
	            fieldMap.put("score", "分数");
	            File osFile = new File("e:/test2.xls");
	            FileOutputStream fos = new FileOutputStream(osFile);
	            ExcelUtils.listToExcel(students, fieldMap, "studentScore", fos);
	            System.out.println("download success!");
	        } catch (Exception e) {
	            throw new Exception("export error:" + e.getMessage());
	        }
	    }
}
