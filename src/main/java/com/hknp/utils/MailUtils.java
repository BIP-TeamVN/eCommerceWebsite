package com.hknp.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * This class provide methods that help you to work with <b>Java mail API</b>
 */
public class MailUtils {
   static final String SMTP_HOST = System.getenv("SMTP_HOST") == null ? "smtp.gmail.com" : System.getenv("SMTP_HOST");
   static final String SMTP_PORT = System.getenv("SMTP_PORT") == null ? "587" : System.getenv("SMTP_PORT");

   static final String SMTP_ADDRESS = System.getenv("SMTP_ADDRESS");
   static final String SMTP_USERNAME = System.getenv("SMTP_USERNAME");
   static final String SMTP_PASSWORD = System.getenv("SMTP_PASSWORD");

   /**
    * Get Authenticator by {@link #SMTP_USERNAME}
    * and {@link #SMTP_PASSWORD}
    *
    * @return {@link javax.mail.Authenticator} object
    */
   static Authenticator getAuthenticator() {
      return new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(
                    SMTP_USERNAME,
                    SMTP_PASSWORD
            );
         }
      };
   }

   /**
    * Get Properties and                                       <br/>
    * set <code>mail.smtp.auth</code> = <code>true</code>      <br/>
    * and <code>mail.smtp.starttls</code> = <code>true</code>  <br/>
    * and <code>mail.smtp.host</code> = {@link #SMTP_HOST}     <br/>
    * and <code>mail.smtp.port</code> = {@link #SMTP_PORT}     <br/>
    *
    * @return {@link java.util.Properties} object
    */
   static Properties getProperties() {
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", SMTP_HOST);
      props.put("mail.smtp.port", SMTP_PORT);
      return props;
   }

   /**
    * Send email using SMTP Java mail <br/>
    * with default <code>contentType</code> = <code>text/plain</code>
    *
    * @param receiveEmail recipient email address
    * @param subject      email subject
    * @param body         email body
    * @return <code>true</code> if sent email successfully<br>
    * <code>false</code> otherwise
    * @see #send(String, String, Object, String)
    */
   public static boolean sendPlanText(String receiveEmail, String subject, Object body) {
      return send(receiveEmail, subject, body, "text/plain");
   }

   /**
    * Send email using SMTP Java mail <br/>
    * with default <code>contentType</code> = <code>text/html</code>
    *
    * @param receiveEmail recipient email address
    * @param subject      email subject
    * @param body         email body
    * @return <code>true</code> if sent email successfully<br>
    * <code>false</code> otherwise
    * @see #send(String, String, Object, String)
    */
   public static boolean sendHtmlText(String receiveEmail, String subject, Object body) {
      return send(receiveEmail, subject, body, "text/html");
   }

   /**
    * Send email using SMTP Java mail
    *
    * @param receiveEmail recipient email address
    * @param subject      email subject
    * @param body         email body
    * @param contentType  type of email body
    * @return <code>true</code> if sent email successfully<br>
    * <code>false</code> otherwise
    */
   public static boolean send(String receiveEmail, String subject, Object body, String contentType) {
      Session session = Session.getInstance(getProperties(), getAuthenticator());
      try {
         Message message = new MimeMessage(session);

         message.setFrom(new InternetAddress(SMTP_ADDRESS));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiveEmail));
         message.setSubject(subject);
         message.setContent(body, contentType + "; charset=UTF-8");

         // Send message
         Transport.send(message);
         return true;
      } catch (MessagingException e) {
         e.printStackTrace();
         return false;
      }
   }
}
