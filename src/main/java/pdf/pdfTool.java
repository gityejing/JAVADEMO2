/*package pdf;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

import com.sinosoft.style.pdf.FileStyle;
import com.sinosoft.style.pdf.FooterStyle;
import com.sinosoft.style.pdf.HeaderStyle;
import com.sinosoft.style.pdf.ImageStyle;
import com.sinosoft.style.pdf.TableBodyStyle;
import com.sinosoft.style.pdf.TableHeadStyle;
import com.sinosoft.style.pdf.TextStyle;
import com.sinosoft.style.pdf.TitleStyle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;

public class pdfTool extends PdfToolFun {
	private static final Logger logger = Logger.getLogger(pdfTool.class);
	protected Document document;
	protected PdfWriter pdfWriter;
	protected Table table;
	protected PdfPTable pdfPtable;
	protected Paragraph tempPara;
	private VData headerInfo;
	protected Image tempImage;
	public PdfTemplate tpl;
	public BaseFont helv;
	private int printNum = 0;
	private int recoverNum = 10000;
	private int recoverA4Num = 10000;
	private boolean needRepeat = false;
	private ArrayList<String> rotateList = new ArrayList();

	public boolean makeFile(String tFilePath, FileStyle fs) {
		Vector vector = makeFileFun(this.document, this.pdfWriter, tFilePath,
				fs);
		if (vector == null) {
			return false;
		}
		this.document = ((Document) vector.get(0));
		this.pdfWriter = ((PdfWriter) vector.get(1));
		return true;
	}

	public boolean makeText(String tContent, TextStyle ts) {
		try {
			Paragraph paragraph = makeTextFun(tContent, ts);
			if (ts.isAlone())
				this.document.add(paragraph);
			else
				this.tempPara = paragraph;
		} catch (DocumentException de) {
			logger.error("makeText(String, TextStyle) - " + de.getMessage(), de);
			return false;
		} catch (Exception ex) {
			logger.error("makeText(String, TextStyle) - " + ex.getMessage(), ex);
			return false;
		}
		return true;
	}

	public boolean makeTitle(String title, TitleStyle ts) {
		boolean reBln = makeTitleFun(this.pdfWriter, title, ts);
		return reBln;
	}

	public boolean makeImage(String path, ImageStyle is) {
		try {
			Image image = makeImageFun(path, is);
			if ((image != null) && (is.isAlone()))
				this.document.add(image);
			else
				this.tempImage = image;
		} catch (Exception ex) {
			logger.error("makeImage(String, ImageStyle) - " + ex.getMessage(),
					ex);
			return false;
		}

		return true;
	}

	public boolean makeTableHead(List headers, TableHeadStyle ths,
			boolean tableType) {
		if (tableType) {
			return makePdfPTableHead(headers, ths);
		}

		return false;
	}

	public boolean makeTableHead(String[] colNames, TableHeadStyle ths,
			boolean pdfpTable) {
		if (pdfpTable) {
			return makePdfPTableHead(colNames, ths);
		}
		return makeTableHead(colNames, ths);
	}

	public boolean makeTableHead(String[] colNames, TableHeadStyle ths) {
		this.table = makeTableHeadFun(this.table, colNames, ths);
		if (this.table == null)
			return false;
		try {
			if (ths.haveTableBody())
				this.table.endHeaders();
			else {
				this.document.add(this.table);
			}
		} catch (DocumentException e) {
			logger.error("makeTableHead(String[], TableHeadStyle)", e);
		}
		return true;
	}

	public boolean makePdfPTableHead(String[] colNames, TableHeadStyle ths) {
		try {
			this.pdfPtable = makePdfPTableHeadFun(colNames, ths);
			if (ths.haveTableBody())
				this.pdfPtable.setHeaderRows(ths.getHeaderRows());
			else
				this.document.add(this.pdfPtable);
		} catch (Exception ex) {
			logger.error("makePdfPTableHead(String[], TableHeadStyle)", ex);
		}
		return true;
	}

	public boolean makePdfPTableHead(List headers, TableHeadStyle ths) {
		try {
			this.pdfPtable = makePdfPTableHeadFun(headers, ths);
		} catch (Exception ex) {
			logger.error(
					"makeTableHead(List, TableHeadStyle) - " + ex.getMessage(),
					ex);
			return false;
		}
		return true;
	}

	public boolean makePDFPTableBody(SSRS tSSRS, TableBodyStyle tbs) {
		try {
			makeTableBodyFun(this.pdfPtable, tSSRS, tbs);
			if ((tbs.getBodyType() == 0) || (tbs.getBodyType() == 2))
				this.document.add(this.pdfPtable);
		} catch (Exception ex) {
			logger.error(
					"makeTableBody(SSRS, TableBodyStyle) - " + ex.getMessage(),
					ex);
			return false;
		}
		return true;
	}

	public boolean makePDFPTableBodyH(SSRS tSSRS, TableBodyStyle tbs) {
		try {
			makeTableBodyFunH(this.pdfPtable, tSSRS, tbs);
			if ((tbs.getBodyType() == 0) || (tbs.getBodyType() == 2))
				this.document.add(this.pdfPtable);
		} catch (Exception ex) {
			logger.error(
					"makeTableBody(SSRS, TableBodyStyle) - " + ex.getMessage(),
					ex);
			return false;
		}
		return true;
	}

	public boolean makeTableBody(SSRS tSSRS, TableBodyStyle tbs) {
		try {
			makeTableBodyFun(this.table, tSSRS, tbs);
			if ((tbs.getBodyType() == 0) || (tbs.getBodyType() == 2)) {
				this.document.add(this.table);
			}
		} catch (DocumentException e) {
			logger.error("makeTableBody(SSRS, TableBodyStyle)", e);
			return false;
		}
		return true;
	}

	protected boolean makeOnTableBody(SSRS tSSRS, TableBodyStyle tbs) {
		try {
			PdfPTable pdfPTable = makeOnTableBodyFun(tSSRS, tbs);
			if ((tbs.getBodyType() == 0) || (tbs.getBodyType() == 2))
				this.document.add(pdfPTable);
		} catch (Exception ex) {
			logger.error(
					"makeOnTableBody(SSRS, TableBodyStyle) - "
							+ ex.getMessage(), ex);
			return false;
		}
		return true;
	}

	public boolean makeHeader(String content, HeaderStyle hs) {
		try {
			HeaderFooter headerFooter = makeHeaderFun(content, hs);
			this.document.setHeader(headerFooter);
		} catch (Exception ex) {
			logger.error(
					"makeHeader(String, HeaderStyle) - " + ex.getMessage(), ex);
			if (logger.isDebugEnabled()) {
				logger.debug("makeHeader(String, HeaderStyle) - " + ex);
			}
			return false;
		}
		return true;
	}

	public boolean makeFooter(String content, FooterStyle fs) {
		try {
			if (!fs.isContinue()) {
				this.document.resetPageCount();
			}
			if (!makeFooterFun(content, fs)) {
				logger.debug("makeFooter(String, FooterStyle) - yejiao failed !");
				return false;
			}
			if (logger.isDebugEnabled())
				logger.debug("makeFooter(String, FooterStyle) - yejiao success !");
		} catch (Exception ex) {
			logger.error(
					"makeFooter(String, FooterStyle) - " + ex.getMessage(), ex);
			return false;
		}
		return true;
	}

	protected boolean makeNewLine() {
		try {
			Chunk chunk = new Chunk(
					"                                                                                                                                            ");

			chunk.setUnderline(new Color(128, 128, 128), 0.01F, 0.01F, 15.0F,
					0.0F, 0);
			this.document.add(chunk);
		} catch (DocumentException e) {
			logger.error("makeNewLine()", e);
			return false;
		}
		return true;
	}

	public boolean makePicTalbe(TableBodyStyle tbs) {
		if ((this.tempImage == null) || (this.tempPara == null)) {
			logger.equals("mekePicTable fail");
			return false;
		}
		if (tbs.isUsePdfPTable()) {
			if (!makePicPdfPtable(tbs)) {
				return false;
			}
		} else if (!makePicTable(tbs)) {
			return false;
		}

		return true;
	}

	public boolean makePicPdfPtable(TableBodyStyle tbs) {
		PdfPTable table = makePicPTableFun(this.tempPara, this.tempImage, tbs);
		PdfPTable pTable = new PdfPTable(1);
		pTable.setWidthPercentage(100.0F);
		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(0);
		pTable.addCell(cell);
		try {
			this.document.add(pTable);
		} catch (DocumentException e) {
			logger.error("makePicPdfPtable(TableBodyStyle)", e);
		}

		return true;
	}

	public boolean makeLargeText(org.w3c.dom.Element element) {
		Paragraph largeText = null;
		NodeList list = element.getElementsByTagName("header");
		org.w3c.dom.Element e = null;
		TextStyle ts = null;
		String size = null;
		String content = null;
		String font = null;
		String style = null;
		String align = null;
		String space = null;
		String remark = null;
		String isStart = null;
		String isEnd = null;
		for (int i = 0; i < list.getLength(); i++) {
			ts = new TextStyle();
			e = (org.w3c.dom.Element) list.item(i);
			size = XMLParser.getTagContent(e, "size", "");
			font = XMLParser.getTagContent(e, "font", "");
			style = XMLParser.getTagContent(e, "style", "");
			content = XMLParser.getTagContent(e, "content", "");
			align = XMLParser.getTagContent(e, "align", "");
			isStart = XMLParser.getTagContent(e, "isStart", "");
			isEnd = XMLParser.getTagContent(e, "isEnd", "");
			space = XMLParser.getTagContent(e, "space", "");
			ts.setSize(Float.parseFloat(size));
			if ((font == null) || (font.equals("")))
				ts.setFontName(0);
			else if (font.equalsIgnoreCase("黑体"))
				ts.setFontName(3);
			else if (font.equalsIgnoreCase("楷体"))
				ts.setFontName(2);
			else if (font.equalsIgnoreCase("仿宋"))
				ts.setFontName(1);
			else if (font.equalsIgnoreCase("Arial"))
				ts.setFontName(4);
			else if (font.equalsIgnoreCase("Arial_BOLD"))
				ts.setFontName(5);
			else if (font.equalsIgnoreCase("Arial_ITALIC"))
				ts.setFontName(6);
			else if (font.equalsIgnoreCase("Arial_BOLDITALIC")) {
				ts.setFontName(7);
			}

			if (align == null)
				ts.setAlign(1);
			else if (align.equalsIgnoreCase("ALIGN_RIGHT"))
				ts.setAlign(2);
			else if (align.equalsIgnoreCase("ALIGN_LEFT"))
				ts.setAlign(0);
			else if (align.equalsIgnoreCase("ALIGN_CENTER")) {
				ts.setAlign(1);
			}

			if ((style == null) || (style.trim().equals("")))
				ts.setStyle(0);
			else if (style.equalsIgnoreCase("BOLD"))
				ts.setStyle(1);
			else if (style.equalsIgnoreCase("ITALIC"))
				ts.setStyle(2);
			else if (style.equalsIgnoreCase("BOLDITALIC"))
				ts.setStyle(3);
			else {
				ts.setStyle(0);
			}
			if ((isStart != null) && (!isStart.equals(""))) {
				ts.setStart(Boolean.valueOf(isStart).booleanValue());
			}
			if ((isEnd != null) && (!isEnd.equals(""))) {
				ts.setEnd(Boolean.valueOf(isEnd).booleanValue());
			}

			if (ts.isStart()) {
				if ((space != null) && (!space.equals("")))
					largeText = new Paragraph(Float.parseFloat(space));
				else {
					largeText = new Paragraph();
				}
			}

			Chunk chunk = makeLargeTextFun(content, ts);
			largeText.add(chunk);
			largeText.setAlignment(ts.getAlign());
			if (ts.isEnd()) {
				try {
					this.document.add(largeText);
				} catch (DocumentException e1) {
					logger.error("makeLargeText(org.w3c.dom.Element)", e1);
				}

			}

		}

		return true;
	}

	public boolean makePicTable(TableBodyStyle tbs) {
		return false;
	}

	private boolean addElement(com.lowagie.text.Element element) {
		try {
			this.document.add(element);
		} catch (Exception e) {
			logger.error("PDF在处理Element时发生异常，具体原因为：", e);
			return false;
		}
		return true;
	}

	public boolean MakePDF1(VData Vtype, VData Vcontent, VData Vstyle) {
		String type = "";
		String content = "";

		if (Vtype.size() < 5) {
			Vtype.add("TEXT");
			Vcontent.add("  ");
			Vstyle.add(new TextStyle());
		}
		for (int i = 0; i < Vtype.size(); i++) {
			type = (String) Vtype.get(i);

			if (type.equals("FILE")) {
				content = (String) Vcontent.get(i);
				FileStyle fs = (FileStyle) Vstyle.get(i);
				if (!makeFile(content, fs)) {
					return false;
				}
			}
			if (type.equals("TEXT")) {
				openDocument();
				Object value = Vcontent.get(i);
				if ((value instanceof String)) {
					TextStyle ts = new TextStyle();
					ts = (TextStyle) Vstyle.get(i);
					if (!makeText(value.toString(), ts))
						return false;
				} else if ((value instanceof org.w3c.dom.Element)) {
					org.w3c.dom.Element element = (org.w3c.dom.Element) value;
					if (!makeLargeText(element)) {
						return false;
					}
				}
			}
			if (type.equals("TITLE")) {
				openDocument();
				content = (String) Vcontent.get(i);
				TitleStyle TS = new TitleStyle();
				TS = (TitleStyle) Vstyle.get(i);
				if (!makeTitle(content, TS)) {
					return false;
				}
			}
			if (type.equals("IMAGE")) {
				openDocument();
				content = (String) Vcontent.get(i);
				ImageStyle is = new ImageStyle();
				is = (ImageStyle) Vstyle.get(i);
				String imageType = is.getImageType().toUpperCase();
				if (((imageType.equals("JPG")) || (imageType.equals("GIF")) || (imageType
						.equals("PNG"))) && (!makeImage(content, is))) {
					return false;
				}
			}

			if (type.equals("TABLEHEAD")) {
				openDocument();
				TableHeadStyle ths = new TableHeadStyle();
				ths = (TableHeadStyle) Vstyle.get(i);
				boolean usePDFPTable = false;
				if (ths.getheadType() < 0) {
					usePDFPTable = true;
				}

				if (Math.abs(ths.getheadType()) == 1) {
					List list = (List) Vcontent.get(i);
					if (!makeTableHead(list, ths, usePDFPTable)) {
						return false;
					}
				}

				if (Math.abs(ths.getheadType()) == 2) {
					String[] col = (String[]) Vcontent.get(i);
					if (!makeTableHead(col, ths, usePDFPTable)) {
						return false;
					}
				}
			}
			if (type.equals("TABLEBODY")) {
				openDocument();
				SSRS tSSRS = (SSRS) Vcontent.get(i);
				TableBodyStyle tbs = new TableBodyStyle();
				tbs = (TableBodyStyle) Vstyle.get(i);
				if (tbs.isUsePdfPTable()) {
					if (!makePDFPTableBody(tSSRS, tbs)) {
						return false;
					}
				} else if (!makeTableBody(tSSRS, tbs)) {
					return false;
				}
			}

			if (type.equals("TABLEBODY-H")) {
				openDocument();
				SSRS tSSRS = (SSRS) Vcontent.get(i);
				TableBodyStyle tbs = new TableBodyStyle();
				tbs = (TableBodyStyle) Vstyle.get(i);
				if (tbs.isUsePdfPTable()) {
					if (!makePDFPTableBodyH(tSSRS, tbs)) {
						return false;
					}
				} else if (!makeTableBody(tSSRS, tbs)) {
					return false;
				}
			}

			if (type.equals("PICTABLEBODY")) {
				openDocument();
				TableBodyStyle tbs = (TableBodyStyle) Vstyle.get(i);
				if (!makePicTalbe(tbs)) {
					return false;
				}
			}

			if (type.equals("ONTABLEBODY")) {
				openDocument();
				SSRS tSSRS = (SSRS) Vcontent.get(i);
				TableBodyStyle tbs = new TableBodyStyle();
				tbs = (TableBodyStyle) Vstyle.get(i);
				if (!makeOnTableBody(tSSRS, tbs)) {
					return false;
				}
			}

			if (type.equals("HEADER")) {
				content = (String) Vcontent.get(i);

				HeaderStyle hs = new HeaderStyle();
				hs = (HeaderStyle) Vstyle.get(i);
				if (!makeHeader(content, hs)) {
					return false;
				}
			}

			if (type.equals("FOOTER")) {
				content = (String) Vcontent.get(i);
				FooterStyle fs = new FooterStyle();
				fs = (FooterStyle) Vstyle.get(i);
				if (!makeFooter(content, fs)) {
					return false;
				}
			}

			if (type.equals("NEWPAGE")) {
				openDocument();
				content = (String) Vcontent.get(i);
				if (!makeNewPageFun(this.document, content)) {
					return false;
				}
			}

			if (type.equals("ROTATION")) {
				openDocument();
				if (!setPageRotation(this.document)) {
					return false;
				}

			}

			if (type.equals("ELEMENT")) {
				openDocument();
				com.lowagie.text.Element element = (com.lowagie.text.Element) Vcontent
						.get(i);
				if (!addElement(element)) {
					return false;
				}
			}
		}

		makeCloseFun(this.document);
		return true;
	}

	private void openDocument() {
		if (!this.document.isOpen()) {
			this.pdfWriter.setPageEvent(this);
			this.document.open();
		}
	}

	public pdfTool() {
	}

	public pdfTool(String path) {
		this.path = path;
	}

	public static void main(String[] args) {
	}

	public VData getHeaderInfo() {
		return this.headerInfo;
	}

	public void setHeaderInfo(VData headerInfo) {
		this.headerInfo = headerInfo;
	}

	public boolean isNeedRepeat() {
		return this.needRepeat;
	}
}


 * Location: F:\新建议书单机测试版\新建议书单机测试版\classes\ Qualified Name:
 * com.sinosoft.utility.pdfTool JD-Core Version: 0.6.2
 */