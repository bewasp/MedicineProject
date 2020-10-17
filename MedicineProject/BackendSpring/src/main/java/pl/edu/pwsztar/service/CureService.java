package pl.edu.pwsztar.service;

import pl.edu.pwsztar.domain.dto.cure.CureDto;

public interface CureService {
    void createNewCure(Long userId, CureDto cure);
    void deleteCure(Long userId, CureDto cure);

}
