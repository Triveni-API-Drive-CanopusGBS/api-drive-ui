package com.ttl.ito.common.Utility;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import org.springframework.stereotype.Component;

@Component
public class SendMail {

	private Boolean debugMode;

	public SendMail() {
		debugMode = false;
	}

	protected String emailHost = "smtp.gmail.com";

	protected String emailFrom = "itoapplication@triveniturbines.com";
	private String username ="itoapplication@triveniturbines.com";
	private String password ="itouser1234";
	public void send(String[] to, String[] cc, String[] bcc, String subject, String htmlMessage, File... attachments)
			throws MessagingException, IOException {
		final Properties props = new Properties();

		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", emailHost);
		props.setProperty("mail.smtp.debug", debugMode.toString());
		//props.setProperty("mail.smtp.socketFactory.port", "465");    //25, 465, 587
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable","true");
		//props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		
		Authenticator authenticator = new Authenticator() {
				   
				      protected PasswordAuthentication getPasswordAuthentication() {  
				    return new PasswordAuthentication(username,password);  
				      }  
				    };  
		
		
		final Session session = Session.getDefaultInstance(props,authenticator);
		session.setDebug(true);
		final MimeMessage message = new MimeMessage(session);

		for (String messageTo : to) {
			message.addRecipients(RecipientType.TO, messageTo);
		}
		if (cc != null) {
			for (String messageCc : cc) {
				message.addRecipients(RecipientType.CC, messageCc);
			}
		}
		
		if (bcc != null) {
			for (String messageBcc : bcc) {
				message.addRecipients(RecipientType.BCC, messageBcc);
			}
		}
		message.setSubject(subject);
		
		message.setFrom(new InternetAddress(emailFrom));
		
		final MimeMultipart multipartMessage = new MimeMultipart();
		
		final MimeBodyPart body = new MimeBodyPart();
		body.setContent(htmlMessage, "text/plain");
		multipartMessage.addBodyPart(body);
		
		final MimeBodyPart attachment = new MimeBodyPart();
		if(attachments!=null){
			for (File attachmentFile : attachments) {
				attachment.attachFile(attachmentFile);
				multipartMessage.addBodyPart(attachment);
			}
		}
		message.setContent(multipartMessage);
		Transport.send(message);
	}
	// Added for attachments
	public void sendMail(String[] to, String[] cc, String[] bcc, String subject, String htmlMessage, List<String> filePath)
			throws MessagingException, IOException {
		final Properties props = new Properties();

		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", emailHost);
		props.setProperty("mail.smtp.debug", debugMode.toString());
		//props.setProperty("mail.smtp.socketFactory.port", "465");    //25, 465, 587
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable","true");
		
		Authenticator authenticator = new Authenticator() {
				   
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};
		
		
		final Session session = Session.getDefaultInstance(props,authenticator);
		session.setDebug(true);
		final MimeMessage message = new MimeMessage(session);

		for (String messageTo : to) {
			message.addRecipients(RecipientType.TO, messageTo);
		}
		if (cc != null) {
			for (String messageCc : cc) {
				message.addRecipients(RecipientType.CC, messageCc);
			}
		}
		
		if (bcc != null) {
			for (String messageBcc : bcc) {
				message.addRecipients(RecipientType.BCC, messageBcc);
			}
		}
		message.setSubject(subject);
		
		message.setFrom(new InternetAddress(emailFrom));
		
		BodyPart  body = new MimeBodyPart();
		final Multipart multipartMessage = new MimeMultipart();

		body.setContent(htmlMessage, "text/plain");  
		multipartMessage.addBodyPart(body); 
		
		//  added to test attachment
		if(filePath!=null){
			for(String files:filePath){
			addAttachmentFile(multipartMessage,files);
		}
		}
		message.setContent(multipartMessage);
		Transport.send(message);
	}
	private void addAttachmentFile(Multipart multipartMessage, String filename) throws MessagingException
	{
		FileDataSource  source = new FileDataSource(filename);     
	    BodyPart body = new MimeBodyPart();
        body.setDataHandler(new DataHandler(source));
        body.setFileName(source.getFile().getName());
        multipartMessage.addBodyPart(body);
	}
	// Added for attachments
}