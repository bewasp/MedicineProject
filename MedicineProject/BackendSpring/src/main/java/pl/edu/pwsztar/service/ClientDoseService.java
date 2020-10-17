package pl.edu.pwsztar.service;

import pl.edu.pwsztar.domain.dto.cure.CureDto;
import pl.edu.pwsztar.domain.entity.Cure;

import java.util.List;

public interface ClientDoseService {
    void addCureForClient(Long userId, Cure cure);
    void deleteClientCure(Long userId, Cure cure);
    List<CureDto> getAllCure(Long userId);
}
