package models.decorator.myDecorator;

import models.decorator.Drink;

public class Soy extends Decorator {
    public Soy(Drink obj) {
        super(obj);
        // 调味品描述及价格
        setDescription("豆浆");
        setPrice(6f);
    }
}
