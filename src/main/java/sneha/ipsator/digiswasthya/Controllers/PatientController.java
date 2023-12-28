package sneha.ipsator.digiswasthya.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sneha.ipsator.digiswasthya.Dto.PatientInfoDTO;
import sneha.ipsator.digiswasthya.Entity.PatientInfo;
import sneha.ipsator.digiswasthya.Service.PatientService;
import sneha.ipsator.digiswasthya.payload.ApiResponse;
import sneha.ipsator.digiswasthya.payload.ServiceResponse;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerPatient(@RequestBody PatientInfoDTO patientInfoDTO, Principal principal){
        try{
           String email= principal != null ? principal.getName() : null;

            ServiceResponse<PatientInfo> patientInfoResponse=patientService.registerPatient(patientInfoDTO,email);
            if(patientInfoResponse.getSuccess())
            {
                return new ResponseEntity<>(new ApiResponse("success",patientInfoResponse.getData(),null), HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(new ApiResponse("failed",null,new Error(patientInfoResponse.getMessage())),HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex)
        {
            log.error("Exception in registerPatient: " + ex.getMessage(), ex);
           return new ResponseEntity<>(new ApiResponse("failed","Something went wrong",new Error(ex.getMessage())),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
