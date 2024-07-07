package electric;

import lombok.Data;

import java.time.LocalTime;

@Data
public class DispatchCommand {
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final Double power;

    public DispatchCommand(LocalTime startTime, LocalTime endTime, Double power) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.power = power;
    }

    @Override
    public String toString() {
        return "DispatchCommand{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", power=" + power +
                '}';
    }
}
