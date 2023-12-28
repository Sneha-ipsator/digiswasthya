package sneha.ipsator.digiswasthya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sneha.ipsator.digiswasthya.Entity.Disease;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease,Long> {
}
