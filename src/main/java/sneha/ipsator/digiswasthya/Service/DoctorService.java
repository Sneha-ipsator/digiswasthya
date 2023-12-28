package sneha.ipsator.digiswasthya.Service;

import sneha.ipsator.digiswasthya.Dto.DoctorInfoDTO;
import sneha.ipsator.digiswasthya.Entity.DoctorInfo;
import sneha.ipsator.digiswasthya.payload.ServiceResponse;

import java.util.List;

public interface DoctorService {
    ServiceResponse<DoctorInfo> registerDoctor(DoctorInfoDTO doctorInfoDTO,String email);

//    List<DoctorInfo> getDoctorsBySpecialization(String specialization);

    List<DoctorInfo> getDoctorsBySpecializations(List<String> specializations);
}
