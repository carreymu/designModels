package models.factory.simpleFactory;

public class PizzaStore {
    public static void main(String[] args) {
        new OrderPizza(new SimpleFactory());
        System.out.println("退出1");
        System.out.println("--------");

        new OrderPizza2();
        System.out.println("退出2");
    }
}
