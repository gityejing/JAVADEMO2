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
		
		// �����ʼ��Ĳ��������ݲ������ɻỰ��
		Properties prop = new Properties();
		prop.setProperty("mail.host", host);
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(prop);
		session.setDebug(true);
		// ��ȡ�ʼ�����
		MimeMessage message = getMessage(from, to);
		// ���ӷ������������ʼ�
		Transport ts = session.getTransport();
		ts.connect(username, password);
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}

	// �˷��������ʼ�������
	private static MimeMessage getMessage(String from, String recipient)
			throws Exception {
		MimeMessage message = new MimeMessage(Session
				.getDefaultInstance(new Properties()));
		message.setFrom(new InternetAddress(from));
		message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
		message.setSubject("���⹦��ѧ���ˣ����ܷ����������ѵ��ʼ���");

		// �ʼ��е��ı�����
		MimeBodyPart mbpText = new MimeBodyPart();
		mbpText.setContent("�����ʼ������ݣ�������ʾͼƬŶ!","text/html;charset=UTF-8");

		// �ʼ��е�ͼƬ
//		MimeBodyPart mbpImage = new MimeBodyPart();
//		DataHandler dhImage = new DataHandler(new FileDataSource("C:/test.jpg"));
//		mbpImage.setDataHandler(dhImage);
//		mbpImage.setContentID("test.jpg");

		// �ʼ��еĸ���
//		MimeBodyPart mbpAttmen = new MimeBodyPart();
//		DataHandler dhAttmen = new DataHandler(
//				new FileDataSource("C:/test.txt"));
//		mbpAttmen.setDataHandler(dhAttmen);
//		// �ʼ������ı����ݺ͸���·��������������룬����ʹ��MimeUtility���б��롣
//		mbpAttmen.setFileName(MimeUtility.encodeText(dhAttmen.getName()));

//		// ��ͼƬ���ı����ݷŵ�һ����Ԫ��
		MimeMultipart mm1 = new MimeMultipart();
		mm1.addBodyPart(mbpText);
//		mm1.addBodyPart(mbpImage);
//		// �������ĺ�ͼƬ�Ĺ�ϵ
//		mm1.setSubType("related");
//		// ������Ҫ���������ı���ͼƬ��ӵ�һ����Ԫ�
//		// MimeMultipart�������MimeMultipart�����������MimeBodyPart��
//		MimeBodyPart text_img = new MimeBodyPart();
//		text_img.setContent(mm1);
//
//		// ���ı���ͼƬ�븽����ӵ�һ����Ԫ��
//		MimeMultipart mm2 = new MimeMultipart();
//		mm2.addBodyPart(mbpAttmen);
//		mm2.addBodyPart(text_img);
//		mm2.setSubType("mixed");
		// ��������ӵ�message��
//		message.setContent(mm2);
//		message.saveChanges();
		message.setContent(mm1);
		return message;
	}
}
