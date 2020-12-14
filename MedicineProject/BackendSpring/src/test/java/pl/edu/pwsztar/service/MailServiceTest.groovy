package pl.edu.pwsztar.service

import pl.edu.pwsztar.domain.dao.ClientDao
import pl.edu.pwsztar.domain.dao.CureDao
import pl.edu.pwsztar.service.serviceImpl.AccessServiceImpl
import pl.edu.pwsztar.service.serviceImpl.MailServiceImpl
import spock.lang.Specification

import javax.mail.MessagingException


class MailServiceTest extends Specification {
    def mailService = new MailServiceImpl()

    def "should send notification about cure on email"(){
        setup:
            def client = new ClientDao.Builder()
                    .clientId(1)
                    .email("mr.gigami@gmail.com")
                    .name("Mykola")
                    .surname("Pylypenko")
                    .phoneNumber("123456789")
                    .password(AccessServiceImpl.toHexString(AccessServiceImpl.getSHA("123456")))
                    .activatedEmail(true)
                    .build()
            def cure = new CureDao.Builder()
                    .cureId(1)
                    .name("Some medicine")
                    .dailyDose(24)
                    .doseTimestamp(1)
                    .doseNumber(15)
                    .build()
        when:
            mailService.sendNotification(client, cure)
        then:
            notThrown(MessagingException)
    }

    def "should send email confirmation link"(){
        setup:
            def client = new ClientDao.Builder()
                    .clientId(1)
                    .email("mr.gigami@gmail.com")
                    .name("Mykola")
                    .surname("Pylypenko")
                    .phoneNumber("123456789")
                    .password(AccessServiceImpl.toHexString(AccessServiceImpl.getSHA("123456")))
                    .activatedEmail(true)
                    .build()
            def link = AccessServiceImpl.toHexString(AccessServiceImpl.getSHA(client.toString()))
        when:
            mailService.sendEmailActivation(client, link)
        then:
            notThrown(MessagingException)
    }
}
