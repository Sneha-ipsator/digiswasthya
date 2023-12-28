package sneha.ipsator.digiswasthya.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HealthReports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime uploadTime;

    private String fileName;

//    private String s3Url;

    @ManyToOne
    @JoinColumn(name = "patient_info_id")
    private PatientInfo patientInfo;

//    @OneToMany
//    private List<VitalsInfo> vitalsInfo;
//
//    @ManyToOne
//    @JoinColumn(name = "disease_id")
//    private Disease disease;
}
