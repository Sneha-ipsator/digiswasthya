package sneha.ipsator.digiswasthya.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDTO {
    private String country;
    private String state;
    private String pin;
    private String city;
    private String area;
}
