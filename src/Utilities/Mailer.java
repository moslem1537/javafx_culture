/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author mohamed
 */
public class Mailer {

    public static void sendPasswordMail(String recepient, int resetCode) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "mohamed.mezghani@esprit.tn";
        //Your gmail password
        String password = "191JMT1561";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient, resetCode);

        //Send mail
        System.out.println("hani bech neb3ath");
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, int resetCode) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Reinitialisation de votre mot de passe");
            String htmlCode = "<br/> <h2><b>Votre code de reinitialisation est : <p style:'color : blue'>" + resetCode + "</p></b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
