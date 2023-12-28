package sneha.ipsator.digiswasthya.Dto;

import lombok.*;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AvailabilityDTO {
    private Date date;

    private Time startTime;
    private Time endTime;
}
