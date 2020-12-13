package pl.edu.pwsztar.service

import pl.edu.pwsztar.domain.dao.ClientDao
import pl.edu.pwsztar.domain.dto.cure.CureDto
import pl.edu.pwsztar.domain.entity.Client
import pl.edu.pwsztar.domain.entity.Cure
import pl.edu.pwsztar.domain.mapper.CureDtoMapper
import pl.edu.pwsztar.domain.repository.ClientDoseRepository
import pl.edu.pwsztar.domain.repository.ClientRepository
import pl.edu.pwsztar.service.serviceImpl.AccessServiceImpl
import pl.edu.pwsztar.service.serviceImpl.ClientDoseServiceImpl
import spock.lang.Specification

class ClientDoseServiceTest extends Specification{
    def clientDoseRepository = Mock(ClientDoseRepository)
    def clientRepository = Mock(ClientRepository)
    def cureListMapper = new CureDtoMapper()

    def clientDoseService = new ClientDoseServiceImpl(clientDoseRepository, clientRepository, cureListMapper)

    def "should add a new cure for client"(){
        setup:
            def userId = 1
            def client = new Client.Builder()
                    .clientId(userId)
                    .email("mr.gigami@gmail.com")
                    .name("Mykola")
                    .surname("Pylypenko")
                    .phoneNumber("123456789")
                    .password(AccessServiceImpl.toHexString(AccessServiceImpl.getSHA("123456")))
                    .activatedEmail(true)
                    .build()
            def cureEntity = new Cure.Builder()
                    .cureId(1)
                    .name("Some medicine")
                    .dailyDose(24)
                    .doseTimestamp(1)
                    .doseNumber(15)
                    .build()
            clientRepository.findById(userId) >> Optional.of(client)
        when:
            def result = clientDoseService.addCureForClient(userId, cureEntity)
        then:
            result
    }

    def "should delete a cure for client"(){
        setup:
            def userId = 1
            def client = new Client.Builder()
                    .clientId(userId)
                    .email("mr.gigami@gmail.com")
                    .name("Mykola")
                    .surname("Pylypenko")
                    .phoneNumber("123456789")
                    .password(AccessServiceImpl.toHexString(AccessServiceImpl.getSHA("123456")))
                    .activatedEmail(true)
                    .build()
            def cureEntity = new Cure.Builder()
                    .cureId(1)
                    .name("Some medicine")
                    .dailyDose(24)
                    .doseTimestamp(1)
                    .doseNumber(15)
                    .build()
            clientRepository.findById(userId) >> Optional.of(client)
        when:
            clientDoseService.deleteClientCure(userId, cureEntity)
        then:
            noExceptionThrown()
    }

    def "should give all user's cures"(){
        setup:
            def userId = 1
            def cures = new ArrayList<>()
            def cureEntity = new Cure.Builder()
                    .cureId(1)
                    .name("Some medicine")
                    .dailyDose(24)
                    .doseTimestamp(1)
                    .doseNumber(15)
                    .build()
            cures.add(cureEntity)
            clientDoseRepository.getAllCure(userId) >> cures
        when:
            List<CureDto> usersCures = clientDoseService.getAllCure(userId)
        then:
            usersCures != null
    }
}
