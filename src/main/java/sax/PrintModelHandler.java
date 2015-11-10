package sax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PrintModelHandler extends DefaultHandler{
	static SAXParser parser = null;
	static {
		// 构建SAXParser
		try {
			parser = SAXParserFactory.newInstance().newSAXParser();
			// 实例化 DefaultHandler对象
			PrintModelHandler parseXml = new PrintModelHandler();
			// 加载资源文件 转化为一个输入流
			InputStream stream = new FileInputStream("src/main/java/sax/NewFile.xml");
			// 调用parse()方法
			parser.parse(stream, parseXml);
		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {
		super.startElement(arg0, arg1, arg2, arg3);
	}
	
	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {
		super.endElement(arg0, arg1, arg2);
	}
	
	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		super.characters(arg0, arg1, arg2);
	}
	
}
