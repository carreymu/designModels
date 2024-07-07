package electric;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class TouDTO {
    private LocalDate now;
    private Set<TouPeriodDTO> periods;
    private Set<TouPriceDTO> prices;
    public TouDTO(LocalDate now, Set<TouPeriodDTO> periods, Set<TouPriceDTO> prices) {
        this.now = now;
        this.periods = periods;
        this.prices = prices;
    }
}
