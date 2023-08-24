//package sn.ept.git.dic2.ventedevelos.utils;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import jakarta.ejb.Singleton;
//import jakarta.ejb.Startup;
//import jakarta.mail.*;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
//import java.util.Properties;
//
//@Singleton
//@Startup
//public class EmailNotificationBean {
//
//    @PostConstruct
//    public void startup() {
//        sendEmail("niangm@ept.sn", "Application started", "The application has been started.");
//    }
//
//    @PreDestroy
//    public void shutdown() {
//        sendEmail("niangm@ept.sn","Application stopped", "The application is being stopped.");
//    }
//
//    private void sendEmail(String recipient, String subject, String body) {
//        // Paramètres de configuration pour le serveur de messagerie
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", "mail.galgit.com");
//        properties.put("mail.smtp.port", 587);
//        properties.put("mail.smtp.auth", "true");
////        properties.put("mail.smtp.starttls.enable", "true");
//
//        // Informations d'authentification pour le serveur de messagerie
//        final String username = "dic2-2023@galgit.com";
//        final String password = "sn-ept@GIT2024";
//
//        // Création d'une session de messagerie avec les informations d'authentification
//        Session session = Session.getInstance(properties, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        try {
//            // Création d'un message MIME
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
//            message.setSubject(subject);
//            message.setText(body);
//
//            // Envoi du message
//            Transport.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            // Gérer les exceptions liées à l'envoi de l'e-mail
//        }
//    }
//}
