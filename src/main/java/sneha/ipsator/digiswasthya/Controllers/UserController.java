package sneha.ipsator.digiswasthya.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import sneha.ipsator.digiswasthya.Dto.UserDto;
import sneha.ipsator.digiswasthya.Entity.User;
import sneha.ipsator.digiswasthya.Service.UserService;
import sneha.ipsator.digiswasthya.payload.ApiResponse;
import sneha.ipsator.digiswasthya.payload.ServiceResponse;
import sneha.ipsator.digiswasthya.Dto.UpdateRoleDto;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@EnableMethodSecurity
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/access")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ApiResponse> giveAccessToUser(@RequestBody UpdateRoleDto updateRoleDto,
                                                        Principal principal) {
        ServiceResponse<User> getUser = userService.giveAccess(updateRoleDto.getEmail(),
                updateRoleDto.getRole(), principal);
        if (getUser.getSuccess()) {
            return new ResponseEntity<>(
                    (new ApiResponse("success", new UserDto(getUser.getData()), null)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    (new ApiResponse("failure", null, new Error(getUser.getMessage()))),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fetchAllUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Secured("ROLE_ADMIN")
    public List<UserDto> loadUsers() {
        return userService.loadAll();
    }

    @GetMapping("/getName")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String giveName(Principal principal) {
        return principal.getName();
    }
}