package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.cure.ClientDto;
import pl.edu.pwsztar.domain.entity.Client;
import pl.edu.pwsztar.domain.mapper.convert.Converter;

@Component
public class ClientMapper implements Converter<Client, ClientDto> {
    @Override
    public ClientDto convert(Client from) {
        return new ClientDto.Builder()
                .name(from.getName())
                .surname(from.getSurname())
                .email(from.getEmail())
                .phoneNumber(from.getPhoneNumber())
                .password(from.getPassword())
                .build();
    }
}
