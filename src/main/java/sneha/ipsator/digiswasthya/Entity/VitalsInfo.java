package sneha.ipsator.digiswasthya.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VitalsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime timestamp;

    private Double height;

    private Double weight;

    private Integer pulse;

    private Integer spo2;

    private Double sugar;

    private Double bmi;

    private Double temperature;

    private Integer bloodPressure;

    @ManyToOne
    @JsonIgnore
    private PatientInfo patientInfo;


}
