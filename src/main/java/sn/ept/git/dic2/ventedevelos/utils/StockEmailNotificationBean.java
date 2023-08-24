//package sn.ept.git.dic2.ventedevelos.utils;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import jakarta.ejb.EJB;
//import jakarta.ejb.Schedule;
//import jakarta.ejb.Singleton;
//import jakarta.ejb.Startup;
//import jakarta.mail.*;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
//import sn.ept.git.dic2.ventedevelos.entities.Produit;
//import sn.ept.git.dic2.ventedevelos.entities.Stock;
//import sn.ept.git.dic2.ventedevelos.facades.ProduitFacade;
//import sn.ept.git.dic2.ventedevelos.facades.StockFacade;
//
//import java.util.List;
//import java.util.Properties;
//
//@Singleton
//public class StockEmailNotificationBean {
//
//    @EJB
//    private ProduitFacade produitFacade;
//
//    private String getStocks(){
//        List<Produit> produits = produitFacade.findAll();
//        String message = "";
//        for(Produit p: produits){
//            int quantite = 0;
//            for(Stock s: p.getStockCollection())
//                quantite += s.getQuantite();
//            message += "\n" + p.getNom() + " : " + quantite + " unités";
//        }
//        return message;
//    }
//
//    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
//    private void sendStockStatus(){
//        String message = getStocks();
//        sendEmail("niangm@ept.sn", "Statut du stock", message);
//    }
//
//    private void sendEmail(String recipient, String subject, String body) {
//        // Paramètres de configuration pour le serveur de messagerie
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", "mail.galgit.com");
//        properties.put("mail.smtp.port", 587);
//        properties.put("mail.smtp.auth", "true");
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
