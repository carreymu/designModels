package electric;

import lombok.Data;

@Data
public class DispatchContext {
    private TouDTO touDTO;
    private DeviceSpecDTO deviceSpecDTO;
    public DispatchContext(TouDTO touDTO, DeviceSpecDTO deviceSpecDTO) {
        this.touDTO  = touDTO;
        this.deviceSpecDTO = deviceSpecDTO;
    }
}
