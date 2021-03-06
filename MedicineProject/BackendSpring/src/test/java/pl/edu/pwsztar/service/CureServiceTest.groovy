package pl.edu.pwsztar.service

import pl.edu.pwsztar.domain.dto.ResponseDto
import pl.edu.pwsztar.domain.dto.cure.CureDto
import pl.edu.pwsztar.domain.entity.Cure
import pl.edu.pwsztar.domain.enums.CureCodeEnum
import pl.edu.pwsztar.domain.mapper.CureMapper
import pl.edu.pwsztar.domain.repository.CureRepository
import pl.edu.pwsztar.service.serviceImpl.CureServiceImpl
import spock.lang.Specification

class CureServiceTest extends Specification {
    def clientDoseService = Mock(ClientDoseService)
    def cureMapper = new CureMapper()
    def cureRepository = Mock(CureRepository)

    def cureService = new CureServiceImpl(clientDoseService, cureMapper, cureRepository)

    def "should create a new cure for user"(){
        setup:
            def userId = 1
            def cure = new CureDto.Builder()
                    .name("Some medicine")
                    .dailyDose(24)
                    .doseTimestamp(1)
                    .doseNumber(15)
                    .build()
            def cureEntity = new Cure.Builder()
                    .cureId(1)
                    .name(cure.getName())
                    .dailyDose(cure.getDailyDose())
                    .doseTimestamp(cure.getDoseTimestamp())
                    .doseNumber(cure.getDoseNumber())
                    .build()
            cureRepository.findCure(_,_,_,_) >> null
            cureRepository.save(_ as Cure) >> cureEntity
            clientDoseService.addCureForClient(userId, _ as Cure) >> new ResponseDto<Void>(null, CureCodeEnum.CURE_CREATED.getValue())
        when:
            def result = cureService.createNewCure(userId, cure)
        then:
            result.code == CureCodeEnum.CURE_CREATED.getValue()
    }

    def "should delete exited cure for client"(){
        setup:
            def userId = 1
            def cure = new CureDto.Builder()
                    .name("Some medicine")
                    .dailyDose(24)
                    .doseTimestamp(1)
                    .doseNumber(15)
                    .build()
            def cureEntity = new Cure.Builder()
                    .cureId(1)
                    .name(cure.getName())
                    .dailyDose(cure.getDailyDose())
                    .doseTimestamp(cure.getDoseTimestamp())
                    .doseNumber(cure.getDoseNumber())
                    .build()
            cureRepository.findCure(_,_,_,_) >> cureEntity
            clientDoseService.deleteClientCure(userId, cureEntity) >> new ResponseDto<Void>(null, CureCodeEnum.CURE_DELETED.getValue())
        when:
            def result = cureService.deleteCure(userId, cure)
        then:
            result.code == CureCodeEnum.CURE_DELETED.getValue()
    }
}
