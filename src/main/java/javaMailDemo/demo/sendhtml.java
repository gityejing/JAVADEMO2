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
import java.net.URLDecoder;
import java.util.Properties;
import java.util.Date;

import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;
import javax.mail.util.*;

public class sendhtml {

	
	
	public static void main(String[] argv) {
		new sendhtml(argv);
	}

	public sendhtml(String[] argv) {

		String url = "smtp.163.com";
		String mailer = "sendhtml";
		String protocol = null, host = null;
		String record = null; // name of folder in which to record mail
		 
		String mailhost = "smtp.163.com"; 
		String from = "mailfendou@163.com"; 
		String to = "526737403@qq.com"; 
		String user = "mailfendou"; 
		String password = "yejing881213";
		String subject = "≤‚ ‘” º˛"; 
		String cc = null; 
		String bcc = null;
		Boolean debug = false;
		
		try {
			Properties props = new Properties();
			props.put("mail.host", mailhost);
			props.setProperty("mail.transport.protocol","smtp");
			props.setProperty("mail.smtp.auth", "true");
			
			// Get a Session object
			Session session = Session.getDefaultInstance(props);
			session.setDebug(debug);

			// construct the message
			Message msg = new MimeMessage(session);
			if (from != null)
				msg.setFrom(new InternetAddress(from));
			else
				msg.setFrom();

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			if (cc != null)
				msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));
			if (bcc != null)
				msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc, false));
			if(subject != null)
				msg.setSubject(subject);
			
			String path = URLDecoder.decode(sendhtml.class.getResource("README.txt").getPath(), "UTF-8");
			BufferedReader in = new BufferedReader(new FileReader(path));
			collect(in, msg);
			msg.setHeader("X-Mailer", mailer);
			msg.setSentDate(new Date());

			// send the thing off
			Transport ts = session.getTransport();
			ts.connect(user, password);
			ts.sendMessage(msg, msg.getAllRecipients());
			ts.close();
			
			System.out.println("\n Mail was sent successfully.");

			// Keep a copy, if requested.
			if (record != null) {
				// Get a Store object
				Store store = null;
				if (url != null) {
					URLName urln = new URLName(url);
					store = session.getStore(urln);
					store.connect();
				} else {
					if (protocol != null)
						store = session.getStore(protocol);
					else
						store = session.getStore();

					// Connect
					if (host != null || user != null || password != null)
						store.connect(host, user, password);
					else
						store.connect();
				}

				// Get record Folder. Create if it does not exist.
				Folder folder = store.getFolder(record);
				if (folder == null) {
					System.err.println("Can't get record folder.");
					System.exit(1);
				}
				if (!folder.exists())
					folder.create(Folder.HOLDS_MESSAGES);

				Message[] msgs = new Message[1];
				msgs[0] = msg;
				folder.appendMessages(msgs);

				System.out.println("Mail was recorded successfully.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void collect(BufferedReader in, Message msg)
		throws MessagingException, IOException {
		String line;
		String subject = msg.getSubject();
		StringBuffer sb = new StringBuffer();
		sb.append("<HTML>\n");
		sb.append("<HEAD>\n");
		sb.append("<TITLE>\n");
		sb.append(subject + "\n");
		sb.append("</TITLE>\n");
		sb.append("</HEAD>\n");
		
		sb.append("<BODY>\n");
		sb.append("<H1>" + subject + "</H1>" + "\n");
		
		while((line = in.readLine()) != null){
			sb.append(line);
			sb.append("\n");
		}
		
		sb.append("</BODY>\n");
		sb.append("</HTML>\n");
		
		msg.setDataHandler(new DataHandler(new ByteArrayDataSource(sb.toString(), "text/html")));
	}
}
