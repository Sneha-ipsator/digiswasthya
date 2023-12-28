package sneha.ipsator.digiswasthya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sneha.ipsator.digiswasthya.Entity.VitalsInfo;

@Repository
public interface VitalsInfoRepository extends JpaRepository<VitalsInfo,Long> {
}
