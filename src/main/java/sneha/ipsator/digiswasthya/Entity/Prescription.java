package sneha.ipsator.digiswasthya.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private DoctorInfo doctorInfo;

    @OneToOne
    private PatientInfo patientInfo;

    private Date date;
    private Time time;

    @OneToMany
    private List<Medicine> medicine=new ArrayList<>();
}
