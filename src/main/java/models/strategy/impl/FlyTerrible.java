package models.strategy.impl;

import models.strategy.interfaces.IFly;

public class FlyTerrible implements IFly {
    @Override
    public void fly() {
        System.out.println("fly terrible...");
    }

    @Override
    public void fly(String name) {
        System.out.println(name + "fly terrible.");
    }
}
