package sneha.ipsator.digiswasthya.Dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import sneha.ipsator.digiswasthya.Entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PatientInfoDTO {
    private String name;

    private String email;

    private String password;

    private String role;

    private String gender;

    private Integer age;

    private String motherTongue;

//    private String profilePhotoUrl;

    private Date followUp;

    private String contactNumber;

    private String emergencyNumber;

    private String relation;

    private String visitLocation;


    private List<AddressDTO> address=new ArrayList<>();

    private LocalDateTime timestamp;

    private Double height;

    private Double weight;

    private Integer pulse;

    private Integer spo2;

    private Double sugar;

    private Double bmi;

    private Double temperature;

    private Integer bloodPressure;

    private String diseaseName;

    private List<ChiefComplaintDTO> chiefComplaint=new ArrayList<>();
}
