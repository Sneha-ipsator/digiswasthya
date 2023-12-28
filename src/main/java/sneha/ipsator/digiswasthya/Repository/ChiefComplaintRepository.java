package sneha.ipsator.digiswasthya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sneha.ipsator.digiswasthya.Entity.ChiefComplaint;

@Repository
public interface ChiefComplaintRepository extends JpaRepository<ChiefComplaint,Long> {
}
