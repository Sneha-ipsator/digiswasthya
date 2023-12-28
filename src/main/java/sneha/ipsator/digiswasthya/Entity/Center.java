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
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private Address address;

    @OneToMany
    private List<NavigatorInfo> navigatorInfo=new ArrayList<>();

    @OneToMany
    private List<PatientInfo> patientInfo=new ArrayList<>();
}
