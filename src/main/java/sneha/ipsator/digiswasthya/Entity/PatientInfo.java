package sneha.ipsator.digiswasthya.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PatientInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer age;
    private String motherTongue;
//    private String profilePhotoUrl;
    private Date followUp;
    private String contactNumber;
    private String emergencyNumber;

    private String relation;

    private String visitLocation;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Address> address=new ArrayList<>();

    @OneToMany(mappedBy = "patientInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChiefComplaint> chiefComplaints = new ArrayList<>();

//    @OneToMany(mappedBy = "patientInfo", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<HealthReports> healthReports = new ArrayList<>(); // optimize

//    @OneToMany(mappedBy = "patientInfo", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ChiefComplaint> chiefComplaints;//optimize
}
