package electric;

import lombok.Data;

@Data
public class TouPriceDTO {
    private TouState state;
    private double price;

    public TouPriceDTO(TouState state, double price) {
        this.state = state;
        this.price = price;
    }
}
