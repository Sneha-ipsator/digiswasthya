package sneha.ipsator.digiswasthya.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DoctorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

//    @ManyToMany
//    @JoinTable(
//            name = "doctor_specializations",
//            joinColumns = @JoinColumn(name = "doctor_id"),
//            inverseJoinColumns = @JoinColumn(name = "specialization_id")
//    )
//    private Set<Specializations> specializations = new HashSet<>();

//    private Set<String> specializationNames;
    @ElementCollection
    @CollectionTable(name = "specializations", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "specialization")
    private List<String> specializations;

    private Integer experience;
    private String phoneNumber;

    private String hospitalName;

    private String addedBy;

//    @OneToMany
//    private List<Availability> availability=new ArrayList<>();
}
