package com.hknp.utils;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class MailUtils {
   static final String SMTP_HOST = "smtp.gmail.com";
   static final String SMTP_PORT = "587";

   static final String AUTH_MAIL_ADDRESS = "";
   static final String AUTH_MAIL_USERNAME = "";
   static final String AUTH_MAIL_PASSWORD = "";

   public static void send(){
      // Recipient's email ID needs to be mentioned.
      String to = "xyz@gmail.com";//change accordingly

      // Sender's email ID needs to be mentioned

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", SMTP_HOST);
      props.put("mail.smtp.port", SMTP_PORT);

      // Get the Session object.
      Session session = Session.getInstance(props,
              new javax.mail.Authenticator() {
                 protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(AUTH_MAIL_USERNAME, AUTH_MAIL_PASSWORD);
                 }
              });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(AUTH_MAIL_ADDRESS));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
                 InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Testing Subject");

         // Now set the actual message
         message.setText("Hello, this is sample for to check send "
                 + "email using JavaMailAPI ");

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
}
