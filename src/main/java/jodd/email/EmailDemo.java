package jodd.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

import jodd.mail.Email;
import jodd.mail.EmailAttachment;
import jodd.mail.EmailAttachmentBuilder;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;
import jodd.mail.SmtpSslServer;

public class EmailDemo {
	public static void main(String[] args) {
		// sendQQMail();
		try {
			send163Mail();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void sendQQMail() {

		Email email = Email.create().from("mailfendou@163.com").to(
				"526737403@qq.com").subject("testQQ").addText("ab���!cd")
				.addHtml(
						"<html><META http-equiv=Content-Type content=\"text/html; charset=utf-8\">"
								+ "<body><h1>���!</h1></body></html>");
		SendMailSession mailSession = new SmtpSslServer("smtp.163.com")//
				.authenticateWith("mailfendou", "yejing881213")//
				.timeout(5000).createSession();//
		mailSession.open();
		mailSession.sendMail(email);
		mailSession.close();
		System.out.println("����QQ�ɹ�!...");
	}

	public static void send163Mail() throws FileNotFoundException,
			UnsupportedEncodingException {
		String filePath = URLDecoder.decode(EmailDemo.class.getClassLoader()
				.getResource("SystemGlobals.properties").getPath(), "UTF-8");
		System.out.println(filePath);
		Email email = Email.create()//
				.from("526737403@qq.com")//
				.to("mailfendou@163.com")//
				.subject("test163")//
				.addHtml(getHtmlMsg("�����ʼ�", "�����ó������ʼ���"))//
				.addText("ab���!cd")//
				.addHtml("<h1>���v</h1></body></html>") //
				.embed(EmailAttachment.attachment().stream(new FileInputStream(filePath))) // �ļ���.bin��׺
				.embed(EmailAttachment.attachment().file(filePath)) // ԭ�ļ���׺
				.embed(EmailAttachment.attachment()//
				.bytes(new File(filePath))) // ԭ�ļ���׺
				.embed(EmailAttachment.attachment().bytes(new FileInputStream(filePath))) // .bin��׺
				;// 

		SendMailSession mailSession = new SmtpServer("smtp.qq.com")//
				.authenticateWith("526737403", "YEJING881213,")//
				.createSession();//
		mailSession.open();
		mailSession.sendMail(email);
		mailSession.close();
		System.out.println("����163�ɹ�!...");
	}

	public static String getHtmlMsg(String subject, String content) {
		StringBuilder sb = new StringBuilder("");
		sb.append("<HTML>\n");
		sb.append("<HEAD>\n");
		sb.append("<TITLE>\n");
		sb.append(subject + "\n");
		sb.append("</TITLE>\n");
		sb.append("</HEAD>\n");
		sb.append("<BODY>\n");
		sb.append("<H1>" + subject + "</H1>" + "\n");
		sb.append(content);
		sb.append("\n");
		sb.append("<a href='http://www.baidu.com'>�ٶ�</a>");
		sb.append("\n");
		sb.append("</BODY>\n");
		sb.append("</HTML>\n");
		return sb.toString();
	}
}
