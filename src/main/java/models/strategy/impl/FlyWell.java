package models.strategy.impl;

import models.strategy.interfaces.IFly;

public class FlyWell implements IFly {
    @Override
    public void fly() {
        System.out.println("fly well...");
    }

    @Override
    public void fly(String name) {
        System.out.println(name + " fly well.");
    }
}
