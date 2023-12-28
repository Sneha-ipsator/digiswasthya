package sneha.ipsator.digiswasthya.ServiceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sneha.ipsator.digiswasthya.Dto.UserDto;
import sneha.ipsator.digiswasthya.Entity.User;
import sneha.ipsator.digiswasthya.Repository.UserRepo;
import sneha.ipsator.digiswasthya.Service.UserService;
import sneha.ipsator.digiswasthya.payload.ServiceResponse;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static final String DEFAULT_ROLE = "ROLE_ADMIN";
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public ServiceResponse<UserDto> createUser(User user) {

        Optional<User> existingUserOpt = userRepo.findByEmail(user.getEmail());

        if (existingUserOpt.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(DEFAULT_ROLE);

            User savedUser = userRepo.save(user);

            UserDto userDto = new UserDto();
            userDto.setEmail(savedUser.getEmail());
            userDto.setName(savedUser.getName());
            userDto.setGender(savedUser.getGender());
            userDto.setId(savedUser.getId());
            userDto.setRole(savedUser.getRole());
            return new ServiceResponse<>(true, userDto,
                    "Successfully created the user... PLEASE LOGIN!!!!!");
        } else {
            return new ServiceResponse<>(false, null, "The specified Email id is already present");
        }
    }
    @Override
    public ServiceResponse<UserDto> getUser(String email, String password) {

        Optional<User> userOpt = userRepo.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                UserDto userDto = new UserDto();
                userDto.setEmail(user.getEmail());
                userDto.setName(user.getName());
                userDto.setGender(user.getGender());
                userDto.setId(user.getId());
                return new ServiceResponse<>(true, userDto, "User logged in successfully.");

            } else {
                return new ServiceResponse<>(false, null, "Entered Password is wrong");
            }
        } else {
            return new ServiceResponse<>(false, null, "Entered Email ID does not exist");
        }
    }

    public User getLoggedInUser(String email) {
        return userRepo.findByEmail(email).get();
    }

    public static final String[] adminAccess = {"ROLE_ADMIN", "ROLE_USER"};
    public List<String> getRolesOfLoggedInUser(String email) {
        String roles = getLoggedInUser(email).getRole();
        List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
        if (assignRoles.contains("ROLE_ADMIN")) {
            return Arrays.stream(adminAccess).collect(Collectors.toList());
        } else {
            return List.of(new String[]{"ROLE_USER"});
        }
    }

    public ServiceResponse<User> giveAccess(String email, String userRole, Principal principal) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {

            List<String> activeRoles = getRolesOfLoggedInUser(email);
            String newRole = "";

            if (!(activeRoles.contains(userRole))) {

                newRole = user.get().getRole() + "," + userRole;
                user.get().setRole(newRole);
                userRepo.save(user.get());
                return new ServiceResponse<>(true, user.get(),
                        "Hello!!! " + user.get().getEmail() + ". New Role has been assigned to you by "
                                + principal.getName() + "i.e., " + user.get().getRole());

            } else {
                return new ServiceResponse<>(false, user.get(), "The user is already " + userRole);
            }

        }
        return new ServiceResponse<>(false, null, "cannot find the user with specified mail..!!!!");

    }

    @Override
    public List<UserDto> loadAll() {

        List<User> list = userRepo.findAll();
        List<UserDto> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            newList.add(new UserDto(list.get(i)));

        }

        return newList;
    }
}
