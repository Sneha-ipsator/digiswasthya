package sneha.ipsator.digiswasthya.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sneha.ipsator.digiswasthya.Entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String role;
    private String gender;

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.gender = user.getGender();
        this.role = user.getRole();
    }



}
