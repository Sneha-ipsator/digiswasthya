package sneha.ipsator.digiswasthya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sneha.ipsator.digiswasthya.Entity.DoctorInfo;

import java.util.List;

@Repository
public interface DoctorInfoRepository extends JpaRepository<DoctorInfo,Long> {
    List<DoctorInfo> findAllBySpecializationsIn(List<String> specializations);
}
