package sneha.ipsator.digiswasthya.Service;

import sneha.ipsator.digiswasthya.Dto.NavigatorInfoDTO;
import sneha.ipsator.digiswasthya.Entity.NavigatorInfo;
import sneha.ipsator.digiswasthya.payload.ServiceResponse;

public interface NavigatorService {
    ServiceResponse<NavigatorInfo> registerNavigator(String name, NavigatorInfoDTO navigatorInfoDTO);
}
