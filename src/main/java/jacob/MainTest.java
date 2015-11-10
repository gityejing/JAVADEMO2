package jacob;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class MainTest {
	public static void main(String[] args) {
		word2PDF("D:\\Requirement.docx","D:\\Requirement_1.pdf");
	}

	public static void word2PDF(String inputFile, String pdfFile) {
		// 打开word应用程序
		ActiveXComponent app = new ActiveXComponent("Word.Application");
		// 设置word不可见
		app.setProperty("Visible", false);
		// 获得word中所有打开的文档,返回Documents对象
		Dispatch docs = app.getProperty("Documents").toDispatch();
		// 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
		Dispatch doc = Dispatch.call(docs, "Open", inputFile, false, true).toDispatch();
		// 调用Document对象的SaveAs方法，将文档保存为pdf格式
		Dispatch.call(doc,"ExportAsFixedFormat", pdfFile, 17);
		// 关闭文档
		Dispatch.call(doc, "Close", false);
		// 关闭word应用程序
		app.invoke("Quit", 0);
	}
}
