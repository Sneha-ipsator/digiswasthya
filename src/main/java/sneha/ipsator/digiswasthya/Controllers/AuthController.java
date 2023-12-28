package sneha.ipsator.digiswasthya.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sneha.ipsator.digiswasthya.Entity.User;
import sneha.ipsator.digiswasthya.Service.UserService;
import sneha.ipsator.digiswasthya.payload.ApiResponse;
import sneha.ipsator.digiswasthya.Dto.UserDto;
import sneha.ipsator.digiswasthya.Jwt.JwtRequest;
import sneha.ipsator.digiswasthya.Security.JwtHelper;
import sneha.ipsator.digiswasthya.payload.ServiceResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user) {
        ServiceResponse<UserDto> createdUser = userService.createUser(user);
        if (createdUser.getSuccess()) {
            return new ResponseEntity<>((new ApiResponse("success", createdUser.getData(), null)),
                    HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(
                    (new ApiResponse("failure", null, new Error(createdUser.getMessage()))),
                    HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> createToken(@RequestBody JwtRequest jwtRequest) {
        doAuthenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        final UserDetails user = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        String token = jwtHelper.generateToken(user);
        ServiceResponse<UserDto> getUser = userService.getUser(jwtRequest.getEmail(),
                jwtRequest.getPassword());
        if (getUser.getSuccess()) {
            return new ResponseEntity<>((new ApiResponse("success",token, null)),
                    HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(
                    (new ApiResponse("failure", null, new Error(getUser.getMessage()))),
                    HttpStatus.BAD_REQUEST);
        }

    }
    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
