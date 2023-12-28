package sneha.ipsator.digiswasthya.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sneha.ipsator.digiswasthya.Entity.User;
import sneha.ipsator.digiswasthya.Repository.UserRepo;
import sneha.ipsator.digiswasthya.payload.CustomUserDetail;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    public UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!!"));
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        return customUserDetail;
    }
}
