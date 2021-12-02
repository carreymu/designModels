package models.strategy.impl;

import models.strategy.interfaces.IFly;

public class NotFly implements IFly {
    @Override
    public void fly() {
        System.out.println("No fly...");
    }

    @Override
    public void fly(String name) {
        System.out.println(name + " does not fly.");
    }
}
