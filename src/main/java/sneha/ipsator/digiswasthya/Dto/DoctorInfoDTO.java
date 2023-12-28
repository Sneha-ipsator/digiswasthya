package sneha.ipsator.digiswasthya.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import lombok.*;
import sneha.ipsator.digiswasthya.Entity.Availability;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorInfoDTO {
    private String name;
    private String email;

    private String password;

    private String role;

    private String gender;

    private List<String> specializations;

    private Integer experience;
    private String phoneNumber;

    private String hospitalName;

    private String addedBy;

//    private List<Availability> availability=new ArrayList<>();
}
