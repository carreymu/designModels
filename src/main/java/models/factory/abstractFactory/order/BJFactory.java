package models.factory.abstractFactory.order;

import models.factory.abstractFactory.piza.BJCheesePizza;
import models.factory.abstractFactory.piza.BJPepperPizza;
import models.factory.abstractFactory.piza.Pizza;

public class BJFactory implements AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("抽象工厂模式>>>");
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
            pizza.setName(" cheese pizza");
        } else if (orderType.equals("pepper")) {
            pizza = new BJPepperPizza();
            pizza.setName(" pepper pizza");
        }
        return pizza;
    }
}
