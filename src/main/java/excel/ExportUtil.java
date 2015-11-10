package excel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExportUtil {
	public static void excleOut(ArrayList ar,String str){
		WritableWorkbook book = null;
		try {
			book = Workbook.createWorkbook(new File(str));
			WritableSheet sheet = book.createSheet("sheet", 0);
			for (int i = 0; i < ar.size(); i++) {
				Object ob = ar.get(i);
				Class cls = ob.getClass();
				Field[] fi = cls.getDeclaredFields();
				for (int j = 0; j < fi.length; j++) {
					fi[j].setAccessible(true);
					Label la = new jxl.write.Label(j, i, String.valueOf(fi[j].get(ob)));
					sheet.addCell(la);
				}
			}
			book.write();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(book != null) book.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static ArrayList excleIn(Class cl,String str){
		ArrayList ar = new ArrayList();
		Workbook book = null;
		try {
			book = Workbook.getWorkbook(new File(str));
			Sheet sheet = book.getSheet(0);
			Field[] fi = cl.getDeclaredFields();
			for (int i = 0; i < sheet.getRows(); i++) {
				Object ob = cl.newInstance();
				for (int j = 0; j < fi.length; j++) {
					fi[j].setAccessible(true);
					String co = sheet.getCell(j, i).getContents();
					if(fi[j].getType().toString().equals("class java.lang.String")){
						fi[j].set(ob,co);
					}else if(fi[j].getType().toString().equals("class java.lang.Long")){
						fi[j].set(ob,Long.parseLong(co));
					}
				}
				ar.add(ob);
			}
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			book.close();
		}
		return ar;
	}
	
	public static void main(String[] args) {
//		ArrayList<Foo> foos = new ArrayList<>();
//		Foo foo = new Foo();
//		foo.setId(1000L);
//		foo.setRemark("foo1");
//		foos.add(foo);
//		Foo foo2 = new Foo();
//		foo2.setId(1000L);
//		foo2.setRemark("foo2");
//		foos.add(foo2);
//		ExportUtil.excleOut(foos, "d://foo.xls");
		
		ArrayList ar = ExportUtil.excleIn(Foo.class, "d://foo.xls");
		System.out.println(ar);
		
	}
}
