package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.entity.ClientDose;
import pl.edu.pwsztar.domain.entity.Cure;
import pl.edu.pwsztar.domain.mapper.convert.Converter;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientDoseMapper implements Converter<List<ClientDose>, List<Cure>> {
    @Override
    public List<Cure> convert(List<ClientDose> clientDose){
        return clientDose.stream()
                .map(ClientDose::getCure)
                .collect(Collectors.toList());
    }
}
