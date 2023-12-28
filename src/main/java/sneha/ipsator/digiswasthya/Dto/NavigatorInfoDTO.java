package sneha.ipsator.digiswasthya.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NavigatorInfoDTO {
    private String name;
    private String email;
    private String password;
    private String role;
    private String gender;
    private Integer age;
    private Integer experience;
    private String education;
    private String phoneNumber;
}
