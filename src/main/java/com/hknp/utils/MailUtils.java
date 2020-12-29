package com.hknp.utils;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class MailUtils {
   static final String SMTP_HOST = "smtp.gmail.com";
   static final String SMTP_PORT = "587";

   static final String AUTH_MAIL_ADDRESS = "ecommerce.webservice@gmail.com";
   static final String AUTH_MAIL_USERNAME = "ecommerce.webservice";
   static final String AUTH_MAIL_PASSWORD = "hknp0000";

   static Authenticator getAuthenticator() {
      return new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(AUTH_MAIL_USERNAME, AUTH_MAIL_PASSWORD);
         }
      };
   }

   static Properties getProperties() {
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", SMTP_HOST);
      props.put("mail.smtp.port", SMTP_PORT);
      return props;
   }

   public static boolean send(String receiveEmail, String subject, String body) {
      Session session = Session.getInstance(getProperties(), getAuthenticator());
      try {
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(AUTH_MAIL_ADDRESS));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiveEmail));
         message.setSubject(subject);
         message.setText(body);

         // Send message
         Transport.send(message);
         return true;
      } catch (MessagingException e) {
         e.printStackTrace();
         return false;
      }
   }

   public static boolean send(String receiveEmail, String subject, Object contentObjet, String contentType) {
      Session session = Session.getInstance(getProperties(), getAuthenticator());
      try {
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(AUTH_MAIL_ADDRESS));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiveEmail));
         message.setSubject(subject);
         message.setContent(contentObjet, contentType);

         // Send message
         Transport.send(message);
         return true;
      } catch (MessagingException e) {
         e.printStackTrace();
         return false;
      }
   }
}
