package models.factory.factoryMethod;

public class BJOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(String orderType) {
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
