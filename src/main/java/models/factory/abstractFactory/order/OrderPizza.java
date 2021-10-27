package models.factory.abstractFactory.order;

import models.factory.abstractFactory.piza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {
    AbsFactory absFactory;

    public OrderPizza(AbsFactory factory) {
        setFactory(factory);
    }

    private void setFactory(AbsFactory factory) {
        Pizza pizza = null;
        String orderType = "";
        this.absFactory = factory;
        do {
            orderType = getType();
            pizza = factory.createPizza(orderType);
            if (pizza == null) {
                System.out.println("没得Pizza。");
                break;
            }
            // 输出Pizza制作过程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }

    private String getType() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza type: ");
            String str = br.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
