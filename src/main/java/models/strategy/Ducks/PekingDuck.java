package models.strategy.Ducks;

import models.strategy.impl.FlyTerrible;

public class PekingDuck extends Duck{
    public PekingDuck() {
        IFly = new FlyTerrible();
    }

    @Override
    void display() {
        System.out.println("PeKing duck...");
    }
}
