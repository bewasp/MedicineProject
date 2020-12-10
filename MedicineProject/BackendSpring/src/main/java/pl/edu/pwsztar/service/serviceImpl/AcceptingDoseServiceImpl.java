package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.dao.ClientDao;
import pl.edu.pwsztar.domain.dao.CureDao;
import pl.edu.pwsztar.domain.dto.cure.ClientDoseInfoDto;
import pl.edu.pwsztar.domain.dto.cure.ClientDoseReportDto;
import pl.edu.pwsztar.domain.dto.cure.ClientInfo;
import pl.edu.pwsztar.domain.dto.cure.CureDto;
import pl.edu.pwsztar.domain.entity.AcceptedDose;
import pl.edu.pwsztar.domain.entity.Client;
import pl.edu.pwsztar.domain.entity.ClientDose;
import pl.edu.pwsztar.domain.entity.Cure;
import pl.edu.pwsztar.domain.entity.key.AcceptedDoseKey;
import pl.edu.pwsztar.domain.mapper.convert.Converter;
import pl.edu.pwsztar.domain.repository.AcceptedDoseRepository;
import pl.edu.pwsztar.domain.repository.ClientRepository;
import pl.edu.pwsztar.domain.repository.CureRepository;
import pl.edu.pwsztar.service.AcceptingDoseService;
import pl.edu.pwsztar.service.MailService;
import pl.edu.pwsztar.service.serviceImpl.global.GlobalVariables;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AcceptingDoseServiceImpl implements AcceptingDoseService {
    private final AcceptedDoseRepository doseRepository;
    private final ClientRepository clientRepository;
    private final CureRepository cureRepository;

    private final Converter<List<ClientDose>, List<Cure>> clientDoseMapper;
    private final Converter<Client, ClientDao> clientDaoMapper;
    private final Converter<Cure, CureDao> cureDaoMapper;

    private final MailService mailService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    @Autowired
    public AcceptingDoseServiceImpl(AcceptedDoseRepository doseRepository,
                                    ClientRepository clientRepository,
                                    CureRepository cureRepository,
                                    Converter<List<ClientDose>, List<Cure>> clientDoseMapper,
                                    Converter<Client, ClientDao> clientDaoMapper,
                                    Converter<Cure, CureDao> cureDaoMapper,
                                    MailService mailService){
        this.doseRepository = doseRepository;
        this.clientRepository = clientRepository;
        this.cureRepository = cureRepository;

        this.clientDoseMapper = clientDoseMapper;
        this.clientDaoMapper = clientDaoMapper;
        this.cureDaoMapper = cureDaoMapper;

        this.mailService = mailService;
    }

    @Override
    public boolean acceptingCure(Long userId, CureDto cureDto) {
        Client client = clientRepository.findClient(userId);
        Cure cure = cureRepository.findCure(cureDto.getName(),cureDto.getDailyDose(),cureDto.getDoseNumber(),cureDto.getDoseTimestamp());

        String currentTime = dateFormat.format(new Date());
        Optional<AcceptedDose> checkAcceptedDose = Optional.ofNullable(doseRepository.findInfo(client.getClientId(),cure.getCureId(),currentTime.substring(0,13)));

        int minutes = (Integer.parseInt(currentTime.substring(11,13)) * 60) + Integer.parseInt(currentTime.substring(14,16)) + GlobalVariables.getInstance().testAddingTime;
        int cureTime = cure.getDoseTimestamp()*60;

        AcceptedDose acceptedDose = null;
        if(checkAcceptedDose.isEmpty()) {
            acceptedDose =
                    new AcceptedDose.Builder()
                            .id(new AcceptedDoseKey(client.getClientId(), cure.getCureId(), currentTime))
                            .client(client)
                            .cure(cure)
                            .build();
        }

        boolean result = false;

        if(checkAcceptedDose.isEmpty() && (minutes%cureTime > 0 && minutes%cureTime >= cureTime-GlobalVariables.getInstance().acceptingTime)){
            acceptedDose =
                    new AcceptedDose.Builder(acceptedDose)
                        .accepted(true)
                        .delayed(false)
                        .build();

            doseRepository.save(acceptedDose);
            result = true;
        } else if (checkAcceptedDose.isEmpty() && minutes%cureTime < GlobalVariables.getInstance().maxDelayTime && minutes%cureTime >= 0){
            acceptedDose =
                    new AcceptedDose.Builder(acceptedDose)
                            .accepted(true)
                            .delayed(true)
                            .build();

            doseRepository.save(acceptedDose);
            result = true;
        }

        return result;
    }

    @Override
    public ClientInfo makeStats(Long userId) {
        Client client = clientRepository.findClient(userId);
        List<AcceptedDose> acceptedDoses = client.getAcceptedDoses();
        List<ClientDoseReportDto> reportList = new ArrayList<>();

        int accepted = 0;
        int declined = 0;
        int delayed = 0;

        for(AcceptedDose acceptedDose: acceptedDoses){
            ClientDoseReportDto report = null;
            if(acceptedDose.isAccepted() && !acceptedDose.isDelayed()){
                accepted++;
                report = new ClientDoseReportDto.Builder().name(acceptedDose.getCure().getName()).acceptedDose("Accepted").date(acceptedDose.getId().getDate()).build();
            }
            if(acceptedDose.isAccepted() && acceptedDose.isDelayed()){
                delayed++;
                report = new ClientDoseReportDto.Builder().name(acceptedDose.getCure().getName()).acceptedDose("Delayed").date(acceptedDose.getId().getDate()).build();
            }
            if(!acceptedDose.isAccepted() && !acceptedDose.isDelayed()){
                declined++;
                report = new ClientDoseReportDto.Builder().name(acceptedDose.getCure().getName()).acceptedDose("Declined").date(acceptedDose.getId().getDate()).build();
            }
            reportList.add(report);
        }

        ClientDoseInfoDto info = new ClientDoseInfoDto.Builder().acceptedDose(accepted).declinedDose(declined).delayedDose(delayed).build();

        return new ClientInfo.Builder().info(info).report(reportList).build();
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 1000 * 60)
    public void acceptingDose(){
        String currentTime = dateFormat.format(new Date());
        int minute = Integer.parseInt(currentTime.substring(14,16));
        int minutes = (Integer.parseInt(currentTime.substring(11,13)) * 60) + minute + GlobalVariables.getInstance().testAddingTime;

        List<Client> clients = clientRepository.findAll();

        for(Client client: clients){
            List<Cure> cures = clientDoseMapper.convert(client.getDose());

            for(Cure cure: cures){
                Optional<AcceptedDose> checkAcceptedDose = Optional.ofNullable(doseRepository.findInfo(client.getClientId(),cure.getCureId(),currentTime.substring(0,13)));
                int cureTime = cure.getDoseTimestamp()*60;

                if((minutes+GlobalVariables.getInstance().notificationTime)%cureTime==0){
                    ClientDao clientDao = clientDaoMapper.convert(client);
                    CureDao cureDao = cureDaoMapper.convert(cure);

                    mailService.sendNotification(clientDao,cureDao);
                }

                if(minutes%cureTime == GlobalVariables.getInstance().maxDelayTime && checkAcceptedDose.isEmpty()){
                    doseRepository.save(new AcceptedDose.Builder().id(new AcceptedDoseKey(client.getClientId(),cure.getCureId(),currentTime)).accepted(false).delayed(false).client(client).cure(cure).build());
                }
            }
        }
    }

}

