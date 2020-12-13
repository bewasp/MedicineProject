package pl.edu.pwsztar.service

import pl.edu.pwsztar.domain.dto.auth.AuthenticationDto
import pl.edu.pwsztar.domain.dto.cure.ClientDto
import pl.edu.pwsztar.domain.entity.Client
import pl.edu.pwsztar.domain.mapper.ClientDaoMapper
import pl.edu.pwsztar.domain.mapper.ClientDtoMapper
import pl.edu.pwsztar.domain.repository.ClientRepository
import pl.edu.pwsztar.domain.repository.LinkRepository
import pl.edu.pwsztar.domain.repository.TokenRepository
import pl.edu.pwsztar.service.serviceImpl.AccessServiceImpl
import pl.edu.pwsztar.service.serviceImpl.MailServiceImpl
import spock.lang.Specification

class AccessServiceTest extends Specification {

    def clientRepository = Mock(ClientRepository)
    def tokenRepository = Mock(TokenRepository)
    def linkRepository = Mock(LinkRepository)

    def clientDtoMapper = new ClientDtoMapper()
    def clientDaoMapper = new ClientDaoMapper()

    def mailService = new MailServiceImpl()

    def accessService = new AccessServiceImpl(clientRepository, tokenRepository, linkRepository, clientDtoMapper, clientDaoMapper, mailService)


    def "registration should be successful"(){
        setup:
        ClientDto client = new ClientDto.Builder()
                .email("mr.gigami@gmail.com")
                .name("Mykola")
                .surname("Pylypenko")
                .phoneNumber("123456789")
                .password("123456")
                .build()
        Client clientEntity = new Client.Builder()
                .clientId(1)
                .email(client.getEmail())
                .name(client.getName())
                .surname(client.getSurname())
                .phoneNumber(client.getPhoneNumber())
                .password(AccessServiceImpl.toHexString(AccessServiceImpl.getSHA(client.getPassword())))
                .activatedEmail(false)
                .build()

        clientRepository.findClientByEmail(_ as String) >> null
        clientRepository.save(_ as Client) >> clientEntity

        when:
            def result = accessService.register(client)
        then:
            result
    }

    def "Authorization should be possible"() {
        setup:
            def user = new AuthenticationDto("mr.gigami@gmail.com", "123456")
            Client clientEntity = new Client.Builder()
                    .clientId(1)
                    .email("mr.gigami@gmail.com")
                    .name("Mykola")
                    .surname("Pylypenko")
                    .phoneNumber("123456789")
                    .password(AccessServiceImpl.toHexString(AccessServiceImpl.getSHA("123456")))
                    .activatedEmail(true)
                    .build()

            clientRepository.findClientByEmail(_ as String) >> clientEntity
        when:
            def result = accessService.authentication(user)
        then:
            result != null
    }

    def "Check email verification"(){
        setup:
            Client clientEntity = new Client.Builder()
                    .clientId(1)
                    .email("mr.gigami@gmail.com")
                    .name("Mykola")
                    .surname("Pylypenko")
                    .phoneNumber("123456789")
                    .password(AccessServiceImpl.toHexString(AccessServiceImpl.getSHA("123456")))
                    .activatedEmail(true)
                    .build()

            linkRepository.findClientIdByLink(_ as String) >> clientEntity.getClientId()
            clientRepository.findClient(clientEntity.getClientId()) >> clientEntity
        when:
            def result = accessService.completeEmailVerification(_ as String)
        then:
            result
    }
}
