package models.decorator.myDecorator;

import models.decorator.Drink;

public class Decorator extends Drink {
    private Drink obj;

    public Decorator(Drink obj) {
        this.obj = obj;
    }

    @Override
    public String getDescription() {
        // obj.getDescription()输出被装饰者的信息
        return super.getDescription() + "  " + getPrice() + " && " + obj.getDescription();
    }

    @Override
    public float cost() {
        // getprice自己的价格
        return super.getPrice() + obj.cost();
    }
}
