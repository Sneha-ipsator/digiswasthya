package sneha.ipsator.digiswasthya.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private Time startTime;
    private Time endTime;

    @OneToMany
    private List<DoctorInfo> doctorInfo;
}
