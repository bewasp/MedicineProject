package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.ClientDoseDto;
import pl.edu.pwsztar.domain.entity.ClientDose;
import pl.edu.pwsztar.domain.mapper.convert.Converter;

@Component
public class ClientDoseMapper implements Converter<ClientDose, ClientDoseDto> {
    @Override
    public ClientDoseDto convert(ClientDose from) {
        return null;
    }
}
