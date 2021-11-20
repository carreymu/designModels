package models.template;

public class PeanutSoyMilk extends SoyMilk {
    @Override
    void addCondiments() {
        System.out.println("选用花生");
    }

    @Override
    void soak() {
        System.out.println("浸猪笼5小时。。。");
    }

    @Override
    void beat() {
        System.out.println("豆已坏。。。");
    }
}
