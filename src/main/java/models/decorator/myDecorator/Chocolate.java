package models.decorator.myDecorator;

import models.decorator.Drink;

// 具体的Decorator,具体调味品
public class Chocolate extends Decorator {
    public Chocolate(Drink obj) {
        super(obj);
        setDescription("巧克力");
        setPrice(3.4f);
    }
}
