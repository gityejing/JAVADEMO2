/*package com.sinosoft.utility;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.sinosoft.style.pdf.FileStyle;
import com.sinosoft.style.pdf.FontStyle;
import com.sinosoft.style.pdf.FooterStyle;
import com.sinosoft.style.pdf.HeaderStyle;
import com.sinosoft.style.pdf.ImageStyle;
import com.sinosoft.style.pdf.TableBodyStyle;
import com.sinosoft.style.pdf.TableHeadStyle;
import com.sinosoft.style.pdf.TextStyle;
import com.sinosoft.style.pdf.TitleStyle;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;

public class PdfToolFun extends PdfPageEventHelper {
	private static final Logger logger = Logger.getLogger(pdfTool.class);
	public static final int TYPE_FILE = 0;
	public static final int TYPE_HEADER = 1;
	public static final int TYPE_FOOTER = 2;
	public static final int TYPE_IMAGE = 6;
	public static final int TYPE_TEXT = 5;
	public static final int TYPE_TITLE = 4;
	public static final int TYPE_NEWPAGE = 3;
	public static final int TYPE_TABLEHEAD = 7;
	public static final int TYPE_TABLEBODY = 8;
	private int recoverNum = 10000;
	private int recoverA4Num = 10000;
	private int printNums = 0;
	protected String path = "";

	private static Logger log = Logger.getLogger(PdfPageEventHelperTool.class
			.getName());
	public PdfTemplate tpl;
	public PdfTemplate currentpagetemp;
	public PdfTemplate numbertemp;
	public PdfTemplate tatolpagetemp;
	public BaseFont font;
	public String footContent = null;

	public float fontSize = 8.0F;

	public String printModel = null;

	protected HashMap hsmTitleUnitRow = new HashMap();

	public BaseFont getFont() {
		return this.font;
	}

	public void setFont(BaseFont font) {
		this.font = font;
	}

	public float getFontSize() {
		return this.fontSize;
	}

	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}

	public String getPrintModel() {
		return this.printModel;
	}

	public void setPrintModel(String printModel) {
		this.printModel = printModel;
	}

	public Vector makeFileFun(Document document, PdfWriter pdfWriter,
			String tFilePath, FileStyle fs) {
		Vector vetData = new Vector();
		try {
			document = new Document();
			document.setMargins(fs.getMarginLeft(), fs.getMarginRight(),
					fs.getMarginTop(), fs.getMarginBottom());
			document.setPageSize(fs.getPageSize());

			File file = new File(tFilePath);
			pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(
					file));

			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
					"UniGB-UCS2-H", false);
			Font pageNumFont = new Font(bfChinese, 9.0F, 0);

			if (!fs.isHeaderOrFooterInFirstPage()) {
				pdfWriter.setPageEvent(this);
				document.open();
			}

			vetData.add(document);
			vetData.add(pdfWriter);
		} catch (DocumentException de) {
			logger.error("makeFile(String, FileStyle) - " + de.getMessage(), de);
			return null;
		} catch (IOException ioe) {
			logger.error("makeFile(String, FileStyle) - " + ioe.getMessage(),
					ioe);
			return null;
		}

		return vetData;
	}

	public Vector makeFileHfFun(Document document, PdfWriter pdfWriter,
			String tFilePath, FileStyle fs) {
		Vector vctReData = new Vector();
		try {
			document = new Document();
			document.setMargins(fs.getMarginLeft(), fs.getMarginRight(),
					fs.getMarginTop(), fs.getMarginBottom());
			document.setPageSize(fs.getPageSize());

			pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(
					tFilePath));

			vctReData.add(document);
			vctReData.add(pdfWriter);
		} catch (DocumentException de) {
			logger.error(
					"makeFileHfFun(Document, PdfWriter, String, FileStyle) - "
							+ de.getMessage(), de);
			return null;
		} catch (IOException ioe) {
			logger.error(
					"makeFileHfFun(Document, PdfWriter, String, FileStyle) - "
							+ ioe.getMessage(), ioe);
			return null;
		}

		return vctReData;
	}

	protected boolean makeCloseFun(Document document) {
		document.close();
		return true;
	}

	protected Font getFontChineseFun(FontStyle fs) {
		try {
			BaseFont bfChinese = getBaseFontFun(fs);

			return new Font(bfChinese, fs.getSize(), fs.getStyle(),
					fs.getColor());
		} catch (Exception ex) {
			logger.error("getFontChinese(FontStyle) - " + ex.getMessage(), ex);
		}
		return null;
	}

	protected BaseFont getBaseFontFun(FontStyle fs) {
		try {
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
					"UniGB-UCS2-H", false);
			switch (fs.getFontName()) {
			case 1:
				bfChinese = BaseFont.createFont(this.path
						+ "conf/Font/simfang.ttf", "Identity-H", false);
				break;
			case 2:
				bfChinese = BaseFont.createFont(this.path
						+ "conf/Font/simkai.ttf", "Identity-H", false);
				break;
			case 3:
				bfChinese = BaseFont.createFont(this.path
						+ "conf/Font/simhei.ttf", "Identity-H", false);
				break;
			case 4:
				bfChinese = BaseFont.createFont(this.path
						+ "conf/Font/arial.ttf", "Cp1252", false);
				break;
			case 5:
				bfChinese = BaseFont.createFont(this.path
						+ "conf/Font/arialbd.ttf", "Cp1252", false);
				break;
			case 6:
				bfChinese = BaseFont.createFont(this.path
						+ "conf/Font/ariali.ttf", "Cp1252", false);
				break;
			case 7:
				bfChinese = BaseFont.createFont(this.path
						+ "conf/Font/arialbi.ttf", "Cp1252", false);
				break;
			case 8:
				bfChinese = BaseFont.createFont(this.path
						+ "conf/Font/tahoma.ttf", "Cp1252", false);
				break;
			}

			return bfChinese;
		} catch (Exception ex) {
			logger.error("getBaseFont(FontStyle) - " + ex.getMessage(), ex);
		}
		return null;
	}

	public Paragraph makeTextFun(String tContent, TextStyle ts) {
		tContent = StrTool.replaceEx(tContent, "<br>", "\n");
		Paragraph paragraph = new Paragraph(ts.getLeading(), tContent,
				getFontChineseFun(ts));
		paragraph.setAlignment(ts.getAlign());

		return paragraph;
	}

	public boolean makeTitleFun(PdfWriter pdfWriter, String title, TitleStyle ts) {
		String[] titles = title.split("<br>");

		PdfContentByte cb = pdfWriter.getDirectContent();
		try {
			cb.beginText();
			cb.setFontAndSize(getBaseFontFun(ts), ts.getSize());
			float step = 5.0F;
			for (int i = 0; i < titles.length; i++) {
				cb.showTextAligned(ts.getAlign(), titles[i],
						ts.getPx() * 72.0F / 25.4F,
						(ts.getPy() - step * i) * 72.0F / 25.4F, 0.0F);
			}
			cb.endText();
		} catch (Exception ex) {
			logger.error("makeTitle(String, TitleStyle) - " + ex.getMessage(),
					ex);
			return false;
		}
		return true;
	}

	public Image makeImageFun(String path, ImageStyle is) {
		Image image = null;
		File file = new File(path);
		if (file.exists()) {
			try {
				image = Image.getInstance(path);
			} catch (BadElementException e) {
				logger.error("makeImageFun(String, ImageStyle)", e);
			} catch (MalformedURLException e) {
				logger.error("makeImageFun(String, ImageStyle)", e);
			} catch (IOException e) {
				logger.error("makeImageFun(String, ImageStyle)", e);
			}
			image.setAlignment(is.getAlign());
			image.scalePercent(is.getScalePercent());

			if (is.getAbsolute()) {
				image.setAbsolutePosition(is.getPx(), is.getPy());
			}
		}
		return image;
	}

	public Table makeTableHeadFun(Table table, String[] colNames,
			TableHeadStyle ths) {
		try {
			int col = ths.getCol();
			table = new Table(col);
			table.setBorderWidth(ths.getBorder());
			table.setWidth(ths.getWidth());

			if (ths.getWidths() != null) {
				table.setWidths(ths.getWidths());
			}
			table.setBorderColor(ths.getColor());
			table.setPadding(ths.getPadding());
			table.setSpacing(ths.getSpacing());
			Font font = getFontChineseFun(ths);
			for (int i = 0; i < col; i++) {
				Phrase phrase = new Phrase(colNames[i], font);
				Cell cell = new Cell(phrase);
				cell.setHorizontalAlignment(ths.getAlign());
				cell.setBorder(ths.getBorder());
				cell.setBackgroundColor(new Color(ths.getR(), ths.getG(), ths
						.getB()));
				table.addCell(cell);
			}
			table.endHeaders();
		} catch (Exception ex) {
			logger.error(
					"makeTableHead(String[], TableHeadStyle) - "
							+ ex.getMessage(), ex);
			logger.error("makeTableHeadFun(Table, String[], TableHeadStyle)",
					ex);
		}
		return table;
	}

	public PdfPTable makePdfPTableHeadFun(String[] colNames, TableHeadStyle ths) {
		PdfPTable pdfpTable = null;
		try {
			int col = ths.getCol();
			pdfpTable = new PdfPTable(col);
			if (ths.getWidths() != null) {
				pdfpTable.setWidths(ths.getWidths());
			}
			pdfpTable.setWidthPercentage(ths.getWidth());
			pdfpTable.setHorizontalAlignment(ths.getTableAlign());
			pdfpTable.setSpacingBefore(ths.getPadding());
			pdfpTable.setSpacingAfter(ths.getPadding());
			Font font = getFontChineseFun(ths);

			for (int i = 0; i < col; i++) {
				Phrase phrase = new Phrase(colNames[i], font);
				PdfPCell pdfpCell = new PdfPCell(phrase);
				pdfpCell.setHorizontalAlignment(ths.getAlign());
				pdfpCell.setVerticalAlignment(1);
				pdfpCell.setBorder(ths.getBorder());
				pdfpCell.setBackgroundColor(new Color(ths.getR(), ths.getG(),
						ths.getB()));
				pdfpTable.addCell(pdfpCell);
			}
		} catch (Exception ex) {
			logger.error("makePdfPTableHeadFun(String[], TableHeadStyle)", ex);
		}
		return pdfpTable;
	}

	public Table makeTableHeadFun(List headers, TableHeadStyle ths) {
		try {
			int col = ths.getCol();
			Table table = new Table(col);
			table.setBorderWidth(ths.getBorder());
			table.setBorder(0);
			table.setWidth(ths.getWidth());
			table.setWidths(ths.getWidths());
			table.setPadding(ths.getPadding());
			table.setSpacing(ths.getSpacing());
			table.setAlignment(ths.getTableAlign());
			Font font = getFontChineseFun(ths);
			Cell cell = null;
			for (int i = 0; i < headers.size(); i++) {
				cell = getHeaderToCell((Header) headers.get(i), cell, font, ths);
				table.addCell(cell);
			}
			table.endHeaders();
		} catch (Exception ex) {
			logger.error(
					"makeTableHead(List, TableHeadStyle) - " + ex.getMessage(),
					ex);
			logger.error("makeTableHeadFun(List, TableHeadStyle)", ex);
			return null;
		}
		Table table;
		return table;
	}

	public PdfPTable makePdfPTableHeadFun(List headers, TableHeadStyle ths) {
		PdfPTable table = new PdfPTable(ths.getCol());
		try {
			table.setWidthPercentage(ths.getWidth());
			table.setWidths(ths.getWidths());
			table.setSpacingBefore(ths.getPadding());
			table.setSpacingAfter(ths.getPadding());
			Font font = getFontChineseFun(ths);
			PdfPCell pdfpcell = null;
			XmlTable xmlTable = null;
			PdfPTable tableElement = null;
			for (int i = 0; i < headers.size(); i++) {
				if (headers.get(i).getClass().getSimpleName().equals("Header")) {
					pdfpcell = getHeaderToPdfPCell((Header) headers.get(i),
							font, ths);
					table.addCell(pdfpcell);
				} else {
					xmlTable = (XmlTable) headers.get(i);
					tableElement = makePdfPTableFun(xmlTable.getHeaders(),
							xmlTable.getTableHeadStyle(), ths);
					pdfpcell = getHeaderToTable(tableElement, font, ths);
					table.addCell(pdfpcell);
				}
			}
			table.setHeadersInEvent(true);
			table.setHeaderRows(table.size());
		} catch (Exception ex) {
			logger.error(
					"makeTableHead(List, TableHeadStyle) - " + ex.getMessage(),
					ex);
			logger.error("makePdfPTableHeadFun(List, TableHeadStyle)", ex);
			return null;
		}
		return table;
	}

	public PdfPTable makePdfPTableFun(List headers, TableHeadStyle ths,
			TableHeadStyle thstwo) {
		PdfPTable pdfPTable = null;
		int col = ths.getCol();
		pdfPTable = new PdfPTable(col);
		pdfPTable.setWidthPercentage(ths.getWidth());
		try {
			pdfPTable.setWidths(ths.getWidths());
		} catch (DocumentException e) {
			logger.error(
					"makePdfPTableFun(List, TableHeadStyle, TableHeadStyle)", e);
		}
		try {
			Font font = getFontChineseFun(thstwo);

			for (int i = 0; i < headers.size(); i++) {
				PdfPCell pdfPCell = getHeaderToPdfPCell(
						(Header) headers.get(i), font, ths);
				pdfPTable.addCell(pdfPCell);
			}
		} catch (Exception ex) {
			logger.error(
					"makeTableHead(List, TableHeadStyle) - " + ex.getMessage(),
					ex);
			logger.error(
					"makePdfPTableFun(List, TableHeadStyle, TableHeadStyle)",
					ex);
			return null;
		}
		return pdfPTable;
	}

	private Cell getHeaderToCell(Header header, Cell cell, Font font,
			TableHeadStyle ths) {
		if (cell == null)
			cell = new Cell();
		try {
			cell = new Cell(new Phrase(header.getName(), font));
		} catch (BadElementException e) {
			throw new RuntimeException("headrtocell转换错误", e);
		}
		cell.setHorizontalAlignment(ths.getAlign());
		cell.setVerticalAlignment(0);
		cell.setBackgroundColor(new Color(ths.getR(), ths.getG(), ths.getB()));
		if (header.getRow() > 1) {
			cell.setRowspan(header.getRow());
		}
		if (header.getCol() > 1) {
			cell.setColspan(header.getCol());
		}
		return cell;
	}

	private PdfPCell getHeaderToPdfPCell(Header header, Font font,
			TableHeadStyle ths) {
		PdfPCell cell = new PdfPCell(new Phrase(header.getName(), font));
		cell.setHorizontalAlignment(ths.getAlign());
		cell.setVerticalAlignment(5);

		cell.setBackgroundColor(new Color(ths.getR(), ths.getG(), ths.getB()));
		if (header.getCol() > 1) {
			cell.setColspan(header.getCol());
		}
		return cell;
	}

	private Cell getHeaderToTable(Table table, Font font, TableHeadStyle ths) {
		Cell cell = null;
		try {
			cell = new Cell(table);
		} catch (BadElementException e) {
			throw new RuntimeException("headrtocell转换错误", e);
		}
		cell.setHorizontalAlignment(ths.getAlign());
		cell.setVerticalAlignment(0);
		cell.setBackgroundColor(new Color(ths.getR(), ths.getG(), ths.getB()));
		if (table.size() > 1) {
			cell.setRowspan(table.size());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getHeaderToTable(Table, Font, TableHeadStyle) - "
					+ table.size() + "table.size()");
		}
		if (table.getColumns() > 1) {
			cell.setColspan(table.getColumns());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getHeaderToTable(Table, Font, TableHeadStyle) - "
					+ table.getColumns() + "table.columns()");
		}
		return cell;
	}

	private PdfPCell getHeaderToTable(PdfPTable table, Font font,
			TableHeadStyle ths) {
		PdfPCell cell = null;
		cell = new PdfPCell(table);
		cell.setPaddingBottom(0.0F);
		cell.setPaddingTop(0.0F);
		cell.setPadding(0.0F);
		cell.setHorizontalAlignment(ths.getAlign());
		cell.setBackgroundColor(new Color(ths.getR(), ths.getG(), ths.getB()));
		if (table.getAbsoluteWidths().length > 1) {
			cell.setColspan(table.getAbsoluteWidths().length);
		}
		return cell;
	}

	public PdfPTable makeTableBodyFun(PdfPTable table, SSRS tSSRS,
			TableBodyStyle tbs) {
		try {
			Font font = getFontChineseFun(tbs);

			int Align = tbs.getAlign();

			for (int j = 1; j <= tSSRS.getMaxRow(); j++) {
				for (int i = 1; i <= tSSRS.getMaxCol(); i++) {
					Phrase phrase = new Phrase(tSSRS.GetText(j, i), font);

					PdfPCell cell = new PdfPCell(phrase);
					if ((Align == 3) && (i == tSSRS.getMaxCol())) {
						cell.setHorizontalAlignment(2);
					} else if ((Align == 3) && (i < tSSRS.getMaxCol())) {
						cell.setHorizontalAlignment(1);
					} else if ((Align == 20) && (i < 3)) {
						cell.setHorizontalAlignment(1);
					} else if ((Align == 20) && (i >= 3)) {
						cell.setHorizontalAlignment(2);
					} else {
						cell.setHorizontalAlignment(tbs.getAlign());
					}

					cell.setVerticalAlignment(0);
					cell.setBorder(tbs.getBorder());
					if (tbs.getBodyType() != 2) {
						if (j % 2 == 0)
							cell.setBackgroundColor(new Color(tbs.getR(), tbs
									.getG(), tbs.getB()));
					} else {
						cell.setBackgroundColor(new Color(tbs.getR(), tbs
								.getG(), tbs.getB()));
					}
					table.addCell(cell);
				}
			}
			if ((tbs.getBodyType() == 0) || (tbs.getBodyType() == 2))
				return table;
		} catch (Exception ex) {
			logger.error(
					"makeTableBody(SSRS, TableBodyStyle) - " + ex.getMessage(),
					ex);
			return null;
		}
		return null;
	}

	public PdfPTable makeTableBodyFunH(PdfPTable table, SSRS tSSRS,
			TableBodyStyle tbs) {
		try {
			Font font = getFontChineseFun(tbs);

			for (int j = 1; j <= tSSRS.getMaxRow(); j++) {
				if (j == tSSRS.getMaxRow() - 1) {
					int k = tSSRS.getMaxCol();
					Phrase phrase = new Phrase(tSSRS.GetText(j, k - 1), font);

					PdfPCell cell = new PdfPCell(phrase);
					cell.setColspan(k - 1);
					cell.setHorizontalAlignment(2);
					cell.setVerticalAlignment(0);
					cell.setBorder(tbs.getBorder());
					if (tbs.getBodyType() != 2) {
						if (j % 2 == 0)
							cell.setBackgroundColor(new Color(tbs.getR(), tbs
									.getG(), tbs.getB()));
					} else {
						cell.setBackgroundColor(new Color(tbs.getR(), tbs
								.getG(), tbs.getB()));
					}
					table.addCell(cell);
					phrase = new Phrase(tSSRS.GetText(j, k), font);

					PdfPCell cellL = new PdfPCell(phrase);
					cellL.setHorizontalAlignment(2);
					cellL.setVerticalAlignment(0);
					cellL.setBorder(tbs.getBorder());
					if (tbs.getBodyType() != 2) {
						if (j % 2 == 0)
							cellL.setBackgroundColor(new Color(tbs.getR(), tbs
									.getG(), tbs.getB()));
					} else {
						cellL.setBackgroundColor(new Color(tbs.getR(), tbs
								.getG(), tbs.getB()));
					}
					table.addCell(cellL);
				} else if (j == tSSRS.getMaxRow()) {
					int k = tSSRS.getMaxCol();
					Phrase phrase = new Phrase(tSSRS.GetText(j, 1), font);

					PdfPCell cell = new PdfPCell(phrase);

					cell.setHorizontalAlignment(tbs.getAlign());
					cell.setVerticalAlignment(0);
					cell.setBorder(tbs.getBorder());
					if (tbs.getBodyType() != 2) {
						if (j % 2 == 0)
							cell.setBackgroundColor(new Color(tbs.getR(), tbs
									.getG(), tbs.getB()));
					} else {
						cell.setBackgroundColor(new Color(tbs.getR(), tbs
								.getG(), tbs.getB()));
					}
					table.addCell(cell);

					phrase = new Phrase(tSSRS.GetText(j, k), font);

					PdfPCell cellL = new PdfPCell(phrase);
					cellL.setColspan(k - 1);
					cellL.setHorizontalAlignment(2);
					cellL.setVerticalAlignment(0);
					cellL.setBorder(tbs.getBorder());
					if (tbs.getBodyType() != 2) {
						if (j % 2 == 0)
							cellL.setBackgroundColor(new Color(tbs.getR(), tbs
									.getG(), tbs.getB()));
					} else {
						cellL.setBackgroundColor(new Color(tbs.getR(), tbs
								.getG(), tbs.getB()));
					}
					table.addCell(cellL);
				} else {
					for (int i = 1; i <= tSSRS.getMaxCol(); i++) {
						Phrase phrase = new Phrase(tSSRS.GetText(j, i), font);

						PdfPCell cell = new PdfPCell(phrase);
						if ((i == tSSRS.getMaxCol())
								|| (i == tSSRS.getMaxCol() - 1))
							cell.setHorizontalAlignment(2);
						else {
							cell.setHorizontalAlignment(tbs.getAlign());
						}
						cell.setVerticalAlignment(0);
						cell.setBorder(tbs.getBorder());
						if (tbs.getBodyType() != 2) {
							if (j % 2 == 0)
								cell.setBackgroundColor(new Color(tbs.getR(),
										tbs.getG(), tbs.getB()));
						} else {
							cell.setBackgroundColor(new Color(tbs.getR(), tbs
									.getG(), tbs.getB()));
						}
						table.addCell(cell);
					}
				}
			}
			if ((tbs.getBodyType() == 0) || (tbs.getBodyType() == 2))
				return table;
		} catch (Exception ex) {
			logger.error(
					"makeTableBody(SSRS, TableBodyStyle) - " + ex.getMessage(),
					ex);
			return null;
		}
		return null;
	}

	public Table makeTableBodyFun(Table table, SSRS tSSRS, TableBodyStyle tbs) {
		try {
			Font font = getFontChineseFun(tbs);

			for (int j = 1; j <= tSSRS.getMaxRow(); j++) {
				for (int i = 1; i <= tSSRS.getMaxCol(); i++) {
					Phrase phrase = new Phrase(tSSRS.GetText(j, i), font);

					Cell cell = new Cell(phrase);
					cell.setHorizontalAlignment(tbs.getAlign());
					cell.setVerticalAlignment(0);
					if (tbs.getBodyType() != 2) {
						if (j % 2 == 0)
							cell.setBackgroundColor(new Color(tbs.getR(), tbs
									.getG(), tbs.getB()));
					} else {
						cell.setBackgroundColor(new Color(tbs.getR(), tbs
								.getG(), tbs.getB()));
					}
					table.addCell(cell);
				}

			}

			if ((tbs.getBodyType() == 0) || (tbs.getBodyType() == 2))
				return table;
		} catch (Exception ex) {
			logger.error(
					"makeTableBody(SSRS, TableBodyStyle) - " + ex.getMessage(),
					ex);
			return null;
		}
		return null;
	}

	protected PdfPTable makeOnTableBodyFun(SSRS tSSRS, TableBodyStyle tbs) {
		String sqbf = "";
		try {
			PdfPTable table = new PdfPTable(tSSRS.getMaxCol());
			Font font = getFontChineseFun(tbs);

			table.setWidthPercentage(100.0F);
			table.setWidths(tbs.getWidths());
			table.setSpacingBefore(tbs.getPad());
			table.setSpacingAfter(tbs.getPad());
			int maxCellRow = 0;
			for (int j = 1; j <= tSSRS.getMaxRow(); j++) {
				for (int i = 1; i <= tSSRS.getMaxCol(); i++) {
					String value = tSSRS.GetText(j, i);

					int maxRowNum = value.length() / 32;
					if (maxRowNum > maxCellRow) {
						maxCellRow = maxRowNum;
					}
					String type = value.substring(value.lastIndexOf(".") + 1)
							.toUpperCase();
					value = StrTool.replaceEx(value, "<br>", "\n");
					Phrase phrase = new Phrase(value, font);

					PdfPCell cell = new PdfPCell(phrase);
					cell.setPadding(10.0F);
					cell.setLeading(1.0F, 1.5F);
					if (i == 1) {
						cell.setHorizontalAlignment(1);
					} else {
						cell.setHorizontalAlignment(0);
					}
					if (tbs.getBodyType() != 2) {
						if (i % 2 == 1)
							cell.setBackgroundColor(new Color(tbs.getR(), tbs
									.getG(), tbs.getB()));
					} else {
						cell.setBackgroundColor(new Color(tbs.getR(), tbs
								.getG(), tbs.getB()));
					}
					table.addCell(cell);
				}
			}

			if ((tbs.getBodyType() == 0) || (tbs.getBodyType() == 2))
				return table;
		} catch (Exception ex) {
			logger.error(
					"makeOnTableBody(SSRS, TableBodyStyle) - "
							+ ex.getMessage(), ex);
			return null;
		}
		PdfPTable table;
		return table;
	}

	public HeaderFooter makeHeaderFun(String content, HeaderStyle hs) {
		Paragraph p = new Paragraph();
		try {
			if (hs.getContentType() == 1) {
				p = new Paragraph(content, getFontChineseFun(hs));
			}
			if (hs.getContentType() == 2) {
				Image image = Image.getInstance(content);
				image.scalePercent(60.0F);
				image.setAlignment(hs.getAlign());
				p.add(image);
			}
			if (hs.getContentType() == 0) {
				String[] contentpic = content.split("&&");
				Image image = Image.getInstance(contentpic[0]);
				image.scalePercent(60.0F);

				image.setAlignment(hs.getAlign());
				p.add(image);
				Paragraph c = new Paragraph(contentpic[1],
						getFontChineseFun(hs));
				p.add(c);
			}
			HeaderFooter header = new HeaderFooter(p, false);

			header.setAlignment(2);
			header.setBorder(0);
			header.setBorderWidth(0.0F);
		} catch (Exception ex) {
			logger.error(
					"makeHeader(String, HeaderStyle) - " + ex.getMessage(), ex);
			return null;
		}
		HeaderFooter header;
		return header;
	}

	public boolean makeFooterFun(String content, FooterStyle fs) {
		HeaderFooter footer = null;
		try {
			Font font = getFontChineseFun(fs);
			setFont(getBaseFontFun(fs));
			setFontSize(font.getSize());
			this.footContent = content;
		} catch (Exception ex) {
			logger.error("makeFooterFun(String, FooterStyle)", ex);
			return false;
		}
		return true;
	}

	public boolean makeNewPageFun(Document document, String content) {
		try {
			if (content.equals("rotation")) {
				document.setPageSize(document.getPageSize().rotate());
				document.newPage();
			} else {
				document.newPage();
			}
		} catch (Exception ex) {
			logger.error("makeNewPage() - " + ex.getMessage(), ex);
			return false;
		}
		return true;
	}

	protected Phrase getPhraseFun(String value, FooterStyle fs) {
		float size = fs.getSize();
		int style = fs.getStyle();
		if ((value.indexOf("<size:") != -1) && (value.indexOf(">") != -1)) {
			size = Float.parseFloat(value.substring(
					value.indexOf("<size:") + 6, value.indexOf(">")));
			value = value.substring(value.indexOf(">") + 1);
		}
		FontStyle fontStyle = new FontStyle();
		fontStyle.setSize(size);
		fontStyle.setStyle(style);
		fontStyle.setColor(fs.getColor());
		Font font = getFontChineseFun(fontStyle);
		return new Phrase(value, font);
	}

	public boolean makePdfPTableHead(List headers, TableHeadStyle ths) {
		PdfPTable pdfpTable = null;
		try {
			int col = ths.getCol();
			pdfpTable = new PdfPTable(col);
			pdfpTable.setTotalWidth(ths.getWidth());
			pdfpTable.setWidths(ths.getWidths());
			pdfpTable.setHorizontalAlignment(ths.getTableAlign());
			pdfpTable.setSpacingBefore(ths.getPadding());
			pdfpTable.setSpacingAfter(ths.getPadding());

			Font font = getFontChineseFun(ths);
			String colName = "";
			int colSpan = 1;
			PdfPCell pdfpCell = null;
			Phrase phrase = null;
			Header header = null;
			for (int i = 0; i < headers.size(); i++) {
				header = (Header) headers.get(i);
				colName = header.getName();
				colSpan = header.getCol();
				phrase = new Phrase(colName, font);
				pdfpCell = new PdfPCell(phrase);
				pdfpCell.setHorizontalAlignment(ths.getAlign());
				pdfpCell.setVerticalAlignment(1);
				pdfpCell.setBorderWidth(ths.getBorder());
				pdfpCell.setBackgroundColor(new Color(ths.getR(), ths.getG(),
						ths.getB()));
				if (colSpan > 1) {
					pdfpCell.setColspan(colSpan);
				}
				pdfpTable.addCell(pdfpCell);
			}
		} catch (Exception ex) {
			logger.error("在处理Table表头时发生异常，具体原因为：", ex);
			return false;
		}
		return true;
	}

	public boolean makeTableHead(String[] colNames, TableHeadStyle ths) {
		int col = ths.getCol();
		Table table = null;
		try {
			table = new Table(col);
			table.setBorderWidth(ths.getBorder());
			table.setWidth(ths.getWidth());

			if (ths.getWidths() != null) {
				table.setWidths(ths.getWidths());
			}
			table.setBorderColor(ths.getColor());
			table.setPadding(ths.getPadding());
			table.setSpacing(ths.getSpacing());
			Font font = getFontChineseFun(ths);
			for (int i = 0; i < col; i++) {
				Phrase phrase = new Phrase(colNames[i], font);
				Cell cell = new Cell(phrase);
				cell.setHorizontalAlignment(ths.getAlign());
				cell.setBorder(ths.getBorder());
				cell.setBackgroundColor(new Color(ths.getR(), ths.getG(), ths
						.getB()));
				table.addCell(cell);
			}
		} catch (Exception ex) {
			logger.error("makeTableHead(String[], TableHeadStyle)", ex);
		}
		return true;
	}

	public Table makeTableHead(List headers, TableHeadStyle ths) {
		int col = ths.getCol();
		Table table = null;
		try {
			table = new Table(col);
			table.setBorderWidth(ths.getBorder());
			table.setWidth(ths.getWidth());
			table.setWidths(ths.getWidths());
			table.setPadding(ths.getPadding());
			table.setSpacing(ths.getSpacing());
			table.setAlignment(ths.getTableAlign());

			Font font = getFontChineseFun(ths);
			String colName = "";
			int rowSpan = 1;
			int colSpan = 1;
			Cell cell = null;
			Phrase phrase = null;
			Header header = null;
			for (int i = 0; i < headers.size(); i++) {
				header = (Header) headers.get(i);
				colName = header.getName();
				rowSpan = header.getRow();
				colSpan = header.getCol();
				phrase = new Phrase(colName, font);
				cell = new Cell(phrase);
				cell.setHorizontalAlignment(ths.getAlign());
				cell.setBorder(ths.getBorder());
				cell.setBackgroundColor(new Color(ths.getR(), ths.getG(), ths
						.getB()));
				if (rowSpan > 1) {
					cell.setRowspan(rowSpan);
				}
				if (colSpan > 1) {
					cell.setColspan(colSpan);
				}
				table.addCell(cell);
			}
		} catch (Exception ex) {
			logger.error("makeTableHead(List, TableHeadStyle)", ex);
			logger.error("在处理Table表头时发生异常，具体原因为：", ex);
		}
		return table;
	}

	public Chunk makeLargeTextFun(String content, TextStyle ts) {
		Font font = getFontChineseFun(ts);
		Chunk chunk = new Chunk(content, font);
		return chunk;
	}

	public PdfPTable makePicPTableFun(Paragraph p, Image image,
			TableBodyStyle tbs) {
		PdfPTable table = new PdfPTable(1);
		try {
			if (!tbs.isHasHead()) {
				table.setWidthPercentage(100.0F);

				table.setWidths(tbs.getWidths());
				table.setHorizontalAlignment(tbs.getAlign());
				table.setSpacingBefore(tbs.getPad());
				table.setSpacingAfter(tbs.getPad());
			}

			PdfPCell cell = new PdfPCell(p);
			cell.setBorder(2);
			cell.setVerticalAlignment(5);
			cell.setHorizontalAlignment(0);
			cell.setMinimumHeight(20.0F);
			table.addCell(cell);

			PdfPCell cell1 = new PdfPCell(image);
			cell1.setBorder(0);
			cell1.setVerticalAlignment(5);
			cell.setHorizontalAlignment(0);
			table.addCell(cell1);
		} catch (DocumentException e) {
			logger.error("makePicPTableFun(Paragraph, Image, TableBodyStyle)",
					e);
		}
		return table;
	}

	public PdfToolFun() {
	}

	public PdfToolFun(String path) {
		this.path = path;
	}

	public void SetneedRepeat(int recoverNum, int recoverA4Num) {
		this.recoverNum = recoverNum;
		this.recoverA4Num = recoverA4Num;
	}

	private Font getFontChinese(FontStyle fs) {
		try {
			BaseFont bfChinese = getBaseFontFun(fs);

			return new Font(bfChinese, fs.getSize(), fs.getStyle(),
					fs.getColor());
		} catch (Exception ex) {
			logger.error("getFontChinese(FontStyle fs)", ex);
		}
		return null;
	}

	public void onCloseDocument(PdfWriter writer, Document document) {
		this.tpl.beginText();
		this.tpl.setFontAndSize(this.font, this.fontSize);
		this.tpl.setTextMatrix(0.0F, 0.0F);
		this.tpl.showText(Integer.toString(writer.getPageNumber() - 1) + "页, ");
		this.tpl.endText();
	}

	public void onStartPage(PdfWriter writer, Document document) {
	}

	public void onEndPage(PdfWriter writer, Document document) {
		if (writer.getPageNumber() == 0) {
			return;
		}

		PdfContentByte cb = writer.getDirectContent();

		String totalpagetext = null;
		String currentpagetext = null;

		if (this.footContent == null) {
			this.footContent = " & ";
		}
		String[] footer = this.footContent.split("&");
		currentpagetext = "　当前为第" + writer.getPageNumber() + "页";
		totalpagetext = "本建议书共";
		try {
			if (this.font == null)
				this.font = BaseFont.createFont("STSongStd-Light",
						"UniGB-UCS2-H", false);
		} catch (Exception e) {
			log.error("页脚创建字体失败", e);
			throw new RuntimeException("页脚创建字体失败", e);
		}
		float adjust = this.font.getWidthPoint("页，", this.fontSize);
		float adjust1 = this.font.getWidthPoint(currentpagetext, this.fontSize);
		float adjust2 = this.font.getWidthPoint(totalpagetext, this.fontSize);
		float textSize = this.font.getWidthPoint(totalpagetext, this.fontSize);
		float textBase = document.bottom() - 16.0F;

		this.numbertemp = writer.getDirectContent().createTemplate(200.0F,
				100.0F);
		this.numbertemp
				.setBoundingBox(new Rectangle(0.0F, 0.0F, 200.0F, 100.0F));
		this.numbertemp.beginText();
		this.numbertemp.setFontAndSize(this.font, this.fontSize);
		this.numbertemp.setTextMatrix(0.0F, 0.0F);
		this.numbertemp.showText(footer[1]);
		this.numbertemp.endText();

		this.tatolpagetemp = writer.getDirectContent().createTemplate(150.0F,
				100.0F);
		this.tatolpagetemp.setBoundingBox(new Rectangle(0.0F, 0.0F, 150.0F,
				100.0F));
		this.tatolpagetemp.beginText();
		this.tatolpagetemp.setFontAndSize(this.font, this.fontSize);
		this.tatolpagetemp.setTextMatrix(0.0F, 0.0F);
		this.tatolpagetemp.showText(totalpagetext);
		this.tatolpagetemp.endText();

		this.currentpagetemp = writer.getDirectContent().createTemplate(100.0F,
				100.0F);
		this.currentpagetemp.setBoundingBox(new Rectangle(0.0F, 0.0F, 130.0F,
				100.0F));
		this.currentpagetemp.beginText();
		this.currentpagetemp.setFontAndSize(this.font, this.fontSize);
		this.currentpagetemp.setTextMatrix(0.0F, 0.0F);
		this.currentpagetemp.showText(currentpagetext);
		this.currentpagetemp.endText();

		cb.beginText();
		cb.setFontAndSize(this.font, this.fontSize);
		cb.setTextMatrix(document.left(), textBase);
		cb.showText(footer[0]);
		cb.endText();
		cb.addTemplate(this.numbertemp, document.left(),
				document.bottom() - 28.0F);
		cb.addTemplate(this.tatolpagetemp, document.right() - adjust2 - adjust1
				- adjust - 20.0F, document.bottom() - 28.0F);
		cb.addTemplate(this.tpl, document.right() - adjust1 - adjust - 20.0F,
				document.bottom() - 28.0F);
		cb.addTemplate(this.currentpagetemp,
				document.right() - adjust1 - 20.0F, document.bottom() - 28.0F);

		cb.stroke();
	}

	public void onOpenDocument(PdfWriter writer, Document document) {
		try {
			this.tpl = writer.getDirectContent().createTemplate(100.0F, 100.0F);
			this.tpl.setBoundingBox(new Rectangle(0.0F, 0.0F, 100.0F, 100.0F));
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}

	public int getPrintNums() {
		return this.printNums;
	}

	public void setPrintNums(int printNums) {
		this.printNums = printNums;
	}

	public boolean setPageRotation(Document document) {
		if (document == null) {
			logger.error("PDF打印失败！setPageRotation():document为空时不能进行设置翻转。");
			return false;
		}
		try {
			document.setPageSize(document.getPageSize().rotate());
		} catch (Exception ex) {
			logger.error("setPageRotation() - " + ex.getMessage(), ex);
			return false;
		}
		return true;
	}
}


 * Location: F:\新建议书单机测试版\新建议书单机测试版\classes\ Qualified Name:
 * com.sinosoft.utility.PdfToolFun JD-Core Version: 0.6.2
 */