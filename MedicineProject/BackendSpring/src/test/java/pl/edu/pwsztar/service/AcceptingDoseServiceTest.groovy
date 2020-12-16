package pl.edu.pwsztar.service

import pl.edu.pwsztar.domain.dto.cure.CureDto
import pl.edu.pwsztar.domain.entity.AcceptedDose
import pl.edu.pwsztar.domain.entity.Client
import pl.edu.pwsztar.domain.entity.Cure
import pl.edu.pwsztar.domain.entity.key.AcceptedDoseKey
import pl.edu.pwsztar.domain.enums.AcceptingCureEnum
import pl.edu.pwsztar.domain.mapper.ClientDaoMapper
import pl.edu.pwsztar.domain.mapper.ClientDoseMapper
import pl.edu.pwsztar.domain.mapper.CureDaoMapper
import pl.edu.pwsztar.domain.repository.AcceptedDoseRepository
import pl.edu.pwsztar.domain.repository.ClientRepository
import pl.edu.pwsztar.domain.repository.CureRepository
import pl.edu.pwsztar.service.serviceImpl.AcceptingDoseServiceImpl
import pl.edu.pwsztar.service.serviceImpl.AccessServiceImpl
import pl.edu.pwsztar.service.serviceImpl.MailServiceImpl
import spock.lang.Specification

class AcceptingDoseServiceTest extends Specification{
    def doseRepository = Mock(AcceptedDoseRepository)
    def clientRepository = Mock(ClientRepository)
    def cureRepository = Mock(CureRepository)

    def clientDoseMapper = new ClientDoseMapper()
    def clientDaoMapper = new ClientDaoMapper()
    def cureDaoMapper = new CureDaoMapper()

    def mailService = new MailServiceImpl()

    def acceptingDoseService = new AcceptingDoseServiceImpl(doseRepository, clientRepository, cureRepository, clientDoseMapper, clientDaoMapper, cureDaoMapper, mailService)

    def "should accept user's cure in time"(){
        setup:
            def userId = 1
            def cureDto = new CureDto.Builder()
                    .name("Some medicine")
                    .dailyDose(24)
                    .doseTimestamp(1)
                    .doseNumber(15)
                    .build()
            def cureEntity = new Cure.Builder()
                    .cureId(1)
                    .name(cureDto.getName())
                    .dailyDose(cureDto.getDailyDose())
                    .doseTimestamp(cureDto.getDoseTimestamp())
                    .doseNumber(cureDto.getDoseNumber())
                    .build()
            def clientEntity = new Client.Builder()
                    .clientId(1)
                    .email("mr.gigami@gmail.com")
                    .name("Mykola")
                    .surname("Pylypenko")
                    .phoneNumber("123456789")
                    .password(AccessServiceImpl.toHexString(AccessServiceImpl.getSHA("123456")))
                    .activatedEmail(true)
                    .build()
            clientRepository.findClient(userId) >> clientEntity
            cureRepository.findCure(_,_,_,_) >> cureEntity
            doseRepository.findInfo(_,_,_) >> null
        when:
            def result = acceptingDoseService.acceptingCure(userId, cureDto)
        then:
            result.code == AcceptingCureEnum.ACCEPTING_CURE_IN_TIME.getValue()
    }

    def "should return user's cures stats"(){
        setup:
            def userId = 1
            def acceptedDoseDate = "2020-12-13 21:00"
            def cureEntity = new Cure.Builder()
                    .cureId(1)
                    .name("Some medicine")
                    .dailyDose(24)
                    .doseTimestamp(1)
                    .doseNumber(15)
                    .build()
            def clientEntity = new Client.Builder()
                    .clientId(userId)
                    .email("mr.gigami@gmail.com")
                    .name("Mykola")
                    .surname("Pylypenko")
                    .phoneNumber("123456789")
                    .password(AccessServiceImpl.toHexString(AccessServiceImpl.getSHA("123456")))
                    .activatedEmail(true)
                    .build()
            def acceptedDose = new AcceptedDose.Builder()
                    .id(new AcceptedDoseKey(clientEntity.getClientId(), cureEntity.getCureId(), acceptedDoseDate))
                    .cure(cureEntity)
                    .client(clientEntity)
                    .accepted(true)
                    .delayed(false)
                    .build()
            def acceptedDoses = new ArrayList<>()
            acceptedDoses.add(acceptedDose)
            clientEntity = new Client.Builder(clientEntity).acceptedDoses(acceptedDoses as List<AcceptedDose>).build()
            clientRepository.findClient(userId) >> clientEntity
        when:
            def result = acceptingDoseService.makeStats(userId)
        then:
            result != null
    }
}
