package models.mediator;

public class TV extends Colleague {
    public TV(Mediator mediator, String name) {
        super(mediator, name);
        mediator.Register(name, this);
    }

    public void startTV() {
        System.out.println("Time to start TV.");
        sendMessage(0);
    }

    public void stopTV(){
        System.out.println("Stop TV.");
        sendMessage(1);
    }
    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange, this.name);
    }
}
