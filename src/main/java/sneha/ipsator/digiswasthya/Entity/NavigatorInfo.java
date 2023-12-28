package sneha.ipsator.digiswasthya.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NavigatorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    private Integer age;
    private Integer experience;
    private String education;
    private String phoneNumber;


    private String addedBy;
}
