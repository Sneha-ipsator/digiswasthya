package sneha.ipsator.digiswasthya.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sneha.ipsator.digiswasthya.Dto.NavigatorInfoDTO;
import sneha.ipsator.digiswasthya.Entity.NavigatorInfo;
import sneha.ipsator.digiswasthya.Entity.User;
import sneha.ipsator.digiswasthya.Repository.NavigatorInfoRepository;
import sneha.ipsator.digiswasthya.Repository.UserRepo;
import sneha.ipsator.digiswasthya.Service.NavigatorService;
import sneha.ipsator.digiswasthya.payload.ServiceResponse;

@Slf4j
@Service
public class NavigatorServiceImpl implements NavigatorService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private NavigatorInfoRepository navigatorInfoRepository;

    public ServiceResponse<NavigatorInfo> registerNavigator(String adminUsername, NavigatorInfoDTO navigatorInfoDTO) {
        try {
            User adminUser = userRepo.findByName(adminUsername);

            User user = new User();
            user.setName(navigatorInfoDTO.getName());
            user.setEmail(navigatorInfoDTO.getEmail());
            user.setPassword(passwordEncoder.encode(navigatorInfoDTO.getPassword()));
            user.setRole(navigatorInfoDTO.getRole());
            user.setGender(navigatorInfoDTO.getGender());

            userRepo.save(user);

            NavigatorInfo navigatorInfo = new NavigatorInfo();
            navigatorInfo.setUser(user);
            navigatorInfo.setAge(navigatorInfoDTO.getAge());
            navigatorInfo.setExperience(navigatorInfoDTO.getExperience());
            navigatorInfo.setEducation(navigatorInfoDTO.getEducation());
            navigatorInfo.setPhoneNumber(navigatorInfoDTO.getPhoneNumber());
            navigatorInfo.setAddedBy(adminUsername);

            navigatorInfoRepository.save(navigatorInfo);

            return new ServiceResponse<>(true,navigatorInfo,"navigator added successfully");
        } catch (Exception e) {
            return new ServiceResponse<>(false,null,"navigator not added");
        }
    }
}
