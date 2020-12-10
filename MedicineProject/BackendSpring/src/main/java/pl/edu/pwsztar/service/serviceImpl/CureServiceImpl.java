package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.dto.cure.CureDto;
import pl.edu.pwsztar.domain.entity.*;
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
    public void createNewCure(Long userId, CureDto cure) {
        Optional<Cure> findCure = Optional.ofNullable(cureRepository.findCure(cure.getName(), cure.getDailyDose(), cure.getDoseNumber(), cure.getDoseTimestamp()));
        Cure newCure = null;

        if(findCure.isEmpty()){
            newCure = cureMapper.convert(cure);
            cureRepository.save(newCure);
            newCure = cureRepository.findCure(cure.getName(), cure.getDailyDose(), cure.getDoseNumber(), cure.getDoseTimestamp());
        }

        Cure finalNewCure = newCure;
        clientDoseService.addCureForClient(userId, findCure.orElseGet(() -> finalNewCure));
    }

    @Override
    public void deleteCure(Long userId, CureDto cure) {
        Optional<Cure> findCure = Optional.ofNullable(cureRepository.findCure(cure.getName(), cure.getDailyDose(), cure.getDoseNumber(), cure.getDoseTimestamp()));
        findCure.ifPresent(value -> clientDoseService.deleteClientCure(userId, value));
    }
}
