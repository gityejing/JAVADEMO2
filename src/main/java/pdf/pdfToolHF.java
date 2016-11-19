/*     package pdf;

     
     import com.lowagie.text.Document;
     
import com.lowagie.text.HeaderFooter;
     
import com.lowagie.text.Phrase;
     
import com.lowagie.text.pdf.PdfWriter;
     
import com.sinosoft.style.pdf.FileStyle;
     
import com.sinosoft.style.pdf.FooterStyle;
     
import java.io.PrintStream;
     
import java.util.Vector;

     
     public class pdfToolHF extends pdfTool
     {
	     public boolean makeFile(String tFilePath, FileStyle fs)
	     {
		 40 Vector vctGetReData = makeFileHfFun(this.document,
				this.pdfWriter, tFilePath, fs);
		 41 if (vctGetReData.size() != 2) {
			 42 return false;
			     }
		 44 this.document = ((Document) vctGetReData.get(0));
		 45 this.pdfWriter = ((PdfWriter) vctGetReData.get(1));
		 46 return true;
		     }

	     
	     public boolean makeFooter(String content, FooterStyle fs)
	     {
		     try
		     {
			 63 if (!fs.isContinue()) {
				 64 this.document.resetPageCount();
				     }
			 66 HeaderFooter footer = makeFoot(content, fs);
			 67 this.document.setFooter(footer);
			 68 System.out.println("yejiao success !");
			 69 this.document.open();
			     } catch (Exception ex) {
			 71 System.err.println(ex.getMessage());
			 72 return false;
			     }
		 74 return true;
		     }

	     
	     public pdfToolHF() {
		     }

	     
	     public pdfToolHF(String path) {
		 81 this.path = path;
		     }

	     
	     public HeaderFooter makeFoot(String content, FooterStyle fs) {
		 85 HeaderFooter footer = null;
		     try {
			 87 Phrase phrase = null;
			 88 Phrase afterPhrase = null;
			 89 Phrase paragraph = new Phrase(content,
					getFontChineseFun(fs));
			 90 if (fs.getBumbered())
				 91 footer = new HeaderFooter(paragraph, afterPhrase);
			     else {
				 93 footer = new HeaderFooter(paragraph, fs.getBumbered());
				     }
			     
			 96 footer.setBorder(fs.getBorder());
			     }
		     catch (Exception localException)
		     {
			     }
		 101 return footer;
		     }
	     
}


 * Location: F:\新建议书单机测试版\新建议书单机测试版\classes\ Qualified Name:
 * com.sinosoft.utility.pdfToolHF JD-Core Version: 0.6.2
 */