package models.strategy.Ducks;

import models.strategy.impl.NotFly;

public class ToyDuck extends Duck {
    public ToyDuck() {
        IFly = new NotFly();
    }

    @Override
    void display() {
        System.out.println("toy duck..");
    }
}
