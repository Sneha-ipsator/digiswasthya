package sneha.ipsator.digiswasthya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sneha.ipsator.digiswasthya.Entity.NavigatorInfo;

@Repository
public interface NavigatorInfoRepository extends JpaRepository<NavigatorInfo,Long> {
}
