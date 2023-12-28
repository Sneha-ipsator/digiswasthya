package sneha.ipsator.digiswasthya.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Column(nullable = false, name = "Email")
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;

    private String gender;

//    @OneToOne
//    private PatientInfo patientInfo;
}
