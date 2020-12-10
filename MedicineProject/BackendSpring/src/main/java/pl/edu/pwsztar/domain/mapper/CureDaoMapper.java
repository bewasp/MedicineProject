package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dao.CureDao;
import pl.edu.pwsztar.domain.entity.Cure;
import pl.edu.pwsztar.domain.mapper.convert.Converter;

@Component
public class CureDaoMapper implements Converter<Cure, CureDao> {
    @Override
    public CureDao convert(Cure from) {
        return new CureDao.Builder()
                .cureId(from.getCureId())
                .name(from.getName())
                .doseTimestamp(from.getDoseTimestamp())
                .dailyDose(from.getDailyDose())
                .doseNumber(from.getDoseNumber())
                .build();
    }
}
