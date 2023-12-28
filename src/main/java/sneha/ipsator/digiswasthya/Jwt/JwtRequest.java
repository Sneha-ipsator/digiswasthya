package sneha.ipsator.digiswasthya.Jwt;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JwtRequest {
    private  String email;
    private String password;
}
