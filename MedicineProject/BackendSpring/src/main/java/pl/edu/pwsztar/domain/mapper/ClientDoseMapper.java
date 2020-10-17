package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.ClientDoseDto;
import pl.edu.pwsztar.domain.entity.ClientDose;
import pl.edu.pwsztar.domain.mapper.convert.Convertor;

@Component
public class ClientDoseMapper implements Convertor<ClientDose, ClientDoseDto> {
    @Override
    public ClientDoseDto convert(ClientDose from) {
        return null;
    }
}
