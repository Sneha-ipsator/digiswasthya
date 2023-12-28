package sneha.ipsator.digiswasthya.ServiceImpl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sneha.ipsator.digiswasthya.Dto.AddressDTO;
import sneha.ipsator.digiswasthya.Dto.ChiefComplaintDTO;
import sneha.ipsator.digiswasthya.Dto.PatientInfoDTO;
import sneha.ipsator.digiswasthya.Entity.*;
import sneha.ipsator.digiswasthya.Repository.*;
import sneha.ipsator.digiswasthya.Service.PatientService;
import sneha.ipsator.digiswasthya.Service.UserService;
import sneha.ipsator.digiswasthya.payload.ServiceResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientInfoRepository patientInfoRepository;

    @Autowired
    private VitalsInfoRepository vitalsInfoRepository;

    @Autowired
    private ChiefComplaintRepository chiefComplaintRepository;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Transactional
    public ServiceResponse<PatientInfo> registerPatient(PatientInfoDTO patientInfoDTO, String userEmail) {
        try {
            if (userEmail == null) {
                return new ServiceResponse<>(false, null, "Admin user not found");
            }
            Optional<User> existingUserOpt = userRepo.findByEmail(patientInfoDTO.getEmail());
            if (existingUserOpt.isEmpty()) {
                User patientUser = new User();
                patientUser.setName(patientInfoDTO.getName());
                patientUser.setEmail(patientInfoDTO.getEmail());
                patientUser.setPassword(patientInfoDTO.getPassword());
                patientUser.setRole("ROLE_PATIENT");
                patientUser.setGender(patientInfoDTO.getGender());
                userRepo.save(patientUser);

                PatientInfo patientInfo = new PatientInfo();
                patientInfo.setAge(patientInfoDTO.getAge());
                patientInfo.setMotherTongue(patientInfoDTO.getMotherTongue());
                patientInfo.setFollowUp(patientInfoDTO.getFollowUp());
                patientInfo.setContactNumber(patientInfoDTO.getContactNumber());
                patientInfo.setEmergencyNumber(patientInfoDTO.getEmergencyNumber());
                patientInfo.setRelation(patientInfoDTO.getRelation());
                patientInfo.setVisitLocation(patientInfoDTO.getVisitLocation());
                patientInfo.setUser(patientUser);

                List<AddressDTO>addressDTOS=patientInfoDTO.getAddress();
                List<Address>addresses=new ArrayList<>();
                for(AddressDTO addressDTO :addressDTOS){
                    Address address=new Address();
                    address.setArea(addressDTO.getArea());
                    address.setCity(addressDTO.getCity());
                    address.setPin(addressDTO.getPin());
                    addresses.add(address);
                }
                patientInfo.setAddress(addresses);

                patientInfoRepository.save(patientInfo);

                VitalsInfo vitalsInfo = new VitalsInfo();
                vitalsInfo.setTimestamp(LocalDateTime.now());
                vitalsInfo.setHeight(patientInfoDTO.getHeight());
                vitalsInfo.setWeight(patientInfoDTO.getWeight());
                vitalsInfo.setPulse(patientInfoDTO.getPulse());
                vitalsInfo.setSpo2(patientInfoDTO.getSpo2());
                vitalsInfo.setSugar(patientInfoDTO.getSugar());
                vitalsInfo.setBmi(patientInfoDTO.getBmi());
                vitalsInfo.setTemperature(patientInfoDTO.getTemperature());
                vitalsInfo.setBloodPressure(patientInfoDTO.getBloodPressure());
                vitalsInfo.setPatientInfo(patientInfo);

                vitalsInfoRepository.save(vitalsInfo);

                List<ChiefComplaintDTO> chiefComplaintDTOs = patientInfoDTO.getChiefComplaint();
                List<ChiefComplaint> chiefComplaints = new ArrayList<>();
                for (ChiefComplaintDTO chiefComplaintDTO : chiefComplaintDTOs) {
                    ChiefComplaint chiefComplaint = new ChiefComplaint();
                    chiefComplaint.setComplaintDesc(chiefComplaintDTO.getComplaintDesc());
                    chiefComplaint.setPatientInfo(patientInfo);
                    chiefComplaints.add(chiefComplaint);
                }
                patientInfo.setChiefComplaints(chiefComplaints);



                patientInfoRepository.save(patientInfo);

                Disease disease = new Disease();
                disease.setName(patientInfoDTO.getDiseaseName());
                disease.setPatientInfo(patientInfo);

                diseaseRepository.save(disease);




                return new ServiceResponse<>(true, patientInfo, "Patient registered successfully");
            } else {
                return new ServiceResponse<>(false, null, "The specified Email id is already present");
            }
        } catch (Exception ex) {
            log.error("Exception in registerPatient: " + ex.getMessage(), ex);
            return new ServiceResponse<>(false, null, ex.getMessage());
        }
    }

}