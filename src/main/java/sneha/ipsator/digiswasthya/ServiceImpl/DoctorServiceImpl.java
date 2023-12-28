package sneha.ipsator.digiswasthya.ServiceImpl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sneha.ipsator.digiswasthya.Dto.DoctorInfoDTO;
import sneha.ipsator.digiswasthya.Entity.DoctorInfo;
import sneha.ipsator.digiswasthya.Entity.User;
import sneha.ipsator.digiswasthya.Repository.AvailabilityRepository;
import sneha.ipsator.digiswasthya.Repository.DoctorInfoRepository;
import sneha.ipsator.digiswasthya.Repository.UserRepo;
import sneha.ipsator.digiswasthya.Service.DoctorService;
import sneha.ipsator.digiswasthya.payload.ServiceResponse;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DoctorInfoRepository doctorInfoRepository;


    @Override
    @Transactional
    public ServiceResponse<DoctorInfo> registerDoctor(DoctorInfoDTO doctorInfoDTO,String email) {
        try {
            if (email== null) {
                return new ServiceResponse<>(false, null, "Admin user not found");
           }
            Optional<User> existingUserOpt = userRepo.findByEmail(doctorInfoDTO.getEmail());
            if(existingUserOpt.isEmpty()) {
                User doctorUser = new User();
                doctorUser.setName(doctorInfoDTO.getName());
                doctorUser.setEmail(doctorInfoDTO.getEmail());
                doctorUser.setPassword(doctorInfoDTO.getPassword());
                doctorUser.setRole("ROLE_DOCTOR");
                doctorUser.setGender(doctorInfoDTO.getGender());
                userRepo.save(doctorUser);

                DoctorInfo doctorInfo = new DoctorInfo();
                doctorInfo.setUser(doctorUser);
                doctorInfo.setSpecializations(doctorInfoDTO.getSpecializations());
                doctorInfo.setExperience(doctorInfoDTO.getExperience());
                doctorInfo.setPhoneNumber(doctorInfoDTO.getPhoneNumber());
                doctorInfo.setHospitalName(doctorInfoDTO.getHospitalName());
                doctorInfo.setAddedBy(email);

                doctorInfoRepository.save(doctorInfo);

                return new ServiceResponse<>(true, doctorInfo, "Doctor added successfully");
            }
            else {
                return new ServiceResponse<>(false, null, "The specified Email id is already present");
            }
        } catch (Exception ex) {
            log.error("Exception in registerDoctor: " + ex.getMessage(), ex);
            return new ServiceResponse<>(false, null, ex.getMessage());
        }
    }

    public List<DoctorInfo> getDoctorsBySpecializations(List<String> specializations) {
        return doctorInfoRepository.findAllBySpecializationsIn(specializations);
    }
}
