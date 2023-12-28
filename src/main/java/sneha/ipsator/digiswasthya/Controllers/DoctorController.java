package sneha.ipsator.digiswasthya.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sneha.ipsator.digiswasthya.Dto.DoctorInfoDTO;
import sneha.ipsator.digiswasthya.Dto.NavigatorInfoDTO;
import sneha.ipsator.digiswasthya.Entity.DoctorInfo;
import sneha.ipsator.digiswasthya.Entity.NavigatorInfo;
import sneha.ipsator.digiswasthya.Service.DoctorService;
import sneha.ipsator.digiswasthya.payload.ApiResponse;
import sneha.ipsator.digiswasthya.payload.ServiceResponse;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerDoctor(@RequestBody DoctorInfoDTO doctorInfoDTO,Principal principal) {
        try {
            String email = principal != null ? principal.getName() : null;
            ServiceResponse<DoctorInfo> doctorInfoResponse = doctorService.registerDoctor(doctorInfoDTO,email);
            if (doctorInfoResponse.getSuccess()) {
                return new ResponseEntity<>(new ApiResponse("Success", doctorInfoResponse.getData(), null), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        new ApiResponse("Failure", null, new Error(doctorInfoResponse.getMessage())),
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            log.error("Exception in registerDoctor: " + ex.getMessage(), ex);
            return new ResponseEntity<>(
                    new ApiResponse("Failure", "Something went wrong", new Error(ex.getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bySpecialization")
    public ResponseEntity<List<DoctorInfo>> getDoctorsBySpecialization(@RequestParam List<String> specializations) {
        List<DoctorInfo> doctors = doctorService.getDoctorsBySpecializations(specializations);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
}
