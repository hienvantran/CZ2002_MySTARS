package myStars;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NotificationCtrl {
    public void sendMail(String receiver, String courseCode, int IndexNo, int type, String peerID, int peerIndex)
    {
        String TextMsg = null;
        String subject = null;
        System.out.println("Preparing to send email...");
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String myAccountEmail = "mapleseaok@gmail.com";
        String password = "mapleseaok";

        Session session = Session.getInstance(props,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        switch(type)
        {
            case(1):
                subject = "Register Course";
                TextMsg = "You have successfully REGISTERED for " + courseCode + " with index: " + IndexNo;
                break;
            case(2):
                subject = "Added to the Waiting List";
                TextMsg = "The course: " + courseCode + "\n Index No: " + IndexNo + " you want to registered for is full, you will be added to the Waiting List";
                break;
            case(3):
                subject = "Drop Course";
                TextMsg = "You have successfully DROP " + courseCode + " with index: " + IndexNo;
                break;
            case(4):
                subject = "Change Index";
                TextMsg = "You have successfully changed " + courseCode + " to your new index: " + IndexNo;
                break;
            case(5):
                subject = "Swap Index";
                TextMsg = "You have successfully swapped " + courseCode + " your index: "+ IndexNo + " with: " + peerID + " indexNo: " + peerIndex;
                break;
        }
        try
        {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver)); // to be added an email addr
            message.setSubject(subject);
            message.setText(TextMsg);
            Transport.send(message);
            System.out.println("Message send successfully :D");

        }
        catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }
}
