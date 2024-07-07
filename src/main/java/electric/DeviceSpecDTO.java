package electric;

import lombok.Data;

@Data
public class DeviceSpecDTO {
    private double capacity;
    private double standardPower;

    public DeviceSpecDTO(double capacity, double standardPower) {
        this.capacity = capacity;
        this.standardPower = standardPower;
    }
}
