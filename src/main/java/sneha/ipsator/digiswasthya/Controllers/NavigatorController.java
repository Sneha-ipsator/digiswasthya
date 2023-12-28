package sneha.ipsator.digiswasthya.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sneha.ipsator.digiswasthya.Dto.NavigatorInfoDTO;
import sneha.ipsator.digiswasthya.Entity.NavigatorInfo;
import sneha.ipsator.digiswasthya.Service.NavigatorService;
import sneha.ipsator.digiswasthya.payload.ApiResponse;
import sneha.ipsator.digiswasthya.payload.ServiceResponse;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/navigator")
public class NavigatorController {

    @Autowired
    private NavigatorService navigatorService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerNavigator(Principal principal, @RequestBody NavigatorInfoDTO navigatorInfoDTO) {
        try {
            String email= principal != null ? principal.getName() : null;
            ServiceResponse<NavigatorInfo> navigatorInfo = navigatorService.registerNavigator(email, navigatorInfoDTO);
            if (navigatorInfo.getSuccess()) {
                return new ResponseEntity<>(new ApiResponse("Success", navigatorInfo.getData(), null), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        new ApiResponse("Failure", null, new Error(navigatorInfo.getMessage())),
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(
                    new ApiResponse("Failure", "Something Went wrong", new Error(ex.getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}