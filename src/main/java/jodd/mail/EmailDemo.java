package jodd.mail;

/*
 * POP3 SMTP IMAP 
 * 
 * SMTPЭ����� SMTP��Simple Mail Transfer Protocal��
 * ��Ϊ���ʼ�����Э�飬Ŀ�������û��ṩ��Ч���ɿ����ʼ����䡣 SMTP��һ����Ҫ�ص������ܹ��ڴ����н��������ʼ���
 * ���ʼ�����ͨ��ͬ�����ϵ��������ʽ���͡���������������£�һ�ǵ����ʼ��ӿͻ����䵽�����������Ǵ�ĳһ�����������䵽��һ����������
 *  SMTP�Ǹ�����/��ӦЭ�飬�����25�Ŷ˿ڣ����ڽ����û����ʼ����󣬲���Զ���ʼ�����������SMTP���ӡ� 
 *  -------------------------------------------------------------------------------- 
 *  3.SMTP�������� SMTPͨ�������ֹ���ģʽ������SMTP�ͽ���SMTP�����幤����ʽΪ������SMTP�ڽӵ��û����ʼ������
 *  �жϴ��ʼ��Ƿ�Ϊ�����ʼ�������ֱ��Ͷ�͵��û������䣬������DNS��ѯԶ���ʼ���������MX��¼����������Զ�˽���SMTP֮���һ��˫����ͨ����
 *  �˺�SMTP�����ɷ���SMTP�������ɽ���SMTP���գ���Ӧ���򷴷��洫�͡�һ������ͨ��������SMTP�����߷���MAIL����ָ���ʼ������ߡ�
 *  ���SMTP�����߿��Խ����ʼ��򷵻�OKӦ��SMTP�������ٷ���RCPT����ȷ���ʼ��Ƿ���յ������SMTP�����߽��գ��򷵻�OKӦ��
 *  ����ܽ��յ����򷢳��ܾ����Ӧ�𣨵�����ֹ����ʼ���������˫��������ظ���Ρ����������յ�ȫ���ʼ������յ��ر�����У�
 *  �������߳ɹ��������ʼ����򷵻�OKӦ��
 *  4.POPЭ���� POP��ȫ���� Post Office Protocol �����ʾ�Э�飬
 *  ���ڵ����ʼ��Ľ��գ���ʹ��TCP��110�˿ڣ����ڳ��õ��ǵ���棬���Լ��Ϊ POP3�� POP3����Client/Server����ģʽ��
 *  ���ͻ�����Ҫ����ʱ���ͻ��˵������Outlook Express��FoxMail������POP3����������TCP���ӣ��˺�Ҫ����POP3Э������ֹ���״̬��
 *  ��������֤��̣�ȷ�Ͽͻ����ṩ���û�������룬����֤ͨ����ת�봦��״̬���ڴ�״̬���û�����ȡ�Լ����ʼ������ʼ���ɾ��
 *  �������Ӧ�Ĳ�����ͻ���㷢��quit����˺��������״̬������ɾ���ǵ��ʼ��ӷ�������ɾ���������Ϊֹ���POP�����ɡ� 
 *  -------------------------------------------------------------------------------- 
 *  5.IMAPЭ���� IMAP��Internet Message Access Protocol����д������˼�壬��Ҫ�ṩ����ͨ��Internet��ȡ��Ϣ��һ��Э�顣
 *  IMAP��POP�����ṩ�˷�����ʼ����ط������û��ܽ��������Ķ�����IMAP����ɵ�ȴԶԶ��ֻ��Щ��
 *  IMAP�ṩ��ժҪ������ܿ����������Ķ������е��ʼ�����ʱ�䡢���⡢�����ˡ���С����Ϣ��������Ƿ����صľ�����
*/

import jodd.mail.Email;

import jodd.mail.SendMailSession;
import jodd.mail.SimpleAuthenticator;
import jodd.mail.SmtpServer;

public class EmailDemo {
	public static void main(String[] args) {
		Email email = Email.create().from("mailfendou@163.com")
				.to("526737403@qq.com").subject("Hello!")
				.addText("A plain text message...");
		
		Email emai2 = Email.create()
				.from("mailfendou@163.com")
				.to("526737403@qq.com")
		        .subject("Hello HTML!")
		        .addHtml("<b>HTML</b> message...");
		
		Email emai3 = Email.create()
				.from("mailfendou@163.com")
				.to("526737403@qq.com")
		        .subject("Hello!")
		        .addText("text message...")
		        .addHtml("<b>HTML</b> message...")
		        .priority(Email.PRIORITY_HIGHEST);
		
		SmtpServer smtpServer = new SmtpServer("smtp.163.com");
		smtpServer.authenticateWith("mailfendou", "yejing881213");
		SendMailSession session = smtpServer.createSession();
	    session.open();
	    session.sendMail(email);// 发送成功
	    session.sendMail(emai2); // 发送失败
	    session.sendMail(emai3); // 发送失败
	    session.close();
	    
//	    Pop3Server popServer = new Pop3Server("pop3.163.com",
//	            new SimpleAuthenticator("mailfendou@163.com", "yejing881213"));
//	    ReceiveMailSession session = popServer.createSession();
//	    session.open();
//	    System.out.println(session.getMessageCount());
//	    ReceivedEmail[] emails = session.receiveEmailAndMarkSeen();
//	    if (emails != null) {
//	        for (ReceivedEmail email : emails) {
//	            System.out.println("\n\n===[" + email.getMessageNumber() + "]===");
//
//	            // common info
//	            Printf.out("%0x", email.getFlags());
//	            System.out.println("FROM:" + email.getFrom());
//	            System.out.println("TO:" + email.getTo()[0]);
//	            System.out.println("SUBJECT:" + email.getSubject());
//	            System.out.println("PRIORITY:" + email.getPriority());
//	            System.out.println("SENT DATE:" + email.getSentDate());
//	            System.out.println("RECEIVED DATE: " + email.getReceiveDate());
//
//	            // process messages
//	            List messages = email.getAllMessages();
//	            for (EmailMessage msg : messages) {
//	                System.out.println("------");
//	                System.out.println(msg.getEncoding());
//	                System.out.println(msg.getMimeType());
//	                System.out.println(msg.getContent());
//	            }
//
//	            // process attachments
//	            List<EmailAttachment> attachments = email.getAttachments();
//	            if (attachments != null) {
//	                System.out.println("+++++");
//	                for (EmailAttachment attachment : attachments) {
//	                    System.out.println("name: " + attachment.getName());
//	                    System.out.println("cid: " + attachment.getContentId());
//	                    System.out.println("size: " + attachment.getSize());
//	                    attachment.writeToFile(
//	                        new File("d:\\", attachment.getName()));
//	                }
//	            }
//	        }
//	    }
//	    session.close();

	}
}
