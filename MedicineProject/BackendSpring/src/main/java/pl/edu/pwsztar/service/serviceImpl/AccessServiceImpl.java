package pl.edu.pwsztar.service.serviceImpl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.dao.ClientDao;
import pl.edu.pwsztar.domain.dto.auth.AuthenticationDto;
import pl.edu.pwsztar.domain.dto.auth.AuthenticationResult;
import pl.edu.pwsztar.domain.dto.cure.ClientDto;
import pl.edu.pwsztar.domain.entity.Client;
import pl.edu.pwsztar.domain.entity.Link;
import pl.edu.pwsztar.domain.entity.Token;
import pl.edu.pwsztar.domain.mapper.convert.Converter;
import pl.edu.pwsztar.domain.repository.ClientRepository;
import pl.edu.pwsztar.domain.repository.LinkRepository;
import pl.edu.pwsztar.domain.repository.TokenRepository;
import pl.edu.pwsztar.service.MailService;
import pl.edu.pwsztar.service.serviceImpl.token.JwtTokenUtil;
import pl.edu.pwsztar.service.AccessService;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class AccessServiceImpl implements AccessService {
    private final ClientRepository clientRepository;
    private final TokenRepository tokenRepository;
    private final LinkRepository linkRepository;

    private final Converter<Client, ClientDto> clientMapper;
    private final Converter<ClientDto, Client> clientDtoMapper;
    private final Converter<Client, ClientDao> clientDaoMapper;

    private final MailService mailService;

    private final JwtTokenUtil jwtTokenUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessServiceImpl.class);
    @Autowired
    public AccessServiceImpl(ClientRepository clientRepository,
                             TokenRepository tokenRepository,
                             LinkRepository linkRepository,
                             Converter<Client, ClientDto> clientMapper,
                             Converter<ClientDto, Client> clientDtoMapper,
                             Converter<Client, ClientDao> clientDaoMapper,
                             MailService mailService,
                             JwtTokenUtil jwtTokenUtil){
        this.clientRepository = clientRepository;
        this.linkRepository = linkRepository;
        this.tokenRepository=tokenRepository;

        this.clientMapper=clientMapper;
        this.clientDtoMapper=clientDtoMapper;
        this.clientDaoMapper = clientDaoMapper;

        this.mailService = mailService;

        this.jwtTokenUtil=jwtTokenUtil;
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    private void createLink(ClientDao client) throws NoSuchAlgorithmException {
        Long clientId = client.getClientId();
        String link = toHexString(getSHA(clientId.toString()));

        Link linkEntity = new Link.Builder().id_client(clientId).link(link).client(clientRepository.findClient(clientId)).build();
        linkRepository.save(linkEntity);

        mailService.sendEmailActivation(client, link);
    }

    @Override
    public boolean register(ClientDto client) {

        Optional<Client> checkEmail = Optional.ofNullable(clientRepository.findClientByEmail(client.getEmail()));

        if(checkEmail.isEmpty()){
            try {
                client = new ClientDto.Builder(client).password(toHexString(getSHA(client.getPassword()))).build();
                Client createClient = clientDtoMapper.convert(client);

                Client newClient = clientRepository.save(new Client.Builder(createClient).activatedEmail(false).build());
                ClientDao clientDao = clientDaoMapper.convert(newClient);

                createLink(clientDao);
                return true;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public AuthenticationResult authentication(AuthenticationDto user){
        AuthenticationResult result = null;
        String password = user.getPassword();

        Optional<Client> checkEmail = Optional.ofNullable(clientRepository.findClientByEmail(user.getEmail()));

        if(checkEmail.isPresent()){
            try {
                Client client = checkEmail.get();
                ClientDao clientDao = clientDaoMapper.convert(client);

                if(!clientDao.getActivatedEmail()){
                    return result;
                }

                if(toHexString(getSHA(password)).equals(client.getPassword())){
                    String token = jwtTokenUtil.generateToken(clientDao);
                    tokenRepository.save(new Token.Builder().userId(clientDao.getClientId()).token(token).client(client).build());

                    result = new AuthenticationResult(token);
                    return result;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return result;
            }
        }

        return result;
    }

    @Override
    public void removeHashSession(Long userId) {
        tokenRepository.removeSession(userId);
    }

    @Override
    public Boolean completeEmailVerification(String link) {
        boolean result = false;
        Optional<Long> clientId = Optional.ofNullable(linkRepository.findClientIdByLink(link));

        if(clientId.isPresent()){
            Client clientEntity = clientRepository.findClient(clientId.get());
            ClientDao client = clientDaoMapper.convert(clientEntity);

            clientRepository.verifyClientEmail(client.getClientId());
            linkRepository.deleteEmailVerificationLink(client.getClientId());

            result = true;
        }

        return result;
    }
}
