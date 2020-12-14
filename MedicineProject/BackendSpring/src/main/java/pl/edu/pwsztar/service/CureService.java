package pl.edu.pwsztar.service;

import pl.edu.pwsztar.domain.dto.ResponseDto;
import pl.edu.pwsztar.domain.dto.cure.CureDto;

public interface CureService {
    ResponseDto<Void> createNewCure(Long userId, CureDto cure);
    ResponseDto<Void> deleteCure(Long userId, CureDto cure);

}
