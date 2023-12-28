package sneha.ipsator.digiswasthya.Service;

import sneha.ipsator.digiswasthya.Dto.PatientInfoDTO;
import sneha.ipsator.digiswasthya.Entity.PatientInfo;
import sneha.ipsator.digiswasthya.payload.ServiceResponse;

import java.security.Principal;

public interface PatientService {

    ServiceResponse<PatientInfo> registerPatient(PatientInfoDTO patientInfoDTO, String email);
}
