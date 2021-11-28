package models.mediator;

public class CoffeeMachine  extends Colleague {
    public CoffeeMachine(Mediator mediator, String name) {
        super(mediator, name);
        mediator.Register(name, this);
    }

    public void startCoffeeMachine() {
        System.out.println("Time to start coffee.");
    }

    public void finishCoffeeMachine(){
        System.out.println("After 5 min.");
        System.out.println("Coffee is OK.");
        sendMessage(0);
    }
    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange, this.name);
    }
}
