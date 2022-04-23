package com.example.tropical.selenium.email.products;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class EmailJavaProductsSender {

    public static void emailJavaProductSender() {
        // Recipient's email ID needs to be mentioned.
        String sendTo = "arthur.schutz123@gmail.com";
        String sendToSecond = "arthurschutzdasilva@gmail.com";
        List<String> to = new ArrayList<>();
        to.add(sendTo);
        to.add(sendToSecond);

        // Sender's email ID needs to be mentioned
        String from = "arthurschutzdasilva@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("arthurschutzdasilva@gmail.com",
                        "rjalevyscnrdbswe");

            }

        });
//session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipients(Message.RecipientType.TO, String.valueOf(to));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
            String dataFormatada = now.format(formatter);
            message.setSubject("Planilha Produtos Tropical - " + dataFormatada);

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {

                File f =new File("produtos-tropical-ml.xlsx");

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
