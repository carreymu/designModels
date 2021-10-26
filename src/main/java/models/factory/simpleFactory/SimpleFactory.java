package models.factory.simpleFactory;

public class SimpleFactory {
    // 根据orderType订购不同Pizza
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        System.out.println("使用简单工厂模式");
        if (orderType.equals("greek")) {
            pizza = new GreekPizza();
            pizza.setName(" greek pizza");
        } else if (orderType.equals("cheese")) {
            pizza = new CheesePizza();
            pizza.setName(" cheese pizza");
        } else if (orderType.equals("pepper")) {
            pizza = new PepperPizza();
            pizza.setName(" pepper pizza");
        }
        return pizza;
    }
}
