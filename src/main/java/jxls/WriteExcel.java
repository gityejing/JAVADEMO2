package jxls;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class WriteExcel {

    public static void write(List list) {
        List students = new ArrayList();
        Map beans = new HashMap();
        beans.put("students", list);
        XLSTransformer transformer = new XLSTransformer();
        String path = ReadExcel.class.getResource("/").getPath();
        path = path.substring(1, path.indexOf("/WebRoot") + 1) + "WebRoot/Excel/";
        try {
            transformer.transformXLS(path + "/student.xls", beans, path + "/stus.xls");
        } catch (ParsePropertyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException ex) {
            Logger.getLogger(WriteExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//	public List getStudetns(){
//		List<Student> list=new ArrayList<Student>();
//		Student stu=null;
//		PreparedStatement pre=null;
//		ResultSet re=null;
//		try{
//			pre=DBConector.getCon().prepareStatement("select * from student");
//			re=pre.executeQuery();
//			while(re.next()){
//				stu=new Student();
//				stu.setId(re.getLong(1));
//				stu.setName(re.getString(2));
//				stu.setSubject(re.getString(3));
//				stu.setScore(re.getLong(4));
//				list.add(stu);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			try{
//				if(re!=null)
//					re.close();
//				if(pre!=null)
//					pre.close();
//			}catch(Exception e){
//			}
//			
//		}
//		return list;
//	}

    public static void main(String[] args) {
        WriteExcel w = new WriteExcel();
//		w.write(w.getStudetns());
    }
}
