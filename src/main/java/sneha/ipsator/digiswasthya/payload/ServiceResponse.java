package sneha.ipsator.digiswasthya.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse<T> {
    private Boolean success;
    private T data;
    private String message;
}
