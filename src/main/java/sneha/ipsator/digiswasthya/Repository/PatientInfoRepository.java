package sneha.ipsator.digiswasthya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sneha.ipsator.digiswasthya.Entity.PatientInfo;

@Repository
public interface PatientInfoRepository extends JpaRepository<PatientInfo,Long> {
}
