package pl.edu.pwsztar.service;

import pl.edu.pwsztar.domain.entity.Client;

public interface MedicineService {
    void register(Client personal);
    boolean login(Client personal);
}
