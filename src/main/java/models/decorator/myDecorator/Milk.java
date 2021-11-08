package models.decorator.myDecorator;

import models.decorator.Drink;

public class Milk extends Decorator {
    public Milk(Drink obj) {
        super(obj);
        setDescription("牛奶");
        setPrice(3.2f);
    }
}
