package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dao.ClientDao;
import pl.edu.pwsztar.domain.entity.Client;
import pl.edu.pwsztar.domain.mapper.convert.Converter;

@Component
public class ClientDaoMapper implements Converter<Client, ClientDao> {
    @Override
    public ClientDao convert(Client from) {
        return new ClientDao.Builder()
                .clientId(from.getClientId())
                .email(from.getEmail())
                .name(from.getName())
                .password(from.getPassword())
                .phoneNumber(from.getPhoneNumber())
                .surname(from.getSurname())
                .activatedEmail(from.getActivatedEmail())
                .build();
    }
}
