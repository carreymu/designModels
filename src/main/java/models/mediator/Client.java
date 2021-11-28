package models.mediator;

public class Client {
    public static void main(String[] args) {
        ConcreteMediator concreteMediator = new ConcreteMediator();
        Alarm alarm = new Alarm(concreteMediator, "alarm");
        CoffeeMachine coffeeMachine = new CoffeeMachine(concreteMediator, "coffeeMachine");
        TV tv = new TV(concreteMediator, "TV");
        alarm.sendAlarm(0);
        coffeeMachine.finishCoffeeMachine();
        System.out.println("======");
        alarm.sendAlarm(1);

        tv.startTV();
    }
}
