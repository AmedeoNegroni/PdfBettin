package it.ariadne;


import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	
	//String username, String password, String mailFrom

	public static void send(String mailTo, String fileToSend, String nameFileToSend) {
		
		final String username = "mail";
		final String password = "password";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("amedeo101094@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(mailTo));
			message.setSubject("Cedolino di Febbraio");
			message.setText("I vostri bellissimi cedolini");
			

			MimeBodyPart messageBodyPart = new MimeBodyPart();

		    Multipart multipart = new MimeMultipart();
		    
		    messageBodyPart = new MimeBodyPart();
	        String file = fileToSend;
	        String fileName = nameFileToSend;
	        DataSource source = new FileDataSource(file);
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setFileName(fileName);
	        multipart.addBodyPart(messageBodyPart);
		        
		    message.setContent(multipart);
			
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
