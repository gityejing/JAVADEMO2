/*
 * Copyright (c) 1998-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package javaMailDemo.demo;

import java.io.*;
import java.util.Properties;
import java.util.Date;

import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.util.*;

public class SendMail {

	public static void main(String[] argv) {
		try {
			sendSimpleEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendSimpleEmail() throws Exception {
		String host = "smtp.163.com";
		String from = "mailfendou@163.com";
		String username = "mailfendou";
		String password = "yejing881213";
		String to = "526737403@qq.com";
		
		// 设置邮件的参数，根据参数生成会话。
		Properties prop = new Properties();
		prop.setProperty("mail.host", host);
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(prop);
		session.setDebug(true);
		// 获取邮件内容
		MimeMessage message = getMessage(from, to);
		// 连接服务器，发送邮件
		Transport ts = session.getTransport();
		ts.connect(username, password);
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}

	// 此方法生成邮件的内容
	private static MimeMessage getMessage(String from, String recipient)
			throws Exception {
		MimeMessage message = new MimeMessage(Session
				.getDefaultInstance(new Properties()));
		message.setFrom(new InternetAddress(from));
		message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
		message.setSubject("把这功能学会了，就能发世界上最难的邮件！");

		// 邮件中的文本内容
		MimeBodyPart mbpText = new MimeBodyPart();
		mbpText.setContent("我是邮件的内容，可以显示图片哦!","text/html;charset=UTF-8");

		// 邮件中的图片
//		MimeBodyPart mbpImage = new MimeBodyPart();
//		DataHandler dhImage = new DataHandler(new FileDataSource("C:/test.jpg"));
//		mbpImage.setDataHandler(dhImage);
//		mbpImage.setContentID("test.jpg");

		// 邮件中的附件
//		MimeBodyPart mbpAttmen = new MimeBodyPart();
//		DataHandler dhAttmen = new DataHandler(
//				new FileDataSource("C:/test.txt"));
//		mbpAttmen.setDataHandler(dhAttmen);
//		// 邮件中在文本内容和附件路径会出现中文乱码，所以使用MimeUtility进行编码。
//		mbpAttmen.setFileName(MimeUtility.encodeText(dhAttmen.getName()));

//		// 将图片与文本内容放到一个单元里
		MimeMultipart mm1 = new MimeMultipart();
		mm1.addBodyPart(mbpText);
//		mm1.addBodyPart(mbpImage);
//		// 描述正文和图片的关系
//		mm1.setSubType("related");
//		// 返回需要将附加与文本和图片添加到一个单元里。
//		// MimeMultipart不能添加MimeMultipart，但可以添加MimeBodyPart。
//		MimeBodyPart text_img = new MimeBodyPart();
//		text_img.setContent(mm1);
//
//		// 将文本、图片与附件添加到一个单元里
//		MimeMultipart mm2 = new MimeMultipart();
//		mm2.addBodyPart(mbpAttmen);
//		mm2.addBodyPart(text_img);
//		mm2.setSubType("mixed");
		// 将内容添加到message中
//		message.setContent(mm2);
//		message.saveChanges();
		message.setContent(mm1);
		return message;
	}
}
