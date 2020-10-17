package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.ClientDto;
import pl.edu.pwsztar.domain.entity.Client;
import pl.edu.pwsztar.domain.mapper.convert.Converter;

@Component
public class ClientDtoMapper implements Converter<ClientDto, Client> {
    @Override
    public Client convert(ClientDto client){
        return new Client.Builder()
                .name(client.getName())
                .surname(client.getSurname())
                .email(client.getEmail())
                .phoneNumber(client.getPhoneNumber())
                .password(client.getPassword())
                .build();
    }
}
