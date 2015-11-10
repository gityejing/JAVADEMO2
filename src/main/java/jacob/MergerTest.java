package jacob;

import java.util.ArrayList;
import java.util.List;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class MergerTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		String file1 = "D:\\file1.docx";
		String file2 = "D:\\file2.docx";
		String file3 = "D:\\file3.docx";
		list.add(file1);
		list.add(file2);
		list.add(file3);
		uniteDoc(list, "d:\\file.docx");
	}

	public static void uniteDoc(List<String> fileList, String savepaths) {
		if (fileList.size() == 0 || fileList == null) {
			return;
		}
		// 打开word
		ActiveXComponent app = new ActiveXComponent("Word.Application");// 启动word
		try {
			// 设置word不可见
			app.setProperty("Visible", new Variant(false));
			// 获得documents对象
			Dispatch docs = app.getProperty("Documents").toDispatch();
			// 打开第一个文件
			Dispatch doc = Dispatch.call(docs, "Open", fileList.get(0),false, true).toDispatch();
			
			Dispatch doc2 = app.getProperty("Selection").toDispatch();
			// 追加文件
			for (int i = 1; i < fileList.size(); i++) {
				Dispatch.call(doc2,"insertFile",fileList.get(i),false,false,false);
			}
			// 保存新的word文件
			Dispatch.call(doc, "SaveAs",savepaths,1);
			Dispatch.call(doc, "Close",false);
		} catch (Exception e) {
			throw new RuntimeException("合并word文件出错.原因:" + e);
		} finally {
			app.invoke("Quit",0);
		}
	}
}
