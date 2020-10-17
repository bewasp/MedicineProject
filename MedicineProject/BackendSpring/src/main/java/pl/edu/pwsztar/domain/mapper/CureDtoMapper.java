package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.cure.CureDto;
import pl.edu.pwsztar.domain.entity.Cure;
import pl.edu.pwsztar.domain.mapper.convert.Converter;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CureDtoMapper implements Converter<List<Cure>, List<CureDto>> {
    @Override
    public List<CureDto> convert(List<Cure> cure){
        return cure.stream()
                .map( value ->{
                return new CureDto.Builder()
                        .name(value.getName())
                        .dailyDose(value.getDailyDose())
                        .doseNumber(value.getDoseNumber())
                        .doseTimestamp(value.getDoseTimestamp())
                        .build();
                })
                .collect(Collectors.toList());
    }
}


