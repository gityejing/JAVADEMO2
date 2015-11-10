package sax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class ModifyConnectionUtil {

	// 查询所有的数据
	public static void list() throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		InputStream file = new FileInputStream("src/main/java/sax/补充资料签收表.xml");
		// 获得文档对象
		Document document = builder.build(file);
		// 获得根节点
		Element root = document.getRootElement();
		System.out.println("root : " + root);
		System.out.println(root.getName());
		
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			if(e.getName().equals("NodeSource")){
				Element e2 = e.getChild("Children");
				if(e2 != null){
					List<Element> list2 = e2.getChildren();
					if(list2 != null && list2.size()>0){
						for (Element element : list2) {
							Element e3 = element.getChild("Reader");
							if(e3 != null){
								Element e4 = e3.getChild("Connection");
								if(e4 != null){
									Element e5 = e4.getChild("Driver");
									Element e6 = e4.getChild("User");
									Element e7 = e4.getChild("Password");
									Element e8 = e4.getChild("Url");
									
									if(e5 != null){
										System.out.println(e5.getTextTrim());
									}
									if(e6 != null){
										System.out.println(e6.getTextTrim());
									}
									if(e7 != null){
										System.out.println(e7.getTextTrim());
									}
									if(e8 != null){
										System.out.println(e8.getTextTrim());
									}
								}
							}
						}
					}
				}
			}
//			System.out.println("ID: " + e.getAttributeValue("id"));
//			System.out.println(e.getChildText("username"));
//			System.out.println(e.getChildText("password"));
			// System.out.println(list.iterator().getClass().toString());
		}
	}

	// 添加数据
	public static void add() throws JDOMException, FileNotFoundException,
			IOException {
		SAXBuilder builder = new SAXBuilder();
		InputStream file = new FileInputStream("src/main/java/sax/补充资料签收表.xml");
		Document document = builder.build(file);
		Element root = document.getRootElement();

		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			if(e.getName().equals("NodeSource")){
				Element e2 = e.getChild("Children");
				if(e2 != null){
					List<Element> list2 = e2.getChildren();
					if(list2 != null && list2.size()>0){
						for (Element element : list2) {
							Element e3 = element.getChild("Reader");
							if(e3 != null){
								Element e4 = e3.getChild("Connection");
								if(e4 != null){
									Element e5 = e4.getChild("Driver");
									Element e6 = e4.getChild("User");
									Element e7 = e4.getChild("Password");
									Element e8 = e4.getChild("Url");
									
									if(e5 != null){
										System.out.println(e5.getTextTrim());
									}
									if(e6 != null){
										System.out.println(e6.getTextTrim());
									}
									if(e7 != null){
										System.out.println(e7.getTextTrim());
									}
									if(e8 != null){
										System.out.println(e8.getTextTrim());
									}
								}
							}
						}
					}
				}
			}
		}
		XMLOutputter output = new XMLOutputter();
		output.output(document, new FileOutputStream("src/po.xml"));
	}

	// 修改数据
	public static void edit() throws JDOMException,
			FileNotFoundException, IOException {
		
		SAXBuilder builder = new SAXBuilder();
		InputStream file = new FileInputStream("src/main/java/sax/补充资料签收表.xml");
		Document document = builder.build(file);
		Element root = document.getRootElement();
		
		List<Element> list = root.getChildren();
		Iterator<Element> it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			if(e.getName().equals("NodeSource")){
				Element e2 = e.getChild("Children");
				if(e2 != null){
					List<Element> list2 = e2.getChildren();
					if(list2 != null && list2.size()>0){
						for (Element element : list2) {
							Element e3 = element.getChild("Reader");
							if(e3 != null){
								Element e4 = e3.getChild("Connection");
								if(e4 != null){
									Element e5 = e4.getChild("Driver");
									Element e6 = e4.getChild("User");
									Element e7 = e4.getChild("Password");
									Element e8 = e4.getChild("Url");
									
									if(e5 != null){
										e5.setText("net.sourceforge.jtds.jdbc.Driver");
									}
									if(e6 != null){
										e6.setText("sa");
									}
									if(e7 != null){
										e7.setText("ezoa");
									}
									if(e8 != null){
										e8.setText("jdbc:jtds:sqlserver://192.168.0.51:1433/AuditSystemDB;instance=SQLSERVER2008R2;SelectMethod=Cursor");
									}
								}
							}
						}
					}
				}
			}
		}
		XMLOutputter output = new XMLOutputter();
		output.output(document, new FileOutputStream("src/main/java/sax/补充资料签收表.xml"));
	}

	// 删除
	public static void del(int id) throws JDOMException, FileNotFoundException,
			IOException {
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build("src/po.xml");
		Element root = document.getRootElement();

		List list = root.getChildren();
		Iterator it = list.iterator();

		for (int i = 0; i < list.size(); i++) {
			Element e = (Element) it.next();
			if (Integer.parseInt(e.getAttributeValue("id")) == id) {
				root.removeContent(e);
				break;
			}
		}
		// 文件处理
		XMLOutputter out = new XMLOutputter();
		out.output(document, new FileOutputStream("src/po.xml"));
	}

	public static void main(String[] args) {
		// XmlTest.add();
		try {
			ModifyConnectionUtil.edit();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			ModifyConnectionUtil.list();
		} catch (JDOMException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		
		/*try {
			try {
				ModifyConnectionUtil.del(1);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		}*/
	}
}
