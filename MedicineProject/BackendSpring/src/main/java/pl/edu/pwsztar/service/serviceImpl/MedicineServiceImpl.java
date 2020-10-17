package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.dto.AcceptedDoseDto;
import pl.edu.pwsztar.domain.dto.ClientDoseDto;
import pl.edu.pwsztar.domain.dto.ClientDto;
import pl.edu.pwsztar.domain.dto.CureDto;
import pl.edu.pwsztar.domain.entity.AcceptedDose;
import pl.edu.pwsztar.domain.entity.Client;
import pl.edu.pwsztar.domain.entity.ClientDose;
import pl.edu.pwsztar.domain.entity.Cure;
import pl.edu.pwsztar.domain.mapper.convert.Convertor;
import pl.edu.pwsztar.domain.repository.AcceptedDoseRepository;
import pl.edu.pwsztar.domain.repository.ClientDoseRepository;
import pl.edu.pwsztar.domain.repository.ClientRepository;
import pl.edu.pwsztar.domain.repository.CureRepository;
import pl.edu.pwsztar.service.MedicineService;

import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {
    private Convertor<AcceptedDose, AcceptedDoseDto> acceptedDoseMapper;
    private Convertor<ClientDose, ClientDoseDto> clientDoseMapper;
    private Convertor<Client, ClientDto> clientMapper;

    private AcceptedDoseRepository acceptedDoseRepository;
    private ClientDoseRepository clientDoseRepository;
    private ClientRepository clientRepository;
    private CureRepository cureRepository;

    @Autowired
    public MedicineServiceImpl(Convertor<AcceptedDose, AcceptedDoseDto> acceptedDoseMapper,
                               Convertor<ClientDose, ClientDoseDto> clientDoseMapper,
                               Convertor<Client, ClientDto> clientMapper ,
                               AcceptedDoseRepository acceptedDoseRepository,
                               ClientDoseRepository clientDoseRepository,
                               ClientRepository clientRepository,
                               CureRepository cureRepository) {
        this.acceptedDoseMapper = acceptedDoseMapper;
        this.clientDoseMapper = clientDoseMapper;
        this.clientMapper = clientMapper;
        this.acceptedDoseRepository = acceptedDoseRepository;
        this.clientDoseRepository = clientDoseRepository;

        this.cureRepository = cureRepository;
    }

    @Override
    public void register(Client personal) {
        clientRepository.save(personal);
    }

    @Override
    public boolean login(Client personal) {
        ClientDto personalDto = clientMapper.convert(personal);
        Optional<String> checkPassword = Optional.of(clientRepository.findByKey(personalDto.getEmail()));
        String password = checkPassword.orElse("");

        if(!password.isEmpty() && personalDto.getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
