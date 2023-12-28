package sneha.ipsator.digiswasthya.Service;

import sneha.ipsator.digiswasthya.Dto.UserDto;
import sneha.ipsator.digiswasthya.Entity.User;
import sneha.ipsator.digiswasthya.payload.ServiceResponse;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface UserService {

    ServiceResponse<UserDto> getUser(String email, String password);

    ServiceResponse<UserDto> createUser(User user);

    ServiceResponse<User> giveAccess(String email, String userRole, Principal principal);

    List<UserDto> loadAll();



}
