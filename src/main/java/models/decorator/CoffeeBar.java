package models.decorator;

import models.decorator.myCoffee.ShortBlack;
import models.decorator.myDecorator.Chocolate;
import models.decorator.myDecorator.Milk;

/**
 * 无论新增decorator或coffee都可顺理成章调用方法；用调料包括咖啡
 */
public class CoffeeBar {
    public static void main(String[] args) {
        Drink shortBlack = new ShortBlack();
        System.out.println("原味：" + shortBlack.getDescription() + ",费用：" + shortBlack.cost());

        System.out.println("加入牛奶啊>>>");
        shortBlack = new Milk(shortBlack);
        System.out.println(shortBlack.getDescription() + ", 费用：" + shortBlack.cost() + "");

        System.out.println("加入巧克力啊>>>");
        shortBlack = new Chocolate(shortBlack);
        System.out.println(shortBlack.getDescription() + ", 费用：" + shortBlack.cost() + "");
    }
}
