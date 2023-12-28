package sneha.ipsator.digiswasthya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sneha.ipsator.digiswasthya.Entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
