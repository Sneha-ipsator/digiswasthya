package sneha.ipsator.digiswasthya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sneha.ipsator.digiswasthya.Entity.Availability;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability,Long> {
}
