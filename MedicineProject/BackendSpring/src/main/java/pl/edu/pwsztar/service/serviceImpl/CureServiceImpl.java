package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.dto.ResponseDto;
import pl.edu.pwsztar.domain.dto.cure.CureDto;
import pl.edu.pwsztar.domain.entity.*;
import pl.edu.pwsztar.domain.enums.CureCodeEnum;
import pl.edu.pwsztar.domain.mapper.convert.Converter;
import pl.edu.pwsztar.domain.repository.*;
import pl.edu.pwsztar.service.ClientDoseService;
import pl.edu.pwsztar.service.CureService;

import java.util.Optional;

@Service
public class CureServiceImpl implements CureService {
    private final ClientDoseService clientDoseService;

    private final Converter<CureDto, Cure> cureMapper;

    private final CureRepository cureRepository;

    @Autowired
    public CureServiceImpl(ClientDoseService clientDoseService,
                           Converter<CureDto, Cure> cureMapper,
                           CureRepository cureRepository) {
        this.cureMapper = cureMapper;
        this.cureRepository = cureRepository;
        this.clientDoseService = clientDoseService;
    }

    @Override
    public ResponseDto<Void> createNewCure(Long userId, CureDto cure) {
        Optional<Cure> findCure = Optional.ofNullable(cureRepository.findCure(cure.getName(), cure.getDailyDose(), cure.getDoseNumber(), cure.getDoseTimestamp()));
        Cure newCure = null;

        if(findCure.isEmpty() && cure.getDailyDose()%(24/(float)cure.getDoseTimestamp())==0){
            newCure = cureMapper.convert(cure);
            newCure = cureRepository.save(newCure);
        }

        Cure finalNewCure = newCure;
        return clientDoseService.addCureForClient(userId, findCure.orElseGet(() -> finalNewCure));
    }

    @Override
    public ResponseDto<Void> deleteCure(Long userId, CureDto cure) {
        Optional<Cure> findCure = Optional.ofNullable(cureRepository.findCure(cure.getName(), cure.getDailyDose(), cure.getDoseNumber(), cure.getDoseTimestamp()));

        if(findCure.isPresent()){
            return clientDoseService.deleteClientCure(userId, findCure.get());
        }

        return new ResponseDto<>(null, CureCodeEnum.CURE_DELETE_ERROR.getValue());
    }
}
