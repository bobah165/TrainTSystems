package com.trains.util;

import com.trains.controller.TrainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class EmailSender {

    private static String host = "smtp.gmail.com";
    private static int port = 587;
    private static String username = "MyTrainSender@gmail.com";
    private static String password = "qwER1234";
    private static final String fileName = "C://Users/ACER/IdeaProjects/TrainTsystems/src/main/resources/ticket.pdf";

    private static Logger logger = LoggerFactory.getLogger(TrainController.class);


    public static void sendMail() {

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.transport.protocol","smtps");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("MyTrainSender@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse("vekalynov@gmail.com"));
                message.setSubject("Mail Subject");

                String msg = "This is my first email using JavaMailer";

                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(msg, "text/html");

                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.attachFile(new File(fileName));

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                multipart.addBodyPart(attachmentBodyPart);

                message.setContent(multipart);

                Transport.send(message);

        } catch (Exception e) {
            logger.error("email doesn't send");
        }
    }
}
