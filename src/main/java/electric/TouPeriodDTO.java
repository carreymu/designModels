package electric;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TouPeriodDTO {
    private TouState state;
    private LocalTime startTime;
    private LocalTime endTime;
    public TouPeriodDTO(TouState state, LocalTime startTime, LocalTime endTime) {
        this.state = state;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
