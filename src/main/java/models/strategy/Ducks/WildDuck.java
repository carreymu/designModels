package models.strategy.Ducks;

import models.strategy.impl.FlyWell;

public class WildDuck extends Duck{
    public WildDuck() {
        IFly = new FlyWell();
    }

    @Override
    void display() {
        System.out.println("wild duck.");
    }
}
