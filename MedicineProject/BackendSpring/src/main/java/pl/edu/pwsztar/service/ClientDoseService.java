package pl.edu.pwsztar.service;

import pl.edu.pwsztar.domain.dto.ResponseDto;
import pl.edu.pwsztar.domain.dto.cure.CureDto;
import pl.edu.pwsztar.domain.entity.Cure;

import java.util.List;

public interface ClientDoseService {
    ResponseDto<Void> addCureForClient(Long userId, Cure cure);
    ResponseDto<Void> deleteClientCure(Long userId, Cure cure);
    List<CureDto> getAllCure(Long userId);
}
