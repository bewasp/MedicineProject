package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.dao.ClientDao;
import pl.edu.pwsztar.domain.dao.CureDao;
import pl.edu.pwsztar.service.MailService;
import pl.edu.pwsztar.service.serviceImpl.global.GlobalVariables;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {
    private static final String email = "medicine.notification@gmail.com";
    private static final String password = "198343583";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    public MailServiceImpl() {
    }

    private static Session connect(){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        javax.mail.Session session = javax.mail.Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    @Override
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(email, password);
                    }
                });

        return session;
    }

    private static Message config(String toClient, String subject, String text) throws MessagingException {
        Session session = connect();
        javax.mail.Message message = new javax.mail.internet.MimeMessage(session);
        message.setFrom(new InternetAddress(email));
        message.setRecipients(
                javax.mail.Message.RecipientType.TO,
                InternetAddress.parse(toClient)
        );
        message.setSubject(subject);
        message.setText(text);

        return message;
    }

    @Override
    public void sendNotification(ClientDao client, CureDao cure) {
        String toClient = "";
        String subject = "";
        String text = "";

        Calendar cal = GregorianCalendar.getInstance();
        cal.add(Calendar.MINUTE, GlobalVariables.getInstance().notificationTime + GlobalVariables.getInstance().testAddingTime);

        try {
            toClient = client.getEmail();
            subject = "Medicine: " +cure.getName();
            text =  "Take your medicine called:" + cure.getName() +
                    "\nin dose number: " + cure.getDoseNumber() +
                    "\nYou have to take your cure in " + dateFormat.format(new Date()).substring(0,11) + cal.get(Calendar.HOUR) + ':' + cal.get(Calendar.MINUTE);

            Message message = config(toClient, subject, text);

            javax.mail.Transport.send(message);

        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailActivation(ClientDao client, String link) {
        String toClient = "";
        String subject = "";
        String text = "";

        try {
            toClient = client.getEmail();
            subject = "Medicine App: Account activation";
            text = "Please, click this link to verify your email\n:" +
                    "http://localhost:4200/register/confirm-email/" + link;

            Message message = config(toClient, subject, text);

            javax.mail.Transport.send(message);

        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }
}
