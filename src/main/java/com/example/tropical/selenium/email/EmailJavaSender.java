package com.example.tropical.selenium.email;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

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

import org.springframework.stereotype.Component;

@Component
public class EmailJavaSender {
	public static void emailJavaSender() {

		//         Recipient's email ID needs to be mentioned.
//		        String to = "arthur.schutz123@gmail.com";
		//        String to = \"vendas@tropicalimport.com.br";
		String to = "marcasregistradas@tropicalimport.com.br";

		// Sender's email ID needs to be mentioned
		String from = "arthurschutzdasilva@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", "465");
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.trust", "*");

		// Get the Session object.// and pass
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("arthurschutzdasilva@gmail.com",
						System.getenv("gmail.password"));

			}

		});
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			//            message.addRecipients(Message.RecipientType.TO, String.valueOf(to));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
			String dataFormatada = now.format(formatter);
			message.setSubject("Planilha Relatório Mercado Livre - " + dataFormatada);

			Multipart multipart = new MimeMultipart();

			MimeBodyPart attachmentPart = new MimeBodyPart();

			MimeBodyPart textPart = new MimeBodyPart();

			try {

				File f = new File("OceanTech-ML.xlsx");

				attachmentPart.attachFile(f);
				textPart.setText("Segue em anexo planilha");
				multipart.addBodyPart(textPart);
				multipart.addBodyPart(attachmentPart);
			} catch (IOException e) {

				e.printStackTrace();

			}

			message.setContent(multipart);

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}
}
