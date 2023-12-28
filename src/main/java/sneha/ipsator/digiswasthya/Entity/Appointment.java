package sneha.ipsator.digiswasthya.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private PatientInfo patientInfo;
    @OneToOne
    private DoctorInfo doctorInfo;
    private Date date;
    private Time time;
    @ManyToOne
    private Camp camp;

}
