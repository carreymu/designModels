package models.strategy.Ducks;

import models.strategy.interfaces.IFly;

public abstract class Duck {
    IFly IFly;

    abstract void display();

    public void fly() {
        if (IFly != null) {
            IFly.fly();
        }
    }

    public void fly(String name) {
        if (IFly != null) {
            IFly.fly(name);
        }
    }

    public void setIFly(models.strategy.interfaces.IFly IFly) {
        this.IFly = IFly;
    }
}
