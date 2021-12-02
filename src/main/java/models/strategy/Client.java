package models.strategy;

import models.strategy.Ducks.Duck;
import models.strategy.Ducks.PekingDuck;
import models.strategy.Ducks.ToyDuck;
import models.strategy.Ducks.WildDuck;
import models.strategy.impl.NotFly;

public class Client {
    public static void main(String[] args) {
        Duck wildDuck = new WildDuck();
        wildDuck.fly();
        wildDuck.fly("wileDuck");
        System.out.println("======");
        Duck toyDuck = new ToyDuck();
        toyDuck.fly();
        System.out.println("-----");
        Duck pekingDuck = new PekingDuck();
        pekingDuck.fly();
        System.out.println("---设置peking duck飞行行为");
        pekingDuck.setIFly(new NotFly());
        pekingDuck.fly();
    }
}
