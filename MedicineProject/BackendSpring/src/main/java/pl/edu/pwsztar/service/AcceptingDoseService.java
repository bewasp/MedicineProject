package pl.edu.pwsztar.service;


import pl.edu.pwsztar.domain.dto.ResponseDto;
import pl.edu.pwsztar.domain.dto.cure.ClientInfo;
import pl.edu.pwsztar.domain.dto.cure.CureDto;


public interface AcceptingDoseService {
    ResponseDto<Void> acceptingCure(Long userId, CureDto cure);
    ClientInfo makeStats(Long userId);
}
