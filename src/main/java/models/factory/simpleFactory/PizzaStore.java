package models.factory.simpleFactory;

public class PizzaStore {
    public static void main(String[] args) {
        new OrderPizza(new SimpleFactory());
        System.out.println("退出");
    }
}
