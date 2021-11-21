package models.template;

public class Client {
    public static void main(String[] args) {
        SoyMilk readBeanSoyMilk = new ReadBeanSoyMilk();
        readBeanSoyMilk.make();

        System.out.println("=======");
        SoyMilk peanutSoyMilk = new PeanutSoyMilk();
        peanutSoyMilk.make();

        System.out.println("======钩子方法");
        SoyMilk pureMilk = new PureSoyMilk();
        pureMilk.make();
    }
}
