package pl.edu.pwsztar.service;

import pl.edu.pwsztar.domain.dao.ClientDao;
import pl.edu.pwsztar.domain.dao.CureDao;

public interface MailService {
    void sendNotification(ClientDao client, CureDao cure);
    void sendEmailActivation(ClientDao client, String link);
}
