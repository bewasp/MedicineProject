package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.AcceptedDoseDto;
import pl.edu.pwsztar.domain.entity.AcceptedDose;
import pl.edu.pwsztar.domain.mapper.convert.Convertor;

@Component
public class AcceptedDoseMapper implements Convertor<AcceptedDose, AcceptedDoseDto> {
    @Override
    public AcceptedDoseDto convert(AcceptedDose from) {
        return new AcceptedDoseDto.Builder()
                .delayed(from.isDelayed())
                .accepted(from.isAccepted())
                .build();
    }
}
