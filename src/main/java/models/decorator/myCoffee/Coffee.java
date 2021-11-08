package models.decorator.myCoffee;

import models.decorator.Drink;

public class Coffee extends Drink {
    @Override
    public float cost() {
        return super.getPrice();
    }
}
