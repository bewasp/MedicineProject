package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.ClientDto;
import pl.edu.pwsztar.domain.entity.Client;
import pl.edu.pwsztar.domain.mapper.convert.Convertor;

@Component
public class ClientMapper implements Convertor<Client, ClientDto> {
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
